package com.example.w04

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.w04.ui.theme.W202510996Theme

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

data class Message(val author: String, val body: String)
data class Profile(val name: String, val intro: String)
data class Department(val name: String, val department: String)

@Composable
fun HomeScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        // Column을 사용하여 카드를 세로로 배치
        Column(
            modifier = Modifier.fillMaxSize(),  // 전체 화면을 차지하도록 설정
            verticalArrangement = Arrangement.Center, // 가운데 정렬
            horizontalAlignment = Alignment.CenterHorizontally // 수평 가운데 정렬
        ) {
            // ProfileCard와 MessageCard를 화면에 아래로 배치
            ProfileCard(Profile("김준수", "우송대학교 재학생"))
            Spacer(modifier = Modifier.height(20.dp)) // 카드 사이에 간격 추가
            DepartmentCard(Department("컴퓨터 공학과", "학과: 컴퓨터 공학과"))
        }
    }
}

@Composable
fun MessageCard(msg: Message) {
    Row(
        modifier = Modifier.padding(all = 8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground), // 임시 아이콘 사용
            contentDescription = "연락처 프로필 사진",
            modifier = Modifier.size(60.dp).clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp)) // 이미지와 텍스트 사이에 수평 간격을 추가합니다.
        Column {
            Text(
                text = msg.author,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = msg.body,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun ProfileCard(data: Profile) {
    Row(
        modifier = Modifier.padding(all = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.img), // 임시 아이콘 사용
            contentDescription = "연락처 프로필 사진",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = data.name,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = data.intro,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun DepartmentCard(data: Department) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(), // 카드를 화면 너비에 맞게
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = data.name,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = data.department,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Preview(
    name = "Profile Card Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)



@Composable
fun PreviewDepartmentCard() {
    W202510996Theme {
        DepartmentCard(Department("김준수", "학과: 컴퓨터 공학과"))
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    W202510996Theme {
        HomeScreen()
    }
}
