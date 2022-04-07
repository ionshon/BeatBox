package com.inu.andoid.beatbox

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.inu.andoid.beatbox.databinding.ItemLayoutBinding
import com.inu.contentresolver.beans.Music
import java.text.SimpleDateFormat

class SoundAdapter2() : RecyclerView.Adapter<SoundAdapter2.Holder>() {

    val musicList = mutableListOf<Music>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = DataBindingUtil.inflate<ItemLayoutBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_layout,
            parent,
            false
        )
        return Holder(binding)
    }

    override fun getItemCount() = musicList.size //sounds.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
//        val sound = sounds[position]
//        holder.bind(sound)
        val music = musicList[position]
          holder.setMusic(music)
    }

    inner class Holder(private val binding: ItemLayoutBinding):
        RecyclerView.ViewHolder(binding.root){

        var musicUri: Uri? = null
/*
        init {
            binding.viewModel2 = SoundViewModel()//beatBox)
            Log.d("d","df")
        }

        fun bind(sound: Sound){
            binding.apply {
                viewModel2?.sound = sound
                executePendingBindings() // recycerView에 포한된 layout 즉각 변경 위해
            }
        }*/
        fun setMusic(music: Music) {
//                1. 로드할 대상 Uri    2. 입력될 이미지뷰
            with(binding) {
                //     imageAlbum.setImageBitmap(Utils.songArt(music.path, binding.root.context))
                //      imageAlbum.setImageBitmap(decodeSampledBitmapFromResource(, music.id.toInt(), 250, 250));
                textTitle.text = music.title
                texArtist.text = music.artist
                val sdf = SimpleDateFormat("mm:ss")
                textDuration.text = sdf.format(music.duration)
            }

            Glide.with(binding.root.context)
                .load(music.getAlbumUri())
                //    .placeholder(R.drawable.ic_menu_close_clear_cancel).into(binding.imageAlbum)
                .placeholder(com.inu.andoid.beatbox.R.drawable.outline_music_note_24).into(binding.imageAlbum)
            //      Log.d("duration 길이:", "${music.duration}")
            //  } // 코루틴 마지막 줄
            this.musicUri = music.getMusicUri()
        }
    }


}