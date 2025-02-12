package com.jetpack.compose.learning.list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpack.compose.learning.theme.AppThemeState
import com.jetpack.compose.learning.theme.BaseView
import com.jetpack.compose.learning.theme.SystemUiController

class LazyGridActivity : ComponentActivity() {

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = remember { SystemUiController(window) }
            val appTheme = remember { mutableStateOf(AppThemeState()) }
            BaseView(appTheme.value, systemUiController) {
                MainContent()
            }
        }
    }

    @ExperimentalFoundationApi
    @Preview
    @Composable
    fun MainContent() {
        Scaffold(topBar = {
            TopAppBar(
                title = { Text("Grid") },
                navigationIcon = {
                    IconButton(onClick = { onBackPressed() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        }) {
            Column() {
                Text(
                    "LazyVerticalGrid :",
                    modifier = Modifier.padding(20.dp),
                    style = MaterialTheme.typography.h6
                )
                LazyGridExample()
            }
        }
    }

    @Composable
    fun LazyColumnExample() {
        val itemsList = (0..20).toList()
        LazyColumn(
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(itemsList.size) {
                ColumnItem(number = it)
            }
        }
    }

    @Composable
    fun ColumnItem(number: Int) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(MaterialTheme.colors.background)
                .border(1.dp, MaterialTheme.colors.primary),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(text = "Item Number $number", color = MaterialTheme.colors.primary)
        }
    }

    @ExperimentalFoundationApi
    @Composable
    fun LazyGridExample() {
        val itemsList = (0..29).toList()
        LazyVerticalGrid(
            // set number of items in a row
            cells = GridCells.Fixed(3),
            contentPadding = PaddingValues(10.dp)
        ) {
            items(itemsList.size) {
                RowItem(number = it)
            }
        }
    }

    @Composable
    fun RowItem(number: Int) {
        Row(
            modifier = Modifier
                .size(100.dp)
                .background(MaterialTheme.colors.background)
                .border(1.dp, MaterialTheme.colors.primary),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Item Number $number", color = MaterialTheme.colors.primary)
        }
    }
}