package ru.z3rg.hotels.ui.screens.share

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.z3rg.hotels.R
import ru.z3rg.hotels.ui.theme.GrayText

@Preview(backgroundColor = 0xFFFEFEFE, showBackground = true)
@Composable
fun ImageViewerPreview(
    images: List<Painter> = listOf(
        painterResource(id = R.drawable.hotel_paceholder),
        painterResource(id = R.drawable.hotel_paceholder),
        painterResource(id = R.drawable.hotel_paceholder),
        painterResource(id = R.drawable.hotel_paceholder),
        painterResource(id = R.drawable.hotel_paceholder)
    )
) {
    ImagesViewer(
        images = images
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImagesViewer(
    modifier: Modifier = Modifier,
    images: List<Painter>
) {
    val pagerState = rememberPagerState()

    Box(
        modifier = modifier
    ) {
        HorizontalPager(
            pageCount = images.size,
            state = pagerState
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth(0.92f)
                    .height(258.dp)
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Crop,
                painter = images[it],
                contentDescription = "Hotel Image"
            )
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
                repeat(images.size) {
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