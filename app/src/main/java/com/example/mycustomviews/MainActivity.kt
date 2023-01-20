package com.example.mycustomviews

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mycustomviews.customeViews.CustomFanController

class MainActivity : AppCompatActivity() {

    lateinit var customView : CustomFanController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        customView = findViewById(R.id.dialView)

        customView.setOnClickListener(View.OnClickListener {
            // Do something here
            Toast.makeText(this@MainActivity, "Start Position", Toast.LENGTH_SHORT).show()

        })
    }
}