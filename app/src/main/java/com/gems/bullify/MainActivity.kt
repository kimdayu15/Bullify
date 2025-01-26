package com.gems.bullify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.gems.bullify.navigation.AppNavHost
import com.gems.bullify.ui.theme.BullifyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BullifyTheme {
                AppNavHost(navController = rememberNavController())
            }
        }
    }
}
