package com.jmoriba.muscletrack.feature.exercise.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.jmoriba.muscletrack.designsystem.component.searchbar.SearchBar
import com.jmoriba.muscletrack.designsystem.component.card.ExerciseCard
import com.jmoriba.muscletrack.designsystem.component.selector.CustomSelector
import com.jmoriba.muscletrack.designsystem.theme.LightBackgroundAppColor
import com.jmoriba.muscletrack.designsystem.theme.spacingS
import com.jmoriba.muscletrack.di.previewModule
import com.jmoriba.muscletrack.feature.exercise.presentation.ExerciseViewModel
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.koin.koinViewModel
import muscletrack.composeapp.generated.resources.Res
import muscletrack.composeapp.generated.resources.ic_equipment
import muscletrack.composeapp.generated.resources.ic_muscle_group
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExerciseScreen(viewModel : ExerciseViewModel, onExerciseClick: (String) -> Unit) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Box(Modifier.background(LightBackgroundAppColor)) {
        Scaffold(
            containerColor = Color.Transparent
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
                    .imePadding()
                    .padding(horizontal = spacingS())
            ) {
                SearchBar(
                    query = uiState.searchQuery,
                    onQueryChange = { viewModel.onSearchQueryChanged(it) },
                    placeholderText = "Search exercises..."
                )

                Spacer(modifier = Modifier.height(spacingS()))

                /*
                CustomSelector(
                    selectedItem = uiState.selectedMuscleGroup,
                    items = listOf<MuscleGroup?>(null) + MuscleGroup.entries,
                    onItemSelected = viewModel::onMuscleGroupSelected,
                    placeholder = "Select muscle group",
                    leadingIcon = Res.drawable.ic_muscle_group,
                    itemDisplayText = { it?.displayName ?: "All muscle groups" }
                )*/

                Spacer(modifier = Modifier.height(spacingS()))

                /*
                CustomSelector(
                    selectedItem = uiState.selectedEquipment,
                    items = listOf<Equipment?>(null) + Equipment.entries,
                    onItemSelected = viewModel::onEquipmentSelected,
                    placeholder = "Select equipment",
                    leadingIcon = Res.drawable.ic_equipment,
                    itemDisplayText = { it?.displayName ?: "All equipment" }
                )*/

                Spacer(modifier = Modifier.height(spacingS()))

                ExerciseCard(
                    exercises = uiState.filteredExercises,
                    onViewDetails = { exercise -> onExerciseClick(exercise.id) },
                    onDelete = {}
                )

                Spacer(modifier = Modifier.height(spacingS()))
            }
        }
    }
}

@Preview
@Composable
fun ExerciseScreenPreview() {
    PreComposeApp {
        KoinApplication(application = {
            modules(previewModule())
        }) {
            val viewModel = koinViewModel<ExerciseViewModel>()
            ExerciseScreen(viewModel = viewModel) {}
        }
    }
}
