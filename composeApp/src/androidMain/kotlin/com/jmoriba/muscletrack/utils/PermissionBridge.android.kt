package com.jmoriba.muscletrack.utils

actual interface PermissionsBridgeListener {
    actual fun requestCameraPermission(callback: PermissionResultCallback)
    actual fun isCameraPermissionGranted(): Boolean
}