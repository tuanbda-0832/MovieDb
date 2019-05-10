package com.example.moviedb.screen.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.moviedb.R
import com.example.moviedb.screen.home.HomeFragment.OnNavigationListener
import com.example.moviedb.utils.extensions.addFragmentToActivity

class MainActivity : AppCompatActivity(),OnNavigationListener {
    private val _mainFragment by lazy { MainFragment.newInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragmentToActivity(R.id.frame_layout_container, _mainFragment,false)
    }

    override fun navigateToFragment(fragment: Fragment) {
        addFragmentToActivity(R.id.frame_layout_container,fragment)
    }
}

