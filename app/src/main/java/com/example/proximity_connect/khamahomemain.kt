package com.example.proximity_connect

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class khamahomemain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_homefra)
        val mButton = findViewById<View>(R.id.button) as Button
        mButton.setOnClickListener {
            startActivity(
                Intent(
                    this@khamahomemain,
                    joinmeeting::class.java
                )
            )
        }
    }
}
