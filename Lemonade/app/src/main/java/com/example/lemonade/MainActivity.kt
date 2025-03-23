package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonadeImage(
    modifier: Modifier = Modifier,
) {

    var currentStep by remember { mutableIntStateOf(1) }
    var squeezeCount by remember { mutableIntStateOf(0) }

    val image = when (currentStep) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val textUnderImage = when (currentStep) {
        1 -> R.string.tab_lemon
        2 -> R.string.keep_tab
        3 -> R.string.tab_lemonade
        else -> R.string.tab_empty
    }

    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Button(
            shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
            onClick = {
                when (currentStep) {
                    1 -> {
                        currentStep = 2
                        squeezeCount = (2..4).random()
                    }

                    2 -> {
                        squeezeCount--
                        if (squeezeCount == 0) {
                            currentStep = 3
                        }
                    }

                    3 -> currentStep = 4
                    else -> currentStep = 1
                }
            }
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = stringResource(R.string.lemon_description),
            )
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(
            text = stringResource(textUnderImage),
            fontSize = 18.sp,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
@Preview(showBackground = false)
fun LemonadeApp(
    modifier: Modifier = Modifier
) {
    LemonadeImage(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}