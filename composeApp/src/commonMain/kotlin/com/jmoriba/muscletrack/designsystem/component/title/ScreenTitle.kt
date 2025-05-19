package com.jmoriba.muscletrack.designsystem.component.title

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jmoriba.muscletrack.designsystem.theme.PrimaryColor
import muscletrack.composeapp.generated.resources.Res
import muscletrack.composeapp.generated.resources.ic_muscletrack
import org.jetbrains.compose.resources.painterResource

@Composable
fun ScreenTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 8.dp)
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_muscletrack),
            contentDescription = "MuscleTrack Icon",
            tint = PrimaryColor,
            modifier = Modifier
                .size(36.dp)
                .align(Alignment.CenterStart)
        )
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

