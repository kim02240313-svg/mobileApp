package com.example.w03

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w03.ui.theme.W202510996Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            W202510996Theme {
                HomeScreen()
            }
        }
    }
}

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD)) // 밝은 하늘색 배경
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Text(
            text = "제주도 여행 가이드",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color(0xFF0D47A1)
            ),
            modifier = Modifier.padding(vertical = 8.dp)
        )


        Image(
            painter = painterResource(id = R.drawable.ji),
            contentDescription = "한라산 이미지",
            modifier = Modifier
                .size(240.dp)
                .padding(bottom = 8.dp)
        )


        Text(
            "한라산을 중심으로 이루어진",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF1E88E5)
            )
        )
        Text(
            "대한민국의 가장 큰 섬",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 20.sp
            ),
            modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
        )


        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = "제주 풍경 이미지",
            modifier = Modifier
                .size(280.dp)
                .padding(bottom = 8.dp)
        )


        Text(
            "제주도로 놀러오세요!",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(0xFFD81B60)
            )
        )


        Spacer(modifier = Modifier.weight(1f))


        Button(
            onClick = {
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1E88E5),
                contentColor = Color.White
            ),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                text = "표 예약하기",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    W202510996Theme {
        HomeScreen()
    }
}
