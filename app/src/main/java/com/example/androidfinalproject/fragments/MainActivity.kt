package com.example.androidfinalproject.fragments

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.androidfinalproject.R
import kotlinx.android.synthetic.main.main.*


class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        requestedOrientation = if(resources.getBoolean(R.bool.isTablet)){
            ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
        }else {
            ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
        }

        setContentView(R.layout.main)
        val phoneFragment = PhoneFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flTest, phoneFragment)
            commit()
        }
        topMovieButton?.setOnClickListener {

            val topMovieFragment = TopMovieFragment()
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flTest, topMovieFragment)
                addToBackStack(null)
                commit()
            }

            hideKeyboard()
        }
        searchButton?.setOnClickListener {
            val searchElement = etSearch?.text.toString()
            if (searchElement != "") {

                val searchFragment = SearchFragment(searchElement)
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.flTest, searchFragment)
                    addToBackStack(null)
                    commit()
                }

                hideKeyboard()

            }
        }
        popMoviesButton?.setOnClickListener {

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flTest, phoneFragment)
                addToBackStack(null)
                commit()
            }

            hideKeyboard()
        }
        etSearch.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                // if the event is a key down event on the enter button
                if (event.action == KeyEvent.ACTION_DOWN &&
                    keyCode == KeyEvent.KEYCODE_ENTER
                ) {
                    val searchElement = etSearch?.text.toString()
                    if (searchElement != "") {

                        val searchFragment = SearchFragment(searchElement)
                        supportFragmentManager.beginTransaction().apply {
                            replace(R.id.flTest, searchFragment)
                            addToBackStack(null)
                            commit()
                        }

                        hideKeyboard()
                        return true
                }

            }
                return false
        }
    })

    }

    private fun hideKeyboard() {
        val view = this.currentFocus

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (view != null) {
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }


}



