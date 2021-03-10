package com.ghn.shell

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ghn.shell.adapters.SampleListAdapter
import com.ghn.shell.data.Sample
import com.ghn.shell.viewmodels.SampleViewModel
import com.ghn.shell.viewmodels.SampleViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SampleActivity : AppCompatActivity() {

    private val sampleActivityRequestCode = 1
    private val sampleViewModel: SampleViewModel by viewModels {
        SampleViewModelFactory((application as SampleApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)

        val recyclerView = findViewById<RecyclerView>(R.id.sample_recyclerView)
        val adapter = SampleListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        sampleViewModel.allSamples.observe(this, Observer { samples ->
            // Update the cached copy of the sample in the adapter.
            samples?.let { adapter.submitList(it) }
        })

        val fab = findViewById<FloatingActionButton>(R.id.sample_fab)
        fab.setOnClickListener {
            val intent = Intent(this@SampleActivity, SampleAddActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == sampleActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.let {
                val name: String = it.getStringExtra("name") ?: ""
                val imgUrl: String = it.getStringExtra("imgUrl") ?: ""
                val sample = Sample()
                sample.name = name
                sample.imgUrl = imgUrl
                sampleViewModel.insert(sample)
            }
        } else {
            Toast.makeText(
                applicationContext,
                "Empty Name Space",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}