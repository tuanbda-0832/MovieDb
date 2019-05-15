package com.example.moviedb.screen.home

import android.content.Context
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
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : BaseFragment<HomeViewModel, HomeFragmentBinding>() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    override val viewModel: HomeViewModel by sharedViewModel()

    private var _onNavigationListener: OnNavigationListener? = null

    private var _homeAdapter by autoCleared<HomeAdapter>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnNavigationListener) {
            _onNavigationListener = context
        }
    }

    override fun getLayout(): Int = R.layout.home_fragment

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
        viewModel.getPopularMovies()
        viewModel.getGenres()

        viewModel.movies.observe(viewLifecycleOwner, Observer {
            _homeAdapter.addData(it)
        })
        viewModel.moviesLoadMore.observe(viewLifecycleOwner, Observer {
            _homeAdapter.addLoadMoreData(it)
        })
        viewModel.genres.observe(viewLifecycleOwner, Observer {
            _homeAdapter.addGenres(it)
        })
        viewModel.onMessageError.observe(viewLifecycleOwner, Observer {
            it?.let {
                context?.showToast(it)
            }
        })
    }

    fun setUpRecyclerView() {
        val layoutManager = GridLayoutManager(context, 2)
        binding?.let {
            it.recyclerViewHome.run {
                adapter = _homeAdapter
                this.layoutManager = layoutManager
                addOnScrollListener(object : EndlessRecyclerViewScrollListener() {
                    override fun onLoadMore(page: Int) {
                        viewModel.onLoadMore(page)
                    }
                })
            }
        }
    }

    interface OnNavigationListener {
        fun navigateToFragment(fragment: Fragment)
    }
}
