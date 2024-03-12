package com.example.rightnow

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var button: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById<Button>(R.id.log)
        button.setOnClickListener(View.OnClickListener { openActivityLoginPage() })
    }

    fun openActivityLoginPage() {
        val intent = Intent(this, loginPage::class.java)
        startActivity(intent)
    }
}