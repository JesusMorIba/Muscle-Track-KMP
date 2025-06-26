package com.jmoriba.muscletrack.utils

import kotlin.experimental.ExperimentalObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName(swiftName = "PermissionRequestProtocol")
actual interface PermissionsBridgeListener {
    actual fun requestCameraPermission(callback: PermissionResultCallback)
    actual fun isCameraPermissionGranted(): Boolean
}

@Suppress("unused")
fun registerPermissionHandler(listener: PermissionsBridgeListener){
    koinInstance.koin.get<PermissionBridge>().setListener(listener)
}