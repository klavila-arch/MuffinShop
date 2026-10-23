
package notes.app.ebc.karen.muffinshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import notes.app.ebc.karen.muffinshop.ui.MuffinShopApp
import notes.app.ebc.karen.muffinshop.ui.theme.MuffinShopTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            MuffinShopTheme {
                MuffinShopApp()
            }
        }
    }
}
