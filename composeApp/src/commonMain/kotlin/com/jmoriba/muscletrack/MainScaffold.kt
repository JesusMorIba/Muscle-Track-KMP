package com.jmoriba.muscletrack

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import com.jmoriba.muscletrack.navigation.AppRoutes
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
    val currentAppRoutes = AppRoutes.fromRoute(currentRoute)

    Scaffold(
        topBar = {
            currentAppRoutes?.let { route ->
                if (route.title != null || route.showBackButton) {
                    CenterAlignedTopAppBar(
                        title = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(Res.drawable.ic_muscletrack),
                                    contentDescription = null,
                                    tint = PrimaryColor,
                                    modifier = Modifier.size(28.dp)
                                )
                                Spacer(Modifier.width(8.dp))
                                Text(
                                    text = route.title.orEmpty(),
                                    style = MaterialTheme.typography.headlineMedium
                                )
                            }
                        },
                        navigationIcon = {
                            if (route.showBackButton) {
                                IconButton(onClick = { navigator.goBack() }) {
                                    Icon(
                                        Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Volver"
                                    )
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
            }
        },
        bottomBar = {
            if (currentAppRoutes?.showBottomBar == true) {
                NavigationBar(navigator)
            }
        },
        floatingActionButton = {
            currentAppRoutes?.fabAction?.let { action ->
                FloatingActionButton(
                    onClick = { action(navigator) },
                    containerColor = PrimaryColor,
                    contentColor = Color.White
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(
                top = 64.dp,
                bottom = innerPadding.calculateBottomPadding()
            )
        ) {
            Navigation(navigator)
        }
    }
}
