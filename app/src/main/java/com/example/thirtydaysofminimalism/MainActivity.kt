package com.example.thirtydaysofminimalism

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thirtydaysofminimalism.color.ColorChanger
import com.example.thirtydaysofminimalism.data.TipsData
import com.example.thirtydaysofminimalism.model.Tip
import com.example.thirtydaysofminimalism.ui.theme.ThirtyDaysOfMinimalismTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThirtyDaysOfMinimalismTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ThirtyDaysOfMinimalismApp()
                }
            }
        }
    }
}

@Composable
fun ThirtyDaysOfMinimalismApp() {
    Scaffold(
        topBar = {
            TopBar()
        }
    ) {
        LazyColumn(
            modifier = Modifier.background(MaterialTheme.colors.background)
        ) {
            items(TipsData.tips) {
                TipItem(tip = it)
            }
        }
    }
}

@Composable
fun TopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colors.primary)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_logo),
            contentDescription = stringResource(R.string.app_name),
            modifier = Modifier
                .padding(8.dp)
                .size(48.dp),
            tint = MaterialTheme.colors.onPrimary
        )
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.h1
        )
    }
}

@Composable
fun TipItem(tip: Tip, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    val color by animateColorAsState(
        targetValue = if (expanded) ColorChanger(MaterialTheme.colors.surface).lighten(0.1f) else MaterialTheme.colors.surface
    )
    Card(
        modifier = modifier
            .padding(8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
                .background(color = color)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TipIcon(expanded)
                TipInformation(tip.name, tip.number)
                Spacer(Modifier.weight(1f))
                TipItemButton(expanded = expanded, onClick = { expanded = !expanded })
            }
            if (expanded) {
                TipDescription(tip.description)
            }
        }
    }
}

@Composable
fun TipIcon(expanded: Boolean, modifier: Modifier = Modifier) {
    Image(
        painter = if (expanded) painterResource(R.drawable.ic_tip_unfilled) else painterResource(R.drawable.ic_tip_filled),
        contentDescription = null,
        modifier = Modifier
            .height(24.dp)
            .padding(end = 10.dp
            )
    )
}

@Composable
fun TipInformation(@StringRes tipName: Int, tipNumber: Int,  modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .width(300.dp)
    ) {
        Text(
            text = stringResource(tipName),
            style = MaterialTheme.typography.h2,
            modifier = modifier.padding(top = 8.dp)
        )
        Text(
            text = stringResource(R.string.day_number, tipNumber),
            style = MaterialTheme.typography.body2
        )
    }
}

@Composable
private fun TipItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            tint = MaterialTheme.colors.secondary,
            contentDescription = stringResource(R.string.expand_button_content_description)
        )
    }
}

@Composable
fun TipDescription(@StringRes tipDescription: Int, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(
                start = 16.dp,
                top = 8.dp,
                bottom = 16.dp,
                end = 16.dp
            )
    ) {
        Text(
            text = stringResource(R.string.tip_description_title),
            style = MaterialTheme.typography.h3
        )
        Text(
            text = stringResource(tipDescription),
            style = MaterialTheme.typography.body2
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ThirtyDaysOfMinimalismTheme {
        ThirtyDaysOfMinimalismApp()
    }
}