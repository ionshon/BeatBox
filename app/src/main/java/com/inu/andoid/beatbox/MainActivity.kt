package com.inu.andoid.beatbox

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inu.andoid.beatbox.databinding.ActivityMainBinding
import com.inu.andoid.beatbox.databinding.ItemLayoutBinding
import com.inu.contentresolver.beans.Music

class MainActivity : MainActivityBluePrint() {

    private lateinit var beatBox: BeatBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        beatBox = BeatBox(assets)
    }


    override fun onDestroy() {
        super.onDestroy()
        beatBox.release()
    }
}