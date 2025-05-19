package com.jmoriba.muscletrack.extension

actual interface PermissionsBridgeListener {
    actual fun requestCameraPermission(callback: PermissionResultCallback)
    actual fun isCameraPermissionGranted(): Boolean
}