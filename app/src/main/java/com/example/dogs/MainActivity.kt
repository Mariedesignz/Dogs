package com.example.dogs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.StringRequestListener
import com.example.dogs.Adapter.DogsAdapter
import com.example.dogs.Model.DogsApi
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    val imageList = ArrayList<DogsApi>()

    private lateinit var dogsRV:RecyclerView
    private lateinit var dogNameText: EditText
    private lateinit var searchBtn:FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    // init views

        dogsRV = findViewById(R.id.dogsRecyclerView)
        dogNameText = findViewById(R.id.dogsNameET)
        searchBtn = findViewById(R.id.search_Btn)

        // Setting up Staggered Layout manger

        dogsRV.layoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)

        searchBtn.setOnClickListener{

            // get test from editText

            var name = dogNameText.text.toString()

            // call search Dog function
            searchDogs(name)

        }

    }

    private fun searchDogs(name: String) {

        imageList.clear()

        AndroidNetworking.initialize(this)

        // Call to json image format
        // pass name parameter in url. (Concate string)

        AndroidNetworking.get("https://dog.ceo/api/breed/$name/images")
            .setPriority(Priority.HIGH)
            .build()
            .getAsString(object : StringRequestListener{
                override fun onResponse(response: String?) {

                    val result = JSONObject(response)
                    val image = result.getJSONArray("message")

                    // iterate each item in the array to fetch each item

                    for (i in 0 until  image.length()){

                        val list = image.get(i)
                        imageList.add(

                            DogsApi(list.toString())
                        )
                    }

                    dogsRV.adapter = DogsAdapter(this@MainActivity, imageList)

                }

                override fun onError(anError: ANError?) {

                }

            })


    }
}