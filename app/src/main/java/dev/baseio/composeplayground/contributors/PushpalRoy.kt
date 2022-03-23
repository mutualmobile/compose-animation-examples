package dev.baseio.composeplayground.contributors

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.baseio.composeplayground.R
import dev.baseio.composeplayground.ui.theme.Typography

const val pushpalRoyImageUrl = "https://ca.slack-edge.com/T02TLUWLZ-US7QYGQL9-2bba9d14df28-512"

@Composable
fun PushpalRoy(modifier: Modifier = Modifier) {
  Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier.padding(4.dp)) {
    CoilImageBox(Modifier.size(64.dp), pushpalRoyImageUrl)
    Column(verticalArrangement = Arrangement.Center, modifier = Modifier.padding(8.dp)) {
      Text(
        modifier = Modifier.padding(bottom = 6.dp),
        text = stringResource(id = R.string.emp_mmh0230),
        style = Typography.h6.copy(MaterialTheme.colors.onSurface),
      )
      Text(
        text = stringResource(id = R.string.emp_mmh0230_email),
        style = Typography.body2.copy(MaterialTheme.colors.onSurface),
        color = Color(0xFFCACACA)
      )
    }
  }
}

@Preview("Pushpal Roy Preview")
@Composable
fun PreviewPushpalRoy() {
  Surface {
    PushpalRoy()
  }
}