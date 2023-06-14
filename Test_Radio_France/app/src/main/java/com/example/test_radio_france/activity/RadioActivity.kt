package com.example.test_radio_france.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apollographql.apollo3.ApolloClient
import com.example.ShowsQuery
import com.example.test_radio_france.R
import com.example.test_radio_france.adapter.ShowAdapter
import com.example.test_radio_france.databinding.ActivityRadioBinding
import com.example.type.StationsEnum
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RadioActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRadioBinding

            override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_radio)
                binding = ActivityRadioBinding.inflate(layoutInflater)
                setContentView(binding.root)

                setSupportActionBar(binding.toolbar)

                supportActionBar?.setDisplayHomeAsUpEnabled(true)

                val apolloClient = ApolloClient.Builder()
                    .serverUrl("https://openapi.radiofrance.fr/v1/graphql?x-token=84c107b0-22f0-4958-883d-381edaa54174")
                    .build()
                val intent = intent
                val data = intent.getStringExtra("id_radio")
                val titleRadio = intent.getStringExtra("title_radio")
                val textTitle = findViewById<TextView>(R.id.TextTitleRadio)
                textTitle.text = titleRadio
                val showsQuery = ShowsQuery(StationsEnum.valueOf(data!!), 100 )
                val recyclerView = findViewById<RecyclerView>(R.id.RecyclerViewShow)
                lifecycleScope.launch {
                    val result = withContext(Dispatchers.IO) {
                        apolloClient.query(showsQuery).execute()
                    }
                    with(recyclerView!!){
                        layoutManager = LinearLayoutManager(this@RadioActivity, LinearLayoutManager.VERTICAL, false)
                        adapter = ShowAdapter(result.data!!)
                    }
                }
            }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}