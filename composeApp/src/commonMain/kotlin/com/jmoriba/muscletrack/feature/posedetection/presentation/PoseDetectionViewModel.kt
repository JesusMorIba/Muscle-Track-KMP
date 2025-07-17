package com.jmoriba.muscletrack.feature.posedetection.presentation

import com.jmoriba.muscletrack.domain.models.WorkoutModelUI
import com.jmoriba.muscletrack.common.utils.PermissionBridge
import com.jmoriba.muscletrack.common.utils.PermissionResultCallback
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import moe.tlaster.precompose.viewmodel.ViewModel

data class PoseDetectionUiState(
    val workouts: List<WorkoutModelUI?> = emptyList(),
    val isCameraPermissionGranted: Boolean = false
)

class PoseDetectionViewModel(
    private val permissionBridge: PermissionBridge
) : ViewModel() {
    private val _uiState = MutableStateFlow(PoseDetectionUiState())
    val uiState = _uiState.asStateFlow()

    init {
        checkCameraPermission()
    }

    fun checkCameraPermission() {
        if (!permissionBridge.isCameraPermissionGranted()) {
            permissionBridge.requestCameraPermission(object : PermissionResultCallback {
                override fun onPermissionGranted() {
                    updatePermission(true)
                }

                override fun onPermissionDenied(isPermanentDenied: Boolean) {
                    updatePermission(false)
                }
            })
        } else {
            updatePermission(true)
        }
    }

    private fun updatePermission(granted: Boolean) {
        _uiState.value = _uiState.value.copy(isCameraPermissionGranted = granted)
    }
}

