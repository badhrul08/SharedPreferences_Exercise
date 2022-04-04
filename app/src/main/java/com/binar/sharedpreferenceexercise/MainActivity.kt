package com.binar.sharedpreferenceexercise

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.binar.sharedpreferenceexercise.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val sharedPrefFile = "kotlinsharedpreference"

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val sharedPreferences : SharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        binding.btnSave.setOnClickListener {
            val id: Int = Integer.parseInt(binding.etInputId.text.toString())
            val name: String = binding.etInputName.text.toString()
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putInt("id_key", id)
            editor.putString("name_key", name)
            editor.apply()
            Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show()
        }

        binding.btnView.setOnClickListener {
            val sharedIdValue = sharedPreferences.getInt("id_key", 0)
            val sharedNameValue = sharedPreferences.getString("name_key", "defaultName")
            if (sharedIdValue == 0 && sharedNameValue.equals("defaultName")){
                binding.tvShowName.text = "default name : $sharedNameValue"
                binding.tvShowId.text = "default id: $sharedIdValue"
                Toast.makeText(this, "Data View Kosong", Toast.LENGTH_SHORT).show()
            } else{
                binding.tvShowName.text = sharedNameValue
                binding.tvShowId.text = sharedIdValue.toString()
                Toast.makeText(this, "Data View Ditampilkan", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnClear.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            binding.tvShowName.text = ""
            binding.tvShowId.text = ""
            Toast.makeText(this, "Data Clear", Toast.LENGTH_SHORT).show()
        }
    }
}