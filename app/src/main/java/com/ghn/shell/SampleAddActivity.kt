package com.ghn.shell

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText

class SampleAddActivity : AppCompatActivity() {
    private lateinit var editTextName: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_add)

        editTextName = findViewById(R.id.sample_add_name)
        val editTextImgUrl = findViewById<EditText>(R.id.sample_add_imgUrl)
        val saveButton = findViewById<Button>(R.id.sample_add_button)
        saveButton.setOnClickListener {
            val intent = Intent()
            if (!TextUtils.isEmpty(editTextName.text)) {
                val name = editTextName.text.toString()
                val imgUrl = (editTextImgUrl.text ?: "").toString()
                intent.putExtra("name", name)
                intent.putExtra("imgUrl", imgUrl)
                setResult(Activity.RESULT_OK, intent)
            }
            finish()
        }
    }
}