package com.jmoriba.muscletrack

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jmoriba.muscletrack.designsystem.component.navigation.NavigationBar
import com.jmoriba.muscletrack.designsystem.theme.PrimaryColor
import com.jmoriba.muscletrack.navigation.AppRoute
import com.jmoriba.muscletrack.navigation.Navigation
import moe.tlaster.precompose.navigation.rememberNavigator
import muscletrack.composeapp.generated.resources.Res
import muscletrack.composeapp.generated.resources.ic_muscletrack
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold() {
    val navigator = rememberNavigator()
    val currentRoute = navigator.currentEntry.collectAsState(null).value?.route?.route
    val currentAppRoute = AppRoute.fromRoute(currentRoute)

    Scaffold(
        topBar = {
            if (currentAppRoute?.title != null || currentAppRoute?.showBackButton == true) {
                CenterAlignedTopAppBar(
                    title = {
                        Box(contentAlignment = Alignment.Center) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(Res.drawable.ic_muscletrack),
                                    contentDescription = "MuscleTrack Icon",
                                    tint = PrimaryColor,
                                    modifier = Modifier.size(28.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = currentAppRoute.title.orEmpty(),
                                    style = MaterialTheme.typography.headlineMedium
                                )
                            }
                        }
                    },
                    navigationIcon = {
                        if (currentAppRoute.showBackButton) {
                            IconButton(onClick = { navigator.goBack() }) {
                                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                            }
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Transparent,
                        titleContentColor = MaterialTheme.colorScheme.onBackground,
                        navigationIconContentColor = MaterialTheme.colorScheme.onBackground
                    )
                )
            }
        },
        bottomBar = {
            if (currentAppRoute?.showBottomBar == true) {
                NavigationBar(navigator = navigator)
            }
        },
        floatingActionButton = {
            if (currentAppRoute?.showFloatingButton == true) {
                IconButton(
                    onClick = { navigator.goBack() },
                    modifier = Modifier
                        .size(48.dp)
                        .background(color = PrimaryColor, shape = CircleShape),
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = Color.White
                    )
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Back")
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(
                    top = 64.dp,
                    bottom = innerPadding.calculateBottomPadding()
                )
        ) {
            Navigation(navigator)
        }
    }
}
