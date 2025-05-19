package com.jmoriba.muscletrack.feature.posedetection.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.jmoriba.muscletrack.designsystem.component.camera.CameraView
import com.jmoriba.muscletrack.designsystem.component.title.ScreenTitle
import com.jmoriba.muscletrack.designsystem.theme.LightBackgroundAppColor
import com.jmoriba.muscletrack.designsystem.theme.spacingS
import com.jmoriba.muscletrack.domain.models.WorkoutModelUI
import com.jmoriba.muscletrack.feature.posedetection.presentation.PoseDetectionUiState


@Composable
fun PoseDetectionScreen(
    uiState: PoseDetectionUiState,
    onWorkoutClick: (WorkoutModelUI) -> Unit
) {
    Box(Modifier.background(LightBackgroundAppColor)) {
        Scaffold(containerColor = Color.Transparent) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
                    .imePadding()
                    .padding(horizontal = spacingS())
            ) {
                ScreenTitle("Pose Detection")
                if (uiState.isCameraPermissionGranted) {
                    CameraView()
                } else {
                    Text(text = "Necesitas otorgar permiso de cámara")
                }
            }
        }
    }
}

