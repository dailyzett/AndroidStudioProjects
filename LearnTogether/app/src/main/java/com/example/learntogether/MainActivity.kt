package com.example.learntogether

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learntogether.ui.theme.LearnTogetherTheme
import java.nio.file.WatchEvent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LearnTogetherTheme {
                Scaffold { innerPadding ->
                    BackgroundImage(
                        modifier = Modifier.padding(innerPadding),
                        subject = stringResource(R.string.subject),
                        secondParagraph = stringResource(R.string.secondParagraph),
                        thirdParagraph = stringResource(R.string.secondParagraph),
                    )
                }
            }
        }
    }
}

@Composable
fun BackgroundImage(
    modifier: Modifier = Modifier,
    subject: String,
    secondParagraph: String,
    thirdParagraph: String,
) {
    val image = painterResource(R.drawable.bg_compose_background)
    Column(modifier) {
        Image(
            painter = image,
            contentDescription = null,
        )

        Text(
            text = subject,
            fontSize = 24.sp,
            modifier = Modifier.padding(all = 16.dp)
        )

        Text(
            text = secondParagraph,
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
            ),
            textAlign = TextAlign.Justify,
        )

        Text(
            text = thirdParagraph,

            modifier = Modifier.padding(all = 16.dp),
            textAlign = TextAlign.Justify,
        )
    }
}

@Preview(showBackground = false)
@Composable
fun BackGroundImagePreview() {
    LearnTogetherTheme {
        BackgroundImage(
            subject = stringResource(R.string.subject),
            secondParagraph = stringResource(R.string.secondParagraph),
            thirdParagraph = stringResource(R.string.secondParagraph),
        )
    }
}

