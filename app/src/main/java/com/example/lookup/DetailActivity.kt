package com.example.lookup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lookup.fragment.CorrectDialogFragment
import com.example.lookup.fragment.IncorrectDialogFragment
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        button1.setOnClickListener {
            CorrectDialogFragment().show(supportFragmentManager,"CorrectDialogFragment")
        }
        button2.setOnClickListener {
            IncorrectDialogFragment().show(supportFragmentManager,"IncorrectDialogFragment")
        }
    }

}