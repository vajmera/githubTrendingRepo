package com.example.githubtrendingrepoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
//import com.google.gson.internal.GsonBuildConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    val modelList=ArrayList<Model>()
    //val responseBody=Response<List<APIResponseObject>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        getMyData()
       // initData()
        //setRecyclerView()
    }

    private fun getMyData() {
        val retrofitBuilder=Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("https://private-anon-fdbd03fae1-githubtrendingapi.apiary-mock.com/").build().create(ApiInterface::class.java)
        val retrofitData=retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<APIResponseObjectItem>?> {
            override fun onResponse(
                call: Call<List<APIResponseObjectItem>?>?,
                response: Response<List<APIResponseObjectItem>?>?
            ) {
                if(response!=null){
                    val responseBody= response.body()
                    val imageView:ImageView?=findViewById(R.id.image_view)
                    if (responseBody != null) {
                        for(data in responseBody){
                            //val imageView:ImageView=view
                            modelList.add(Model(data.author.toString(),data.name.toString(),data.description.toString(),R.drawable.ic_action_name))
//                           if(imageView!=null){
//                               Picasso.get().load(data.avatar.toString()).into(imageView)
//
//                           }

                            Log.d("Author is ",data.author.toString())
                            Log.d("Name is ",data.name.toString())


                        }
                        val recyclerAdapter=RecyclerAdapter(modelList)
                        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView);

                        recyclerView.adapter=recyclerAdapter
                        recyclerView.setHasFixedSize(true)
                    }
                }
            }

            override fun onFailure(call: Call<List<APIResponseObjectItem>?>?, t: Throwable?) {
                if (t != null) {
                    Log.d("MainActivity",t.message.toString())
                }
            }
        })
    }

    private fun setRecyclerView(){
        val recyclerAdapter=RecyclerAdapter(modelList)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView);

        recyclerView.adapter=recyclerAdapter
        recyclerView.setHasFixedSize(true)
    }
//    private fun initData(){
//
//        modelList.add(Model("vajmera","hellokotlin","The details are to be shown here only",R.drawable.ic_action_name))
//        modelList.add(Model("vajmera","hellokotlin","The details are to be shown here only",R.drawable.ic_action_name))
//        modelList.add(Model("vajmera","hellokotlin","The details are to be shown here only",R.drawable.ic_action_name))
//        modelList.add(Model("vajmera","hellokotlin","The details are to be shown here only",R.drawable.ic_action_name))
//        modelList.add(Model("vajmera","hellokotlin","The details are to be shown here only",R.drawable.ic_action_name))
//        modelList.add(Model("vajmera","hellokotlin","The details are to be shown here only",R.drawable.ic_action_name))
//        modelList.add(Model("vajmera","hellokotlin","The details are to be shown here only",R.drawable.ic_action_name))
//
//
//
//    }
}