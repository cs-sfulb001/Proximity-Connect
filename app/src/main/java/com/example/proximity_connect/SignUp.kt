package com.example.proximity_connect

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SignUp : AppCompatActivity() {
    private lateinit var button4: Button
    private lateinit var button5: Button
    private lateinit var button6: Button
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        button4 = findViewById<Button>(R.id.SigningIN)
        button4!!.setOnClickListener { openActivityhomePage() }
        button5 = findViewById<Button>(R.id.conttt)
        button5!!.setOnClickListener { openActivitybypassPage() }
        button6 = findViewById<Button>(R.id.log)
        button6!!.setOnClickListener { openActivityjoinPage() }
    }

    fun openActivityhomePage() {
        val intent: Intent = Intent(this, homeMain::class.java)
        startActivity(intent)
    }

    fun openActivitybypassPage() {
        val intent: Intent = Intent(this, homeMain::class.java)
        startActivity(intent)
    }

    fun openActivityjoinPage() {
        val intent: Intent = Intent(this, loginPage::class.java)
        startActivity(intent)
    }
}