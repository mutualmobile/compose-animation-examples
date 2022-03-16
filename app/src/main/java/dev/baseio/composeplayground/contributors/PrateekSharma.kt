package dev.baseio.composeplayground.contributors

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.baseio.composeplayground.R
import dev.baseio.composeplayground.ui.theme.Typography

const val prateekImageUrl = "https://pbs.twimg.com/profile_images/1496554615688425472/M6rm_jwG_normal.jpg"

@Composable
fun PrateekSharma(modifier: Modifier = Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier.padding(4.dp)) {
        CoilImageBox(Modifier.size(64.dp), prateekImageUrl)
        Column(verticalArrangement = Arrangement.Center, modifier = Modifier.padding(8.dp)) {
            Text(
                text = stringResource(id = R.string.prateek),
                style = Typography.h6.copy(MaterialTheme.colors.onSurface),
            )
            Text(
                text = stringResource(id = R.string.prateek_email),
                style = Typography.subtitle1.copy(MaterialTheme.colors.onSurface),
            )
        }
    }
}