package com.example.proximity_connect

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.proximity_connect.SignUp

class loginPage : AppCompatActivity() {
    private lateinit var button7: Button
    private lateinit var button8: Button
    private lateinit var Textx: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        button7 = findViewById<Button>(R.id.logging)
        button7.setOnClickListener(View.OnClickListener { openActivityMainPage() })
        button8 = findViewById<Button>(R.id.contgue)
        button8.setOnClickListener(View.OnClickListener { openGuessbyass() })
        Textx = findViewById<TextView>(R.id.DHA)
        Textx.setOnClickListener(View.OnClickListener { openActivitySignPage() })
    }

    fun openActivityMainPage() {
        val intent = Intent(this, homeMain::class.java)
        startActivity(intent)
    }

    fun openGuessbyass() {
        val intent = Intent(this, homeMain::class.java)
        startActivity(intent)
    }

    fun openActivitySignPage() {
        val intent = Intent(this, SignUp::class.java)
        startActivity(intent)
    }
}