package com.example.potatoservice.ui.sign

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.potatoservice.databinding.ActivitySignupInfoBinding

class SignUpInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.completeButton.setOnClickListener {
            val intent = Intent(this, SignUpInterestActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
