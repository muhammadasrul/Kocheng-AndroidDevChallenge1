/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.data.Kocheng
import com.example.androiddevchallenge.data.kochengList
import com.example.androiddevchallenge.ui.theme.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                elevation = 4.dp,
                title = {Text(text = "Kocheng")}
            )
        },
        content = {
            NavHost(navController, startDestination = "kocheng") {
                composable("kocheng") { MainScreen(navController)}
                composable("details/{id}") { navBackStackEntry ->
                    val kocheng =
                        kochengList.first { it.id == navBackStackEntry.arguments!!.getString("id")!!}
                    DetailScreen(kocheng)
                }
            }
        }
    )
}

@Composable
fun MainScreen(navController: NavController) {
    Surface(color = MaterialTheme.colors.background) {
        Column(modifier = Modifier.padding(18.dp)) {
            BannerItem()
            Text(
                text = "Recomended",
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp)
            )
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(14.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(kochengList) {kocheng ->
                    ListItem(kocheng) {
                        navController.navigate("details/${kocheng.id}")
                    }
                }
            }
        }
    }
}

@Composable
fun ListItem(kocheng: Kocheng, onItemClick: (Kocheng) -> Unit) {
    Card(
        shape = RoundedCornerShape(18.dp),
        elevation = 0.dp,
        modifier = Modifier
            .clickable { onItemClick(kocheng) }
            .height(124.dp)
            .border(1.dp, Color.Gray, RoundedCornerShape(18.dp)),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = kocheng.image),
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .width(108.dp)
                    .height(108.dp)
                    .clip(shape = RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.width(16.dp))
            Column {
                Text(text = kocheng.name, fontFamily = FontFamily(Font(R.font.poppins_medium)))
                Text(text = "Type: ${kocheng.breed}", style = typography.body2)
                Text(text = "Age: ${kocheng.age}", style = typography.body2)
                Spacer(Modifier.height(4.dp))
                if (kocheng.sex == "Male") {
                    Text(
                        text = kocheng.sex,
                        style = typography.body2,
                        color = purple500,
                        modifier = Modifier
                            .background(purple50, RoundedCornerShape(12.dp))
                            .padding(horizontal = 18.dp)
                    )
                } else if (kocheng.sex == "Female"){
                    Text(
                        text = kocheng.sex,
                        style = typography.body2,
                        color = salmon200,
                        modifier = Modifier
                            .background(salmon50, RoundedCornerShape(12.dp))
                            .padding(horizontal = 18.dp)
                    )
                }

            }

        }
    }
}

@Composable
fun BannerItem() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(purple200, RoundedCornerShape(18.dp))
            .height(124.dp)
            .fillMaxWidth()
            .padding(horizontal = 18.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_kocheng),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(72.dp)
        )
        Spacer(modifier = Modifier.width(18.dp))
        Text(
            text = "Adopt your favorite cat!",
            fontSize = 24.sp,
            color = Color.White,
            fontFamily = FontFamily(Font(R.font.poppins_medium))
        )
    }
}

@Composable
fun DetailScreen(kocheng: Kocheng) {
    Column {
        Image(
            painter = painterResource(id = kocheng.image),
            contentDescription = null,
            modifier = Modifier
                .requiredHeight(320.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Column(Modifier.padding(24.dp)) {
            Text(
                text = kocheng.name,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(text = "Breed: ${kocheng.breed}", style = typography.body2)
            Text(text = "Age: ${kocheng.age}", style = typography.body2)
            Text(text = "Sex: ${kocheng.sex}", style = typography.body2)
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = "${kocheng.name} is Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas tristique lacus id ornare finibus. Suspendisse potenti. Sed sit amet ante non ipsum lacinia malesuada at ac augue. Pellentesque ultrices nulla at sollicitudin facilisis.",
                style = typography.body1
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(backgroundColor = purple200),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth(),
                content = {
                    Text(
                        text = "Adopt Me",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)))
                }
            )
        }
    }
}


@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
