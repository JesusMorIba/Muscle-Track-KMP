package com.jmoriba.muscletrack.designsystem.component.camera

import android.annotation.SuppressLint
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.pose.PoseDetection
import com.google.mlkit.vision.pose.PoseDetector
import com.google.mlkit.vision.pose.accurate.AccuratePoseDetectorOptions

@SuppressLint("UnsafeOptInUsageError")
class PoseAnalyzer(
    private val onFeedback: (SquatFeedback) -> Unit
) : ImageAnalysis.Analyzer {
    private val poseDetector: PoseDetector by lazy {
        val options = AccuratePoseDetectorOptions.Builder()
            .setDetectorMode(AccuratePoseDetectorOptions.STREAM_MODE)
            .build()
        PoseDetection.getClient(options)
    }

    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image ?: run { imageProxy.close(); return }
        val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
        val timestamp = imageProxy.imageInfo.timestamp
        poseDetector.process(image)
            .addOnSuccessListener { pose -> onFeedback(PoseProcessor.analyze(pose, timestamp)) }
            .addOnFailureListener { e -> Log.e("PoseAnalyzer", "Error", e) }
            .addOnCompleteListener { imageProxy.close() }
    }
}


