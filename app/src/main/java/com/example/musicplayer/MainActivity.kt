package com.example.musicplayer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mRecyclerView: RecyclerView
    lateinit var musicData:ArrayList<Data>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)  //learn
        setContentView(binding.root)

        mRecyclerView=findViewById(R.id.recyclerView)



        //API CODE
        val retrofitBuilder=Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIInterface::class.java)

        val retrofitData=retrofitBuilder.getMusic("eminem")
        retrofitData.enqueue(object : Callback<music?> {
            override fun onResponse(p0: Call<music?>, p1: Response<music?>) {
                val musicData=p1.body()?.data!!
                val myAdapter=MyAdapter(this@MainActivity,musicData)
                mRecyclerView.layoutManager=LinearLayoutManager(this@MainActivity)
                mRecyclerView.adapter=myAdapter
                myAdapter.setOnClickListener(object :MyAdapter.onItemClickListener{
                    //action to be performed
                    override fun onItemClicking(position: Int) {
                        //what action to be performed
                        val intent=Intent(applicationContext,MusicDetail::class.java)
                        intent.putExtra("tit",musicData[position].title)
                        intent.putExtra("imgUrl",musicData[position].album.cover)
                        val musicURL=musicData[position].preview
                        intent.putExtra("music",musicURL)
                        startActivity(intent)
                    }
                })
            }

            override fun onFailure(p0: Call<music?>, p1: Throwable) {
                Log.d("Tag","API FAILED ${p1.message}")
            }
        })
    }
}