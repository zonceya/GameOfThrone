package com.example.gameofthrones.ui

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gameofthrones.R
import com.example.gameofthrones.adapters.GameOfThronesAdapter
import com.example.gameofthrones.api.GameOfThronesApi
import com.example.gameofthrones.api.RetrofitInstance
import com.example.gameofthrones.data.GameOfThronesResponsesItem


class MainActivity : AppCompatActivity() {
    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
    var retrofit = RetrofitInstance.getInstance()
    var apiInterface = retrofit.create(GameOfThronesApi::class.java)

    private var adapter: GameOfThronesAdapter? = null
    private var houses: MutableList<GameOfThronesResponsesItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        recyclerview.layoutManager = LinearLayoutManager(this)
        adapter = GameOfThronesAdapter(houses) {
            val intent = Intent(this, HouseDetailsActivity::class.java)
            intent.putExtra(HouseDetailsActivity.HOUSE, "")
            startActivity(intent)
        }
        recyclerview!!.adapter = adapter
        fetchhouses()

    }

    private fun fetchhouses() {
        apiInterface.getHouses()?.enqueue(object : retrofit2.Callback<List<GameOfThronesResponsesItem>> {
            override fun onResponse(
                call: retrofit2.Call<List<GameOfThronesResponsesItem>>,
                response: retrofit2.Response<List<GameOfThronesResponsesItem>>
            ) {
                val houses = response.body()
                if (houses != null) {
                    this@MainActivity.houses.addAll(houses.sortedWith(compareByDescending{ it.name }))
                     adapter!!.notifyDataSetChanged()
                }

            }
            override fun onFailure(
                call: retrofit2.Call<List<GameOfThronesResponsesItem>>,
                t: Throwable
            ) {
                Log.e(TAG, "Got error : " + t.localizedMessage)
            }
        })
    }
}

