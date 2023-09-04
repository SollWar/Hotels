package ru.z3rg.hotels.ui.screens.share

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.z3rg.hotels.ui.theme.GrayText

@Preview(backgroundColor = 0xFFFEFEFE, showBackground = true)
@Composable
fun ImageViewerPreview(
    imagesUrls: List<String> = listOf(
        "https://www.atorus.ru/sites/default/files/upload/image/News/56149/Club_Priv%C3%A9_by_Belek_Club_House.jpg",
        "https://deluxe.voyage/useruploads/articles/The_Makadi_Spa_Hotel_02.jpg",
        "https://deluxe.voyage/useruploads/articles/article_1eb0a64d00.jpg"
    )
) {
    ImagesViewer(
        imagesUrls = imagesUrls
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImagesViewer(
    modifier: Modifier = Modifier,
    imagesUrls: List<String>
) {
    val pagerState = rememberPagerState { imagesUrls.size }

    Box(
        modifier = modifier
    ) {
        HorizontalPager(
            state = pagerState
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.width(1.dp))
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth(0.92f)
                        .height(258.dp)
                        .clip(RoundedCornerShape(20.dp)),
                    contentScale = ContentScale.Crop,
                    model = imagesUrls[it],
                    contentDescription = "Hotel Image"
                )
                Spacer(modifier = Modifier.width(1.dp))
            }

        }
        Box(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(Color.White)
                .height(17.dp)
                .align(alignment = Alignment.BottomCenter)
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
            ) {
                repeat(imagesUrls.size) {
                    val color = if (pagerState.currentPage == it) Color.Black else GrayText
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 5.dp, vertical = 5.dp)
                            .clip(CircleShape)
                            .background(color)
                            .width(7.dp)
                            .height(7.dp)
                    )
                }
            }
        }
    }
}