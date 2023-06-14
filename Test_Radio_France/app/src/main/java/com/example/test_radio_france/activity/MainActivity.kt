package com.example.test_radio_france.activity

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apollographql.apollo3.ApolloClient
import com.example.BrandsQuery
import com.example.test_radio_france.R
import com.example.test_radio_france.adapter.RadioAdapter
import com.example.test_radio_france.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val apolloClient = ApolloClient.Builder()
            .serverUrl("https://openapi.radiofrance.fr/v1/graphql?x-token=84c107b0-22f0-4958-883d-381edaa54174")
            .build()

        val brandsQuery = BrandsQuery()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        lifecycleScope.launch {
            val result = withContext(Dispatchers.IO) {
                apolloClient.query(brandsQuery).execute()
            }
            with(recyclerView){
                layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                adapter = RadioAdapter(result.data!!, this@MainActivity)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

}