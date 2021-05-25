package com.example.androidfinalproject.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidfinalproject.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main)
        val phoneFragment = PhoneFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flTest, phoneFragment)
            commit()
        }


    }
}

