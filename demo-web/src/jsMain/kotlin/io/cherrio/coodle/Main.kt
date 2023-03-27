package io.cherrio.coodle

import androidx.compose.runtime.*
import io.coodle.core.layout.Alignment
import io.coodle.core.layout.Box
import io.coodle.core.layout.Column
import io.coodle.core.layout.Row
import io.coodle.core.modifier.*
import io.coodle.core.node.setContent
import io.coodle.foundation.button.Button
import io.coodle.foundation.text.Text
import io.coodle.foundation.textfield.TextField
import io.nacular.doodle.application.Modules
import io.nacular.doodle.drawing.Color

import io.nacular.doodle.theme.basic.BasicTheme
import io.nacular.doodle.theme.native.NativeTheme


fun main() {
    val themes = listOf(
        BasicTheme.basicButtonBehavior(),
        BasicTheme.basicLabelBehavior(),
        NativeTheme.nativeTextFieldBehavior(),
        BasicTheme.basicSwitchBehavior(),
        NativeTheme.nativeScrollPanelBehavior(),
        BasicTheme.basicListBehavior()
    )
    val controls = listOf(Modules.PointerModule, Modules.ImageModule)
    val allModules = themes + controls

    setContent(allModules) {
        var welcomeCount by remember { mutableStateOf(0) }


        Row(modifier = Modifier
            //.fillMaxWidth()
            .padding(15)
            .background(Color.Pink)
            .shadow(4)
        ) {
            Text(
                text = "Welcome",
                modifier = Modifier
                    //.background(Color.Pink)
                    .width(100)
                    .height(40)
            )
            Text(
                text = "Login: $welcomeCount",
                modifier = Modifier
                    //.background(Color.Magenta)
                    .width(100)
                    .height(40)
                    .clickable {
                        welcomeCount++
                    }
            )
            Text(
                text = "Continue",
                modifier = Modifier
                    //.background(Color.Red)
                    .width(100)
                    .height(40)
            )
            Button(
                modifier = Modifier
                    .background(Color.Red)
                    .width(100)
                    .height(40),
                text = "Login",
            )
        }
    }
//    renderComposable(root = "roo"){
//        Div {
//            org.jetbrains.compose.web.dom.Button {  }
//        }
//    }
}

@Composable
fun DebugScreen(){

}

@Composable
fun LoginScreen2() {

    val firstMessage by remember { mutableStateOf("Welcome:") }
    val secondMessage by remember { mutableStateOf("Login hereR") }
    val thirdMessage by remember { mutableStateOf("ContinueR") }

    Box(modifier = Modifier.fillMaxSize().background(Color.Pink)) {
        Column(
            modifier = Modifier
                .fillMaxWidth(2)
                .padding(10)
                .background(Color.Orange)
                //.clip(RoundedCorner(15))
                .border(Color.Blue, 3.0, RoundedCorner(15))
                .align(Alignment.Center)
        ) {
            Column(modifier = Modifier
                //.fillMaxWidth()
                .padding(15)
                .background(Color.Pink)
                .shadow(4)
            ) {
                Text(
                    text = "Welcome",
                    modifier = Modifier
                        .background(Color.Pink)
                        .weight(1f)
                        .fillMaxWidth()
                )
                Text(
                    text = "Login here",
                    modifier = Modifier
                        .background(Color.Magenta)
                        .weight(1f)
                        .fillMaxWidth()
                )
                Text(
                    text = "Continue",
                    modifier = Modifier
                        .background(Color.Red)
                        .weight(1f)
                        .fillMaxWidth()
                )
            }
            Row(modifier = Modifier
                //.fillMaxWidth()
                .padding(15)
                .background(Color.Pink)
                .shadow(4)
            ) {
                Text(
                    text = "Welcome",
                    modifier = Modifier
                        //.background(Color.Pink)
                        .weight(1f)
                        .height(40)
                        .clickable {
                            //welcomeCount++
                        }
                )
                Text(
                    text = "",
                    modifier = Modifier
                        //.background(Color.Magenta)
                        .weight(1f)
                        .height(40)
                )
                Text(
                    text = "Continue",
                    modifier = Modifier
                        //.background(Color.Red)
                        .weight(1f)
                        .height(40)
                )
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .height(40),
                    text = "Login",
                )
            }

            TextField(
                modifier = Modifier
                    .padding(10)
                    .fillMaxWidth()
                    .height(40)
            ) {

            }
            TextField(
                modifier = Modifier
                    .padding(10)
                    .fillMaxWidth()
                    .height(40)
            ) {

            }

            Button(
                modifier = Modifier
                    .padding(10)
                    .fillMaxWidth()
                    .background(Color.Green)
                    .height(50),
                text = "Login",
                textColor = Color.White
            )
        }
    }
}