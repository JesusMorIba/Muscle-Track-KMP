package com.jmoriba.muscletrack.di

import com.jmoriba.muscletrack.data.repository.ReportRepository
import com.jmoriba.muscletrack.domain.repository.ReportRepositoryImpl
import com.jmoriba.muscletrack.feature.home.presentation.HomeViewModel
import com.jmoriba.muscletrack.feature.login.presentation.LoginViewModel
import com.jmoriba.muscletrack.feature.posedetection.presentation.PoseDetectionViewModel
import com.jmoriba.muscletrack.feature.report.presentation.ReportViewModel
import org.koin.dsl.module

fun previewModule() = module {
    single<ReportRepository> { ReportRepositoryImpl(get()) }
    factory { ReportViewModel(get()) }
    factory { LoginViewModel(get()) }
    factory { HomeViewModel(get()) }
    factory { PoseDetectionViewModel(get()) }
}