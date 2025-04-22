package com.jmoriba.muscletrack.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.unit.dp

object Spacing {
    const val XXS = 4f
    const val XS = 8f
    const val S = 16f
    const val M = 24f
    const val L = 32f
    const val XL = 40f
    const val XXL = 48f
    const val ITEM = 100f
    const val MAX_BUTTON = 140f
    const val ICON_SIZE = 24f
}

@Composable
@ReadOnlyComposable
fun spacingXXS() = Spacing.XXS.dp
@Composable
@ReadOnlyComposable
fun spacingXS() = Spacing.XS.dp
@Composable
@ReadOnlyComposable
fun spacingS() = Spacing.S.dp
@Composable
@ReadOnlyComposable
fun spacingM() = Spacing.M.dp
@Composable
@ReadOnlyComposable
fun spacingL() = Spacing.L.dp
@Composable
@ReadOnlyComposable
fun spacingXL() = Spacing.XL.dp
@Composable
@ReadOnlyComposable
fun spacingXXL() = Spacing.XXL.dp
@Composable
@ReadOnlyComposable
fun itemSpacing() = Spacing.ITEM.dp
@Composable
@ReadOnlyComposable
fun maxButton() = Spacing.MAX_BUTTON.dp
@Composable
@ReadOnlyComposable
fun iconSize() = Spacing.ICON_SIZE.dp
