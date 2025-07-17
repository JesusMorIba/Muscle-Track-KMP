package com.jmoriba.muscletrack.common.utils

expect interface PermissionsBridgeListener {
    fun requestCameraPermission(callback: PermissionResultCallback)
    fun isCameraPermissionGranted(): Boolean
}

class PermissionBridge {

    private var listener: PermissionsBridgeListener? = null

    fun setListener(listener: PermissionsBridgeListener) {
        this.listener = listener
    }

    fun requestCameraPermission(callback: PermissionResultCallback) {
        listener?.requestCameraPermission(callback) ?: error("Callback handler not set")
    }

    fun isCameraPermissionGranted(): Boolean {
        return listener?.isCameraPermissionGranted() ?: false
    }

}

interface PermissionResultCallback {
    fun onPermissionGranted()
    fun onPermissionDenied(isPermanentDenied: Boolean)
}
