package com.inu.andoid.beatbox

import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import android.media.SoundPool
import android.util.Log
import androidx.databinding.BaseObservable
import java.io.IOException
import java.lang.Exception

private const val TAG = "assets"
private const val SOUNDS_FOLDER = "sample_sounds"
private const val MAX_SOUNDS = 5
class BeatBox(private val assets: AssetManager): BaseObservable() {

    val sounds : List<Sound>
    private val soundPool = SoundPool.Builder()
        .setMaxStreams(MAX_SOUNDS)
        .build()

    init {
        sounds = loadSound()
    }

    fun play(sound: Sound){
        sound.soundId?.let {
            soundPool.play(it, 1.0f, 1.0f, 1, 0, 1.0f)
        }
    }

    fun release() {
        soundPool.release()
    }

    private fun loadSound(): List<Sound>{
        val soundNames : Array<String>
        try {
            soundNames = assets.list(SOUNDS_FOLDER)!!
        }catch (e: Exception){
            Log.e(TAG, "Could not list asstes", e)
            return emptyList()
        }
        val sounds = mutableListOf<Sound>()
        soundNames.forEach { filename ->
            val assetPath = "$SOUNDS_FOLDER/$filename"
            val sound = Sound(assetPath)
            //sounds.add(sound)
            try {
                load(sound)
                sounds.add(sound)
            } catch (ioe: IOException){
                Log.e(TAG, "Could not load sound $filename", ioe)
            }
        }
        return sounds
    }

    private fun load(sound: Sound) {
        val afd : AssetFileDescriptor = assets.openFd(sound.assetPath)
        val soundId = soundPool.load(afd, 1)
        sound.soundId = soundId
    }
}