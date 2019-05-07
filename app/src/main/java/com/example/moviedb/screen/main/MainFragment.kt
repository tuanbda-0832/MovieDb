package com.example.moviedb.screen.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.example.moviedb.R
import com.example.moviedb.base.BaseFragment
import com.example.moviedb.databinding.FavoriesFragmentBinding
import com.example.moviedb.databinding.FragmentMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainFragment : BaseFragment() {
    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var _mainBinding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _mainBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return _mainBinding.root
    }

    override fun setUpView() {
        val host: NavHostFragment? =
            childFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment?
        val navController = host?.navController
        navController?.let {
            setupBottomNavMenu(_mainBinding.root, navController)
        }
    }

    override fun bindView() {
    }

    private fun setupBottomNavMenu(view: View, navController: NavController) {
        val bottomNav = view.findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        bottomNav?.setupWithNavController(navController)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController())
                || super.onOptionsItemSelected(item)
    }
}
