package com.jmoriba.muscletrack.di

import com.jmoriba.muscletrack.domain.repository.*
import com.jmoriba.muscletrack.feature.auth.presentation.AuthViewModel
import com.jmoriba.muscletrack.feature.dashboard.presentation.DashboardViewModel
import com.jmoriba.muscletrack.feature.exercise.presentation.ExerciseViewModel
import com.jmoriba.muscletrack.feature.exercisedetail.presentation.ExerciseDetailViewModel
import com.jmoriba.muscletrack.feature.posedetection.presentation.PoseDetectionViewModel
import com.jmoriba.muscletrack.feature.workout.presentation.WorkoutViewModel
import com.jmoriba.muscletrack.network.api.HttpClientProvider
import com.jmoriba.muscletrack.network.api.TokenProvider
import com.jmoriba.muscletrack.network.repository.AuthRepository
import com.jmoriba.muscletrack.network.repository.DashboardRepository
import com.jmoriba.muscletrack.network.repository.ExerciseDetailRepository
import com.jmoriba.muscletrack.network.repository.ExerciseRepository
import com.jmoriba.muscletrack.network.repository.WorkoutRepository
import com.jmoriba.muscletrack.common.utils.PermissionBridge
import com.jmoriba.muscletrack.feature.workoutdetail.presentation.WorkoutDetailViewModel
import org.koin.dsl.module

fun appModule() = module {

    // Utilities
    single { PermissionBridge() }
    single { TokenProvider() }
    single { HttpClientProvider(get()) }

    // Data repositories
    single<WorkoutRepository> { WorkoutRepositoryImpl(get()) }
    single<DashboardRepository> { DashboardRepositoryImpl(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<ExerciseRepository> { ExerciseRepositoryImpl(get()) }
    single<ExerciseDetailRepository> { ExerciseDetailRepositoryImpl(get()) }

    // ViewModels
    factory { AuthViewModel(get()) }
    factory { WorkoutViewModel(get()) }
    factory { WorkoutDetailViewModel(get()) }
    factory { ExerciseViewModel(get()) }
    factory { ExerciseDetailViewModel(get()) }
    factory { DashboardViewModel(get()) }
    factory { PoseDetectionViewModel(get()) }
}
