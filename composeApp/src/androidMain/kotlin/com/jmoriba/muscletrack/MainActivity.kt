package com.jmoriba.muscletrack

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.jmoriba.muscletrack.extension.PermissionBridge
import com.jmoriba.muscletrack.extension.PermissionResultCallback
import com.jmoriba.muscletrack.extension.PermissionsBridgeListener
import org.koin.core.context.GlobalContext

class MainActivity : ComponentActivity(), PermissionsBridgeListener {
    private var cameraPermissionResultCallback: PermissionResultCallback? = null

    private val requestCameraPermissionLauncher: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                cameraPermissionResultCallback?.onPermissionGranted()
            } else {
                val permanentlyDenied =
                    !shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)
                cameraPermissionResultCallback?.onPermissionDenied(permanentlyDenied)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GlobalContext.get().get<PermissionBridge>().setListener(this)

        setContent {
            App()
        }
    }

    override fun requestCameraPermission(callback: PermissionResultCallback) {
        val permission = Manifest.permission.CAMERA
        when {
            ContextCompat.checkSelfPermission(
                this,
                permission
            ) == PackageManager.PERMISSION_GRANTED -> {
                callback.onPermissionGranted()
            }

            shouldShowRequestPermissionRationale(permission) -> {
                callback.onPermissionDenied(false)
            }

            else -> {
                cameraPermissionResultCallback = callback
                requestCameraPermissionLauncher.launch(permission)
            }
        }
    }

    override fun isCameraPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_CONTACTS
        ) == PackageManager.PERMISSION_GRANTED
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}