package com.jmoriba.muscletrack.designsystem.component.camera

import android.graphics.PointF
import com.google.mlkit.vision.pose.Pose
import com.google.mlkit.vision.pose.PoseLandmark
import kotlin.math.*

data class SquatFeedback(
    val message: String,
    val repCount: Int,
    val velocity: Float?,
    val repDuration: Float?,
    val rom: Double?
)

object PoseProcessor {
    private var lastSquatStarted = false
    private var count = 0

    private var startTime: Long? = null
    private var lastRepDuration: Float? = null
    private var lastROM: Double? = null
    private var currentSpeed: Float? = null

    private var minKneeAngle = 180.0
    private var maxKneeAngle = 0.0

    private var repStartHipY: Float? = null
    private var repEndHipY: Float? = null

    private fun estimatePixelToMeterRatio(shoulder: PointF?, hip: PointF?): Float {
        return if (shoulder != null && hip != null) {
            val torsoHeight = abs(shoulder.y - hip.y)
            if (torsoHeight > 0) 0.5f / torsoHeight else 1f  // Torso típico ~0.5 m
        } else 1f
    }

    fun analyze(pose: Pose, timestamp: Long): SquatFeedback {
        val lHip = pose.getPoseLandmark(PoseLandmark.LEFT_HIP)?.position
        val rHip = pose.getPoseLandmark(PoseLandmark.RIGHT_HIP)?.position
        val lKnee = pose.getPoseLandmark(PoseLandmark.LEFT_KNEE)?.position
        val rKnee = pose.getPoseLandmark(PoseLandmark.RIGHT_KNEE)?.position
        val lAnkle = pose.getPoseLandmark(PoseLandmark.LEFT_ANKLE)?.position
        val rAnkle = pose.getPoseLandmark(PoseLandmark.RIGHT_ANKLE)?.position
        val lFoot = pose.getPoseLandmark(PoseLandmark.LEFT_FOOT_INDEX)?.position
        val rFoot = pose.getPoseLandmark(PoseLandmark.RIGHT_FOOT_INDEX)?.position
        val lShoulder = pose.getPoseLandmark(PoseLandmark.LEFT_SHOULDER)?.position
        val rShoulder = pose.getPoseLandmark(PoseLandmark.RIGHT_SHOULDER)?.position

        val leftKneeAngle = if (lHip != null && lKnee != null && lAnkle != null) calculateAngle(lHip, lKnee, lAnkle) else 180.0
        val rightKneeAngle = if (rHip != null && rKnee != null && rAnkle != null) calculateAngle(rHip, rKnee, rAnkle) else 180.0
        val avgKneeAngle = (leftKneeAngle + rightKneeAngle) / 2.0

        minKneeAngle = min(minKneeAngle, avgKneeAngle)
        maxKneeAngle = max(maxKneeAngle, avgKneeAngle)

        val footDistance = if (lFoot != null && rFoot != null) distance(lFoot, rFoot) else null
        val shoulderDistance = if (lShoulder != null && rShoulder != null) distance(lShoulder, rShoulder) else null
        val asymmetry = abs(leftKneeAngle - rightKneeAngle)

        val avgHipY = if (lHip != null && rHip != null) (lHip.y + rHip.y) / 2 else null

        val atBottom = avgKneeAngle < 70

        if (atBottom && !lastSquatStarted) {
            // Inicio de una nueva sentadilla
            lastSquatStarted = true
            startTime = timestamp
            minKneeAngle = avgKneeAngle
            maxKneeAngle = avgKneeAngle
            repStartHipY = avgHipY
        }

        if (!atBottom && lastSquatStarted) {
            // Fin de una repetición
            lastSquatStarted = false
            count += 1
            lastRepDuration = if (startTime != null) (timestamp - startTime!!) / 1000f else null
            lastROM = maxKneeAngle - minKneeAngle
            repEndHipY = avgHipY

            val pxToM = estimatePixelToMeterRatio(lShoulder, lHip)
            val romPixels = abs((repEndHipY ?: 0f) - (repStartHipY ?: 0f))
            val romMeters = romPixels * pxToM

            currentSpeed = if (lastRepDuration != null && lastRepDuration!! > 0f) {
                romMeters / lastRepDuration!!
            } else null
        }

        val hipFlexionAngle = if (lShoulder != null && lHip != null && lKnee != null) {
            calculateAngle(lShoulder, lHip, lKnee)
        } else null

        val spineMsg = when {
            hipFlexionAngle != null && hipFlexionAngle < 40 -> "Excessive forward lean"
            hipFlexionAngle != null && hipFlexionAngle > 120 -> "Upright posture"
            else -> "Back angle ok"
        }

        val depthMsg = when {
            avgKneeAngle > 140 -> "Go deeper for full range"
            avgKneeAngle < 40 -> "Caution: Excessive depth"
            else -> "Depth is good"
        }

        val symmetryMsg = if (asymmetry > 15) "Lateral imbalance detected" else "Symmetrical movement"

        val stanceMsg = if (footDistance != null && shoulderDistance != null) {
            val ratio = footDistance / shoulderDistance
            when {
                ratio < 0.96f -> "Widen your stance"
                ratio > 1.44f -> "Bring your feet closer"
                else -> "Stance width is appropriate"
            }
        } else "Stance not detected"

        val feedback = listOf(depthMsg, symmetryMsg, stanceMsg, spineMsg).joinToString(" | ")

        return SquatFeedback(
            message = feedback,
            repCount = count,
            velocity = currentSpeed,
            repDuration = lastRepDuration,
            rom = lastROM
        )
    }

    private fun calculateAngle(a: PointF, b: PointF, c: PointF): Double {
        val abx = a.x - b.x; val aby = a.y - b.y
        val cbx = c.x - b.x; val cby = c.y - b.y
        val dot = abx * cbx + aby * cby
        val magAB = hypot(abx.toDouble(), aby.toDouble())
        val magCB = hypot(cbx.toDouble(), cby.toDouble())
        val angleRad = acos((dot / (magAB * magCB)).coerceIn(-1.0, 1.0))
        return angleRad * (180.0 / PI)
    }

    private fun distance(a: PointF, b: PointF): Float {
        return hypot((a.x - b.x).toDouble(), (a.y - b.y).toDouble()).toFloat()
    }
}
