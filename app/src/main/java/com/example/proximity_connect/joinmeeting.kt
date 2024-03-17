package com.example.proximity_connect

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class joinmeeting : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.joinmeetingpromp)
        val mButton = findViewById<View>(R.id.button) as Button
        mButton.setOnClickListener {
            startActivity(
                Intent(
                    this@joinmeeting,
                    inmeetingpage::class.java
                )
            )
        }
    }
}
