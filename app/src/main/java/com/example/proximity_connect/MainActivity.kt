package com.example.proximity_connect

// Imports from cmilroy
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.proximity_connect.ui.theme.ProximityConnectTheme

// imports from Khama
import android.content.Intent
import android.view.View
import android.widget.Button

// Nordic imports
import dagger.hilt.android.AndroidEntryPoint


//  Khama: class MainActivity : AppCompatActivity() {
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
   // Changed from: private var button: Button? = null
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        button1 = findViewById<Button>(R.id.log)
        button1!!.setOnClickListener { openActivityLoginPage() }
        button2 = findViewById<Button>(R.id.join)
        button2!!.setOnClickListener { openActivitySignPage() }
        button3 = findViewById<Button>(R.id.cont)
        button3!!.setOnClickListener { openGuessbyass() }
    }


    fun openActivityLoginPage() {
        val intent: Intent = Intent(this, loginPage::class.java)
        startActivity(intent)
    }

    fun openActivitySignPage() {
        val intent: Intent = Intent(this, SignUp::class.java)
        startActivity(intent)
    }

    fun openGuessbyass() {
        val intent: Intent = Intent(this, homeMain::class.java)
        startActivity(intent)
    }

}

/* Bluetooth stuff to work on elsewhere
        setContent {
            ProximityConnectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Creates List of Devices Found by scanner
                    //NavigationView(listOf(ScannerDestination))
                    Greeting("Android")
                }
            }
        }
*/
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProximityConnectTheme {
        Greeting("Android")
    }
}
