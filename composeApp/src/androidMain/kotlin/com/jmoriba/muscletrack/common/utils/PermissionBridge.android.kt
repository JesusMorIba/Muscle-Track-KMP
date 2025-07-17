package com.jmoriba.muscletrack.common.utils

import com.jmoriba.muscletrack.common.utils.PermissionResultCallback

actual interface PermissionsBridgeListener {
    actual fun requestCameraPermission(callback: PermissionResultCallback)
    actual fun isCameraPermissionGranted(): Boolean
}