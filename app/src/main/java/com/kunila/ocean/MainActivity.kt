package com.kunila.ocean

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kunila.ocean.presentation.navigation.Navigation
import com.kunila.ocean.presentation.theme.OceanAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OceanAppTheme {
                TabLayoutComposeUiMain()
            }
        }
        }

    @Composable
    fun TabLayoutComposeUiMain() {
        OceanAppTheme {
            Surface(color = MaterialTheme.colorScheme.background) {
                Navigation()
            }

        }
    }

    @Preview(showBackground = true)
    @Composable
    fun TabLayoutComposeUiMainPreview() {
        TabLayoutComposeUiMain()
    }
}