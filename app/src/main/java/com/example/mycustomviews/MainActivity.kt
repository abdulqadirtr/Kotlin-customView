package com.example.mycustomviews

import android.os.Bundle
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import androidx.appcompat.app.AppCompatActivity
import com.example.mycustomviews.customeViews.MyCanvas

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var myCanvas = MyCanvas(this@MainActivity)
        myCanvas.systemUiVisibility = SYSTEM_UI_FLAG_FULLSCREEN

        setContentView(myCanvas)

    }
}