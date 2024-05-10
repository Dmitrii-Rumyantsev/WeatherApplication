package com.example.weather.presentation

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R
import com.example.weather.locations.AddLocation
import com.example.weather.locations.Back
import com.example.weather.locations.BackIcon
import com.example.weather.locations.Locations
import com.example.weather.locations.SelectCity
import com.example.weather.locations.TopLevel
import com.example.weather.locations.Vector

class LocationsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LocationsTest()
        }
    }
}

@Composable
fun More() {
    Locations(
        selectCity = "stringResource(id = R.string.selectCity)"
    )
}

@Preview
@Composable
fun LocationsTest(
    modifier: Modifier = Modifier,
    selectCity: String = stringResource(id = R.string.selectCity),
    onClickAdd: () -> Unit = {},
    onClickBack: () -> Unit = {}
) {
    TopLevel(modifier = modifier) {
        Back(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 30.0.dp,
                    y = 70.0.dp
                )
            )
        ) {
            BackIcon(onClickBack = onClickBack) {
                Vector(
                    modifier = Modifier
                        .boxAlign(
                            alignment = Alignment.TopStart,
                            offset = DpOffset(
                                x = -1.0.dp,
                                y = -1.0.dp
                            )
                        )
                        .rowWeight(1.0f)
                        .columnWeight(1.0f)
                )
            }
            SelectCity(
                selectCity = selectCity,
                modifier = Modifier.boxAlign(
                    alignment = Alignment.TopStart,
                    offset = DpOffset(
                        x = 26.0.dp,
                        y = 0.0.dp
                    )
                )
            )
        }
        AddLocation(
            onClickAdd = onClickAdd,
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 327.375.dp,
                    y = 73.375.dp
                )
            )
        )
        CityLazy()
    }
}

@Composable
fun CityLazy(){
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .width(250.dp) // Устанавливаем размер
            .padding(bottom = 200.dp)
            .offset(x = 38.dp, y = 141.dp) // Сдвигаем по X и Y
    ){
        items(400)

        {
            Text(
                text = "Item $it",
                fontSize = 20.sp, // Уменьшаем размер шрифта
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Right,
            )
        }
    }
}