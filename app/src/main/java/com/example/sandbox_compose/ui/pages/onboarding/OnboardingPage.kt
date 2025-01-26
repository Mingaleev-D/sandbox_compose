package com.example.sandbox_compose.ui.pages.onboarding

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sandbox_compose.R
import com.example.sandbox_compose.domain.model.OnboardUiPageModel
import com.example.sandbox_compose.domain.model.pages
import com.example.sandbox_compose.ui.pages.components.Dimens
import com.example.sandbox_compose.ui.pages.components.NewsBtnComp
import com.example.sandbox_compose.ui.pages.components.NewsTextBtnComp
import com.example.sandbox_compose.ui.pages.components.PageIndicator
import com.example.sandbox_compose.ui.theme.Sandbox_composeTheme
import kotlinx.coroutines.launch

@Composable
fun OnboardingPage(
       onEvent: (OnboardingEvent) -> Unit
) {
    Column(
           modifier = Modifier.fillMaxSize(),
    ) {
        val pagerState = rememberPagerState(initialPage = 0) { pages.size }
        val btnState = remember {
            derivedStateOf {
                when (pagerState.currentPage) {
                    0 -> listOf("", "Next")
                    1 -> listOf("Back", "Next")
                    2 -> listOf("Back", "Get started")
                    else -> listOf("", "")
                }
            }
        }

        HorizontalPager(state = pagerState) { index ->
            OnboardingPageContent(
                   pagesOnBoard = pages[index],
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(horizontal = Dimens.MediumPadding2)
                   .navigationBarsPadding(),
               horizontalArrangement = Arrangement.SpaceBetween,
               verticalAlignment = Alignment.CenterVertically
        ) {
            PageIndicator(
                   pagesSize = pages.size,
                   selectedPage = pagerState.currentPage,
                   modifier = Modifier.width(Dimens.PageIndicatorSize),
            )

            Row(
                   verticalAlignment = Alignment.CenterVertically,
            ) {
                val scope = rememberCoroutineScope()
                if (btnState.value[0].isNotEmpty()) {
                    NewsTextBtnComp(
                           text = btnState.value[0],
                           onClick = {
                               scope.launch {
                                   pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                               }
                           }
                    )
                }

                NewsBtnComp(
                       text = btnState.value[1],
                       onClick = {
                           scope.launch {
                               if (pagerState.currentPage == 2) {
                                   onEvent(OnboardingEvent.SaveAppEntry)
                               } else {
                                   pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                               }
                           }
                       }
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
private fun OnboardingPageContent(
       modifier: Modifier = Modifier,
       pagesOnBoard: OnboardUiPageModel
) {
    Column(
           modifier = modifier,
    ) {
        Image(
               painter = painterResource(id = pagesOnBoard.image),
               contentDescription = null,
               contentScale = ContentScale.Crop,
               modifier = Modifier
                   .fillMaxWidth()
                   .fillMaxHeight(fraction = .6f),
        )
        Spacer(modifier = Modifier.height(Dimens.MediumPadding1))
        Text(
               text = pagesOnBoard.title,
               style = MaterialTheme.typography.displaySmall.copy(
                      fontWeight = FontWeight.Bold,
               ),
               color = colorResource(R.color.display_small),
               modifier = Modifier.padding(horizontal = Dimens.MediumPadding2),
        )
        Text(
               text = pagesOnBoard.description,
               style = MaterialTheme.typography.bodySmall.copy(
               ),
               color = colorResource(R.color.text_medium),
               modifier = Modifier.padding(horizontal = Dimens.MediumPadding2),
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
private fun OnboardingPagePreview() {
    Sandbox_composeTheme {
        OnboardingPageContent(
               pagesOnBoard = pages[0]
        )
    }

}
