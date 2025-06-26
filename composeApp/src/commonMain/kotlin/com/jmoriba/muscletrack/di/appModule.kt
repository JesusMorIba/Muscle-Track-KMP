package com.jmoriba.muscletrack.di

import com.jmoriba.muscletrack.data.repository.AuthRepository
import com.jmoriba.muscletrack.data.repository.DashboardRepository
import com.jmoriba.muscletrack.data.repository.ExerciseDetailRepository
import com.jmoriba.muscletrack.data.repository.ExerciseRepository
import com.jmoriba.muscletrack.data.repository.WorkoutRepository
import com.jmoriba.muscletrack.domain.repository.AuthRepositoryImpl
import com.jmoriba.muscletrack.domain.repository.DashboardRepositoryImpl
import com.jmoriba.muscletrack.domain.repository.ExerciseRepositoryImpl
import com.jmoriba.muscletrack.domain.repository.WorkoutRepositoryImpl
import com.jmoriba.muscletrack.domain.repository.ExerciseDetailRepositoryImpl
import com.jmoriba.muscletrack.utils.PermissionBridge
import com.jmoriba.muscletrack.feature.workoutdetail.presentation.ReportDetailViewModel
import com.jmoriba.muscletrack.feature.dashboard.presentation.DashboardViewModel
import com.jmoriba.muscletrack.feature.exercise.presentation.ExerciseViewModel
import com.jmoriba.muscletrack.feature.exercisedetail.presentation.ExerciseDetailViewModel
import com.jmoriba.muscletrack.feature.auth.presentation.AuthViewModel
import com.jmoriba.muscletrack.feature.posedetection.presentation.PoseDetectionViewModel
import com.jmoriba.muscletrack.feature.workout.presentation.WorkoutViewModel
import org.koin.dsl.module

fun appModule() = module {
    single<WorkoutRepository> { WorkoutRepositoryImpl(get()) }
    single<DashboardRepository> { DashboardRepositoryImpl(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<ExerciseRepository> { ExerciseRepositoryImpl(get()) }
    single<ExerciseDetailRepository> { ExerciseDetailRepositoryImpl(get()) }
    single { PermissionBridge() }
    factory { AuthViewModel(get()) }
    factory { WorkoutViewModel(get()) }
    factory { ReportDetailViewModel(get()) }
    factory { ExerciseViewModel(get()) }
    factory { ExerciseDetailViewModel(get()) }
    factory { DashboardViewModel(get()) }
    factory { PoseDetectionViewModel(get()) }
}