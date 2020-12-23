package com.example.creditcardchecker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun starter(view: View) {
        val intent = Intent(this,card_check_activity::class.java).putExtra("hey","hey")
        startActivity(intent)
    }
}