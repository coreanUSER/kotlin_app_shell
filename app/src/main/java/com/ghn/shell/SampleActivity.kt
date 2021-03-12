package com.ghn.shell

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ghn.shell.adapters.SampleListAdapter
import com.ghn.shell.data.Sample
import com.ghn.shell.viewmodels.SampleViewModel
import com.ghn.shell.viewmodels.SampleViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SampleActivity : AppCompatActivity() {

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
            requestActivity.launch(intent)
        }
    }

    private val requestActivity: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if(result.resultCode == Activity.RESULT_OK) {
            result.data?.let {
                var resultIntent = it
                val name: String = resultIntent.getStringExtra("name") ?: ""
                val imgUrl: String = resultIntent.getStringExtra("imgUrl") ?: ""
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

    // Notification Permission
    private fun isPermissionGranted() {
        val sets: Set<String> = NotificationManagerCompat.getEnabledListenerPackages(this)
        if(!sets.contains(packageName)) {
            val intent = Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
            startActivity(intent)
        }
    }
}