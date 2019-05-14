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
import com.example.moviedb.databinding.HomeFragmentBinding
import com.example.moviedb.screen.movied_edtail_fragment.MovieDetailFragment
import com.example.moviedb.utils.EndlessRecyclerViewScrollListener
import com.example.moviedb.utils.extensions.showToast
import com.example.moviedb.utils.liveData.autoCleared
import kotlinx.android.synthetic.main.home_fragment.recycler_view_home
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : BaseFragment() {
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
        _homeAdapter = HomeAdapter {
            _onNavigationListener?.navigateToFragment(MovieDetailFragment.newInstance(it.id))
        }
        setUpRecyclerView()
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
        _homeViewModel.moviesLoadMore.observe(viewLifecycleOwner, Observer {
            _homeAdapter.addLoadMoreData(it)
        })
        _homeViewModel.genres.observe(viewLifecycleOwner, Observer {
            _homeAdapter.addGenres(it)
        })
        _homeViewModel.onMessageError.observe(viewLifecycleOwner, Observer {
            it?.let {
                context?.showToast(it)
            }
        })
        _homeViewModel.onProgressBarEvent.observe(viewLifecycleOwner, Observer {
            _homeFragmentBinding.progressBar.visibility = when (it) {
                true -> View.VISIBLE
                else -> View.GONE
            }
        })
    }

    fun setUpRecyclerView() {
        val layoutManager = GridLayoutManager(context, 2)
        _homeFragmentBinding.recyclerViewHome.run {
            adapter = _homeAdapter
            this.layoutManager = layoutManager
            addOnScrollListener(object : EndlessRecyclerViewScrollListener() {
                override fun onLoadMore(page: Int) {
                    _homeViewModel.onLoadMore(page)
                }
            })
        }
    }

    interface OnNavigationListener {
        fun navigateToFragment(fragment: Fragment)
    }
}
