package com.example.w05

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // 랜덤 목표 시간 1~20초, 1~99밀리초
            val randomSeconds = remember { Random.nextInt(1, 21) }
            val randomMillis = remember { Random.nextInt(1, 100) }

            // 시작 횟수 카운트
            var startCount by remember { mutableStateOf(0) }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "시작 횟수: $startCount",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                StopWatchApp(
                    targetSeconds = randomSeconds,
                    targetMillis = randomMillis,
                    startCount = startCount,
                    onStartCountChange = { startCount = it }
                )
            }
        }
    }
}

@Composable
fun StopWatchApp(
    targetSeconds: Int,
    targetMillis: Int,
    startCount: Int,
    onStartCountChange: (Int) -> Unit
) {
    var timeInMillis by remember { mutableStateOf(0L) }
    var isRunning by remember { mutableStateOf(false) }
    var resultMessage by remember { mutableStateOf("") }
    var backgroundColor by remember { mutableStateOf(Color.White) }

    val targetTimeMillis = (targetSeconds * 1000L) + (targetMillis * 10L)
    val tolerance = 100L // 오차 범위 00:10

    LaunchedEffect(isRunning) {
        if (isRunning) {
            resultMessage = ""
            backgroundColor = Color.White
            while (isRunning) {
                delay(10L)
                timeInMillis += 10L
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            StopwatchScreen(
                timeInMillis = timeInMillis,
                targetSeconds = targetSeconds,
                targetMillis = targetMillis,
                resultMessage = resultMessage,
                tolerance = tolerance,
                onStartClick = {
                    if (!isRunning) {
                        onStartCountChange(startCount + 1)
                        timeInMillis = 0L
                        resultMessage = ""
                        backgroundColor = Color.White
                        isRunning = true
                    }
                },
                onStopClick = {
                    isRunning = false
                    val isSuccess =
                        timeInMillis in (targetTimeMillis - tolerance)..(targetTimeMillis + tolerance)
                    resultMessage = if (isSuccess)
                        "성공했습니다! "
                    else
                        "실패했습니다! "
                    backgroundColor = if (isSuccess) Color(0xFFB9F6CA) else Color(0xFFFF8A80)
                }
            )
        }
    }
}

@Composable
fun StopwatchScreen(
    timeInMillis: Long,
    targetSeconds: Int,
    targetMillis: Int,
    resultMessage: String,
    tolerance: Long,
    onStartClick: () -> Unit,
    onStopClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "목표 시간: ${targetSeconds}초:${String.format("%02d", targetMillis)} (오차범위 00:10)",
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = formatTime(timeInMillis),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Button(onClick = onStartClick) { Text("Start") }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = onStopClick) { Text("Stop") }
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (resultMessage.isNotEmpty()) {
            Text(
                text = resultMessage,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = if (resultMessage.contains("성공")) Color(0xFF2E7D32)
                else Color(0xFFC62828)
            )
        }
    }
}

private fun formatTime(timeInMillis: Long): String {
    val seconds = timeInMillis / 1000
    val millis = (timeInMillis % 1000) / 10
    return String.format("%02d:%02d", seconds, millis)
}
