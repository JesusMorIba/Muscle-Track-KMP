package com.jmoriba.muscletrack.di

import com.jmoriba.muscletrack.network.repository.WorkoutRepository
import com.jmoriba.muscletrack.domain.repository.WorkoutRepositoryImpl
import com.jmoriba.muscletrack.feature.auth.presentation.AuthViewModel
import com.jmoriba.muscletrack.feature.dashboard.presentation.DashboardViewModel
import com.jmoriba.muscletrack.feature.posedetection.presentation.PoseDetectionViewModel
import com.jmoriba.muscletrack.feature.workout.presentation.WorkoutViewModel
import org.koin.dsl.module

fun previewModule() = module {
    single<WorkoutRepository> { WorkoutRepositoryImpl(get()) }
    factory { WorkoutViewModel(get()) }
    factory { AuthViewModel(get()) }
    factory { DashboardViewModel(get()) }
    factory { PoseDetectionViewModel(get()) }
}