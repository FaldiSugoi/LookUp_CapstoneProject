package com.example.lookup.onboarding.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.lookup.R
import kotlinx.android.synthetic.main.fragment_first_screen.view.*


class SecondScreen : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_second_screen, container, false)

        val viewPage = activity?.findViewById<ViewPager2>(R.id.viewPager)

        view.button.setOnClickListener {
            viewPage?.currentItem = 2
        }

        return view
    }


}