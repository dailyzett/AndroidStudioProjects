package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.model.AnimationInfo
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtworkDisplay(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ArtworkDisplay(modifier: Modifier = Modifier) {

    val animationInfoList: List<AnimationInfo> = AnimationInfo.list()
    val index = remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxSize(),
    ) {

        Box(
            modifier = Modifier.padding(
                start = 12.dp,
                end = 12.dp,
                top = 64.dp,
                bottom = 54.dp
            )
        ) {
            ArtworkCard(
                imageRes = animationInfoList[index.intValue].painterId,
                contentDescription = null,
            )
        }

        Box(
            modifier = Modifier
                .padding(
                    start = 12.dp,
                    end = 12.dp,
                )
                .background(color = Color.LightGray)
                .fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(animationInfoList[index.intValue].title),
                )

                Text(
                    text = stringResource(animationInfoList[index.intValue].description),
                )
            }
        }
        
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            ActionButton(
                onClick = {
                    if (index.intValue > 0) {
                        index.intValue--
                    } else {
                        index.intValue = animationInfoList.size - 1
                    }
                },
                modifier = Modifier
                    .width(150.dp),
                text = "Previous",
            )

            ActionButton(
                onClick = {
                    if (index.intValue < animationInfoList.size - 1) {
                        index.intValue++
                    } else {
                        index.intValue = 0
                    }
                },
                modifier = Modifier
                    .width(150.dp),
                text = "Next",
            )
        }
    }
}

@Composable
private fun ActionButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier
    ) {
        Text(text = text)
    }
}

@Composable
fun ArtworkCard(
    imageRes: Int,
    contentDescription: String?,
) {
    Box(
        modifier = Modifier
            .shadow(elevation = 4.dp, shape = RectangleShape)
            .background(Color.White)
            .padding(22.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(4f / 5f),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        ArtworkDisplay()
    }
}