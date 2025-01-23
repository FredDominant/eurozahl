package com.freddominant.eurozahl.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.freddominant.eurozahl.ui.state.LotteryUiEvents
import com.freddominant.eurozahl.ui.theme.EurozahlTheme
import com.freddominant.eurozahl.ui.viewmodel.EurozahlViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: EurozahlViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            EurozahlTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val state = viewModel.lotteryEvents.collectAsState(
                        LotteryUiEvents.ShowProgress
                    )
                    LotteryResult(
                        modifier = Modifier.padding(innerPadding),
                        state = state
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getLotteryResult()
    }
}

@Composable
fun LotteryResult(modifier: Modifier = Modifier, state: State<LotteryUiEvents>) {
    Text("Hello World :) ")
}


@Preview(showBackground = true)
@Composable
fun LotteryResultPreview() {

}