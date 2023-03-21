package io.cherrio.coodle

import androidx.compose.runtime.Composable
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
        LoginScreen2()
    }
}

@Composable
fun LoginScreen2() {
    Box(modifier = Modifier.fillMaxSize().background(Color.Pink)) {
        Column(
            modifier = Modifier
                .fillMaxWidth(2)
                .padding(20)
                .background(Color.Orange)
                .clip(CutCornerShape(15, 15, 15, 15))
                .align(Alignment.Center)
        ) {
            Row(modifier = Modifier.fillMaxWidth().padding(5)) {
                Text(
                    text = "Welcome", Color.White, modifier = Modifier
                        .size(100, 100)
                        .background(Color.Blue)
                        .align(Alignment.Vertical.CenterVertically)
                )
//                Text(
//                    text = "Login here",
//                    Color.Blue,
//                    modifier = Modifier
//                        .size(35, 200)
//                        .align(Alignment.Vertical.CenterVertically)
//                )
            }

            TextField(
                modifier = Modifier
                    .padding(10)
                    .fillMaxWidth()
                    .height(40.dp)
            ) {

            }
            TextField(
                modifier = Modifier
                    .padding(10)
                    .fillMaxWidth()
                    .height(40.dp)
            ) {

            }

            Button(
                modifier = Modifier
                    .padding(10)
                    .fillMaxWidth()
                    .height(50.dp),
                text = "Login",
                textColor = Color.White
            )
        }
    }
}