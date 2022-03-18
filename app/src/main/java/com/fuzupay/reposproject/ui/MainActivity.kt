package com.fuzupay.reposproject.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fuzupay.reposproject.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toSearch.setOnClickListener {
            startActivity(Intent(this@MainActivity,SearchActivity::class.java))
        }
    }
}