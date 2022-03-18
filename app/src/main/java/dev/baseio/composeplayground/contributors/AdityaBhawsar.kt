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

const val adityaImageUrl = "https://ca.slack-edge.com/T02TLUWLZ-U02CDNRLMSA-4aa8ad906c93-72"

@Composable
fun AdityaBhawsar(modifier: Modifier = Modifier) {
  Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier.padding(4.dp)) {
    CoilImageBox(Modifier.size(64.dp), adityaImageUrl)
    Column(verticalArrangement = Arrangement.Center, modifier = Modifier.padding(8.dp)) {
      Text(
        text = stringResource(id = R.string.emp_mmh0329),
        style = Typography.h6.copy(MaterialTheme.colors.onSurface),
      )
      Text(
        text = stringResource(id = R.string.emp_mmh0329_email),
        style = Typography.subtitle1.copy(MaterialTheme.colors.onSurface),
      )
    }
  }
}