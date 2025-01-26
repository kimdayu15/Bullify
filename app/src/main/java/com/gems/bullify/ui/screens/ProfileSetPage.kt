package com.gems.bullify.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun ProfileSetPage() {
    Scaffold() { innerPadding ->
        val gradient = Brush.verticalGradient(listOf(Color(0xFFFFEB3B),Color.White, Color(0x320019FF) ))
        Box(modifier = Modifier.fillMaxSize().background(gradient)) {
            Column(modifier = Modifier.padding(innerPadding)) {

                Text("Set image profile")
//                val profile = remember { painterResource() }
//
//                Image(painter = painterResource(profile))

                Button(onClick = {}) {
                    Text("Set")
                }
                Button(onClick = {}) {
                    Text("Skip")
                }


            }
        }

    }

}