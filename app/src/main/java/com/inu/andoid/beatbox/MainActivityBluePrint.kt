package com.inu.andoid.beatbox

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.ActionMode
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.inu.andoid.beatbox.databinding.ActivityMainBinding
import com.inu.andoid.beatbox.utils.MusicProvider
import com.inu.contentresolver.beans.Music

abstract class MainActivityBluePrint: AppCompatActivity() {
    private val permission = Manifest.permission.READ_EXTERNAL_STORAGE
    private val FLAG_REQ_STORAGE = 99
    private var adapter: MusicAdapter? = null
    private var deviceMusic = mutableListOf<Music>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = MusicAdapter()

        if(isPermitted()) {
            setViews()
        }else {
            ActivityCompat.requestPermissions(this, arrayOf(permission), FLAG_REQ_STORAGE)
        }
    }

    private fun setViews() {
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        getMusic()
        with(binding) {
            recyclerView.adapter = adapter
            recyclerView.layoutManager = GridLayoutManager(applicationContext, 1) //LinearLayoutManager(this)
        }
    }
    private fun getMusic() {
        deviceMusic.addAll(MusicProvider.getMusicList(this))
        adapter?.addSongs(deviceMusic)
    }

    private fun isPermitted() : Boolean { // 책에는 checkPermission, 조건이 하나일 때 한줄로
        return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == FLAG_REQ_STORAGE) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //      startProcess()
            } else {
                Toast.makeText(this, "권한 요청 실행해야지 앱 실행", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}