package com.jetpack.compose.learning.tabarlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpack.compose.learning.theme.AppThemeState
import com.jetpack.compose.learning.theme.BaseView
import com.jetpack.compose.learning.theme.SystemUiController

class TabBarLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = remember { SystemUiController(window) }
            val appTheme = remember { mutableStateOf(AppThemeState()) }
            BaseView(appTheme.value, systemUiController) {
                tabBarSample()
            }
        }
    }

    @Preview
    @Composable
    fun tabBarSample() {
        val spaceHeight = 50.dp
        Column {
            Spacer(modifier = Modifier.height(60.dp))
            //simple text tabBar
            Row {
                var tabIndex by remember { mutableStateOf(0) }
                val tabData = listOf(
                    "C#",
                    "Python",
                    "Swift",
                    "Kotlin",
                )
                TabRow(selectedTabIndex = tabIndex) {
                    tabData.forEachIndexed { index, text ->
                        Tab(selected = tabIndex == index, onClick = {
                            tabIndex = index
                        }, text = {
                            Text(text = text)
                        })
                    }
                }
            }
            Spacer(Modifier.requiredHeight(spaceHeight))
            //simple Icon tabBar with indicator
            Row {
                var tabIndex by remember { mutableStateOf(0) }
                //simple icon tabBar
                val tabIcon = listOf(
                    Icons.Filled.Home,
                    Icons.Filled.Favorite,
                    Icons.Filled.Person,
                    Icons.Filled.Settings,
                )
                TabRow(selectedTabIndex = tabIndex,
                    indicator = { tabPositions ->
                        TabRowDefaults.Indicator(
                            modifier = Modifier.indicatorSetUp(tabPositions[tabIndex]),
                            height = 5.dp,
                            color = Color.DarkGray
                        )
                    }) {
                    tabIcon.forEachIndexed { index, icon ->
                        Tab(selected = tabIndex == index, onClick = {
                            tabIndex = index
                        }, icon = {
                            Icon(imageVector = icon, contentDescription = null)
                        })
                    }
                }
            }
            Spacer(Modifier.requiredHeight(spaceHeight))
            //tabbar with text,icon,indicator and divider
            Row {
                var tabIndex by remember { mutableStateOf(0) }
                val tabData = listOf(
                    "HOME" to Icons.Filled.Home,
                    "GAME" to Icons.Filled.Games,
                    "USER" to Icons.Filled.Person,
                    "SETTING" to Icons.Filled.Settings,
                )
                TabRow(selectedTabIndex = tabIndex,
                    divider = {
                        TabRowDefaults.Divider(
                            thickness = 5.dp,
                            color = MaterialTheme.colors.primaryVariant
                        )
                    },
                    indicator = { tabPositions ->
                        TabRowDefaults.Indicator(
                            modifier = Modifier.indicatorSetUp(tabPositions[tabIndex]),
                            height = 5.dp,
                            color = Color.White
                        )
                    }) {
                    tabData.forEachIndexed { index, pair ->
                        Tab(selected = tabIndex == index, onClick = {
                            tabIndex = index
                        }, text = {
                            Text(text = pair.first)
                        }, icon = {
                            Icon(imageVector = pair.second, contentDescription = null)
                        })
                    }
                }
            }
            Spacer(Modifier.requiredHeight(spaceHeight))
            //scrollable icon tabbar
            Row {
                var tabIndex by remember { mutableStateOf(0) }
                //simple icon tabBar
                val tabIcon = listOf(
                    Icons.Filled.Home,
                    Icons.Filled.Favorite,
                    Icons.Filled.GetApp,
                    Icons.Filled.AddAPhoto,
                    Icons.Filled.Headset,
                    Icons.Filled.Person,
                    Icons.Filled.Settings,
                )
                ScrollableTabRow(selectedTabIndex = tabIndex) {
                    tabIcon.forEachIndexed { index, icon ->
                        Tab(selected = tabIndex == index, onClick = {
                            tabIndex = index
                        }, icon = {
                            Icon(imageVector = icon, contentDescription = null)
                        })
                    }
                }
            }
            Spacer(Modifier.requiredHeight(spaceHeight))
            //scrollable tabbar with text,icon and divider
            Row {
                var tabIndex by remember { mutableStateOf(0) }
                val tabData = listOf(
                    "HOME" to Icons.Filled.Home,
                    "FAVORITE" to Icons.Filled.Favorite,
                    "DOWNLOAD" to Icons.Filled.GetApp,
                    "PICTURES" to Icons.Filled.AddAPhoto,
                    "MUSIC" to Icons.Filled.Headset,
                    "USER" to Icons.Filled.Person,
                    "SETTING" to Icons.Filled.Settings,
                )
                ScrollableTabRow(selectedTabIndex = tabIndex,
                    divider = {
                        TabRowDefaults.Divider(
                            thickness = 5.dp,
                            color = MaterialTheme.colors.primaryVariant
                        )
                    }) {
                    tabData.forEachIndexed { index, pair ->
                        Tab(selected = tabIndex == index, onClick = {
                            tabIndex = index
                        }, text = {
                            Text(text = pair.first)
                        }, icon = {
                            Icon(imageVector = pair.second, contentDescription = null)
                        })
                    }
                }
            }
        }
    }

    private fun Modifier.indicatorSetUp(currentTabPosition: TabPosition): Modifier = composed(
        inspectorInfo = debugInspectorInfo {
            name = "tabIndicator"
            value = currentTabPosition
        }
    ) {
        val indicatorWidth = 32.dp
        val currentTabWidth = currentTabPosition.width
        val indicatorOffset by animateDpAsState(
            targetValue = currentTabPosition.left + currentTabWidth / 2 - indicatorWidth / 2,
            animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
        )
        fillMaxWidth()
            .wrapContentSize(Alignment.BottomStart)
            .offset(x = indicatorOffset)
            .width(indicatorWidth)
    }
}