package com.inu.andoid.beatbox

import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.DataBindingUtil
import com.inu.andoid.beatbox.databinding.ItemLayoutBinding
import com.inu.contentresolver.beans.Music

private var isPlaying = false

class SoundViewModel(): BaseObservable() {

    var mediaPlayer:MediaPlayer? = null
    /*val binding = DataBindingUtil.inflate<ItemLayoutBinding>(
        LayoutInflater.from(),
      com.inu.andoid.beatbox.R.layout.item_layout,
      parent,
      false
  )*/
    fun onButtonClicked() {

    //    player?.musicPlayer(musicUri, binding.root.context)
        Log.d("클릭:", "adapter Holder")

      /*  if(mediaPlayer != null && !isPlaying)
        {
            mediaPlayer?.release()
            mediaPlayer = null
            mediaPlayer = MediaPlayer.create(, musicUri)
            mediaPlayer?.start()
            Log.d("mediplayer", "not null")
        }*/
    }

    private var music: Music? = null
        set(value) {
            field = value
            notifyChange()
        }

    @get:Bindable
    val musicUri: Uri?
    get() = music?.getMusicUri()
}