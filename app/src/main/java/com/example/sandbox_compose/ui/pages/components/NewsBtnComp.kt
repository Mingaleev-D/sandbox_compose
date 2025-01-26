package com.example.sandbox_compose.ui.pages.components

import android.content.res.Configuration
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sandbox_compose.ui.theme.Sandbox_composeTheme

@Composable
fun NewsBtnComp(
       modifier: Modifier = Modifier,
       text: String,
       onClick: () -> Unit
) {
    Button(
           onClick = onClick,
           colors = ButtonDefaults.buttonColors(
                  containerColor = MaterialTheme.colorScheme.primary,
                  contentColor = Color.White
           ),
           shape = RoundedCornerShape(size = 6.dp),
           modifier = modifier
    ) {
        Text(
               text = text,
               style = MaterialTheme.typography.labelMedium.copy(
                      fontWeight = FontWeight.SemiBold
               )
        )
    }

}

@Composable
fun NewsTextBtnComp(
       modifier: Modifier = Modifier,
       text: String,
       onClick: () -> Unit
) {
    TextButton(
           onClick = onClick,
           modifier = modifier,
    ) {
        Text(
               text = text,
               style = MaterialTheme.typography.labelMedium.copy(
                      fontWeight = FontWeight.SemiBold
               ),
               color = MaterialTheme.colorScheme.primary
        )
    }

}

@Preview(
       name = "Ночь-Night Mode",
       uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
       name = "День-Day Mode",
       uiMode = Configuration.UI_MODE_NIGHT_NO,
       showBackground = true
)
@Composable
private fun NewsBtnCompPreview() {
    Sandbox_composeTheme {
        NewsBtnComp(
               text = "Новости",
               onClick = {}
        )
    }

}
