package com.example.moviedb.screen.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviedb.R
import com.example.moviedb.base.BaseFragment
import com.example.moviedb.data.model.Movie
import com.example.moviedb.databinding.HomeFragmentBinding
import com.example.moviedb.screen.home.HomeAdapter.OnItemClickListener
import com.example.moviedb.screen.movied_edtail_fragment.MovieDetailFragment
import com.example.moviedb.utils.extensions.showToast
import com.example.moviedb.utils.liveData.autoCleared
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : BaseFragment(), OnItemClickListener {
    companion object {

        fun newInstance() = HomeFragment()
    }

    private lateinit var _homeFragmentBinding: HomeFragmentBinding

    private val _homeViewModel: HomeViewModel by sharedViewModel()

    private var _onNavigationListener: OnNavigationListener? = null

    private var _homeAdapter by autoCleared<HomeAdapter>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnNavigationListener) {
            _onNavigationListener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _homeFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        return _homeFragmentBinding.root
    }

    override fun setUpView() {
        _homeAdapter = HomeAdapter()
        _homeAdapter.setOnItemClickListener(this)
        _homeFragmentBinding.run {
            recyclerViewHome.run {
                adapter = _homeAdapter
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
            }
        }
    }

    override fun onItemClick(movie: Movie) {
        _onNavigationListener?.navigateToFragment(MovieDetailFragment.newInstance(movie.id))
    }

    override fun onDetach() {
        super.onDetach()
        _onNavigationListener = null
    }

    override fun registerLiveData() {
        _homeViewModel.getPopularMovies()
        _homeViewModel.getGenres()
        _homeViewModel.movies.observe(viewLifecycleOwner, Observer {
            _homeAdapter.addData(it)
        })
        _homeViewModel.genres.observe(viewLifecycleOwner, Observer {
            _homeAdapter.addGenres(it)
        })
        _homeViewModel.onMessageError.observe(viewLifecycleOwner, Observer {
            it?.let {
                context?.showToast(it)
            }
        })
    }

    interface OnNavigationListener {
        fun navigateToFragment(fragment: Fragment)
    }
}
