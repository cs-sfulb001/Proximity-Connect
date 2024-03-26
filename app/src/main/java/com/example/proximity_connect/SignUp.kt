package com.example.proximity_connect

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import database.DatabaseQueries
import android.os.StrictMode

class SignUp : AppCompatActivity() {
    private lateinit var emails: EditText
    private lateinit var phnumba: EditText
    private lateinit var psswrd: EditText
    private lateinit var Signing: Button
    private lateinit var guestP: Button
    private lateinit var golog: Button

    protected override fun onCreate(savedInstanceState: Bundle?) {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        phnumba = findViewById<EditText>(R.id.enterTextEmail)
        psswrd = findViewById<EditText>(R.id.enterTextPhone)
        emails = findViewById<EditText>(R.id.editTextTextPassword)
        Signing = findViewById<Button>(R.id.SigningIN)
        Signing.setOnClickListener { openActivityhomePage() }
        guestP = findViewById<Button>(R.id.conttt)
        guestP.setOnClickListener { openActivitybypassPage() }
        golog = findViewById<Button>(R.id.log)
        golog.setOnClickListener { openActivityjoinPage() }


    }

    fun openActivityhomePage() {
        val databaseQueries: DatabaseQueries = DatabaseQueries()
        val theemails = emails.text.toString()
        val thenumba = phnumba.text.toString()
        val thepsswd = psswrd.text.toString()
        val calendar = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val current = dateFormat.format(calendar).toString()
        val temp = theemails + " " + current
        val user_id = databaseQueries.AddUser(theemails, thenumba, temp, thepsswd, current)
        print(user_id)


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