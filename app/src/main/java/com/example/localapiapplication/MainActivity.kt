package com.example.localapiapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var rvdata: RecyclerView
    lateinit var apiService: ServiceInterface
    private var ambilData: ArrayList<KontakData> = arrayListOf()
    lateinit var btnadd: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvdata = findViewById(R.id.rv_data)
        btnadd = findViewById(R.id.btn_main_add)
        btnadd.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }
        apiService = Repository.getDataAPI().create(ServiceInterface::class.java)
        apiService.getData().enqueue(object : Callback<List<KontakData>>{
            override fun onResponse(
                call: retrofit2.Call<List<KontakData>>,
                response: Response<List<KontakData>>
            ) {
                if (response.isSuccessful){
                    val res = response.body()
                    ambilData.addAll(res!!)
                    rvdata.layoutManager = LinearLayoutManager(this@MainActivity)
                    rvdata.adapter = KontakAdapter(ambilData)
                }
            }

            override fun onFailure(call: retrofit2.Call<List<KontakData>>, t: Throwable) {
            }

        })

    }
}