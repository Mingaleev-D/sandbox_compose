package com.example.sandbox_compose.ui.pages.home.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.TopAppBarState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.sandbox_compose.R
import com.example.sandbox_compose.ui.theme.Sandbox_composeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(
       modifier: Modifier = Modifier,
       openDrawer: () -> Unit,
       onSearch: () -> Unit,
       topAppBarState: TopAppBarState = rememberTopAppBarState(),
       scrollBehaviour: TopAppBarScrollBehavior = TopAppBarDefaults
           .enterAlwaysScrollBehavior(topAppBarState),
) {
    val title = "github.com/Mingaleev-D"
    CenterAlignedTopAppBar(
           title = {
               Text(
                      text = title,
                      style = MaterialTheme.typography.titleLarge.copy(
                             fontWeight = FontWeight.Bold,
                             fontStyle = FontStyle.Normal

                      ),
                      color = MaterialTheme.colorScheme.primary,
               )
//               Image(
//                      painter = painterResource(R.drawable.ic_newsy_water_mark),
//                      contentDescription = title,
//                      contentScale = ContentScale.Inside,
//                      colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
//                      modifier = Modifier.fillMaxWidth()
//               )
           },
           navigationIcon = {
               IconButton(onClick = openDrawer) {
                   Icon(
                          painterResource(R.drawable.ic_newsy_logo),
                          contentDescription = "navigation",
                          tint = MaterialTheme.colorScheme.primary
                   )
               }
           },
           actions = {
               IconButton(onClick = onSearch) {
                   Icon(
                          imageVector = Icons.Filled.Search,
                          contentDescription = "navigation",
                          tint = MaterialTheme.colorScheme.primary
                   )
               }
           }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun PrevHomeTopAppBar() {
    Sandbox_composeTheme {
        HomeTopAppBar(
               openDrawer = {},
               onSearch = {},
        )
    }
}
