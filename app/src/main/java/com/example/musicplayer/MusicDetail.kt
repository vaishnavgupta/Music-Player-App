package com.example.musicplayer

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.squareup.picasso.Picasso

class MusicDetail : AppCompatActivity() {
    lateinit var mediaPlayer: MediaPlayer
    var totalTime:Int=0
    override fun onBackPressed() {
        super.onBackPressed()
        mediaPlayer.stop()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_music_detail)


        val bPlay=findViewById<Button>(R.id.playBtn)
        val bPause=findViewById<Button>(R.id.pauseBtn)
        val seek=findViewById<SeekBar>(R.id.seekBar)
        val alImg=findViewById<ImageView>(R.id.albumImage)
        val alNm=findViewById<TextView>(R.id.albumName)

        //intent var
        val mName=intent.getStringExtra("tit")
        val mImgUrl=intent.getStringExtra("imgUrl").toString()
        val mMusic= intent.getStringExtra("music")
        if (mMusic != null && mMusic.isNotEmpty()) {
            val musicUri = mMusic.toUri()
            mediaPlayer = MediaPlayer.create(this, musicUri)
        } else {
            Log.e("MusicDetail", "Received null or empty music URI")
            // Handle the error, e.g., show a message or fallback behavior
        }

        alNm.text=mName.toString()
        //picasso
        Picasso.get().load(mImgUrl).into(alImg);


        mediaPlayer.setVolume(1f,2f)
        totalTime=mediaPlayer.duration

        bPlay.setOnClickListener {
            mediaPlayer.start()
        }
        bPause.setOnClickListener {
            mediaPlayer.pause()
        }

        seek.max=totalTime
        seek.setOnSeekBarChangeListener(object:SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(fromUser){
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                //nothing
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //nothing
            }

        })


        var handler= Handler()
        handler.postDelayed(object:Runnable{
            override fun run() {
                try {
                    seek.progress = mediaPlayer.currentPosition
                    handler.postDelayed(this, 1000)
                }catch (exception:Exception){
                    seek.progress=0
                }
            }

        },0)
    }
}