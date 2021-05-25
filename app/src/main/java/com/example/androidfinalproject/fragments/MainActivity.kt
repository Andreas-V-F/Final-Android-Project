package com.example.androidfinalproject.fragments

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidfinalproject.R
import kotlinx.android.synthetic.main.activity_main.*

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

