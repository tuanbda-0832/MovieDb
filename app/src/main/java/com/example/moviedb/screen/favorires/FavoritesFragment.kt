package com.example.moviedb.screen.favorires

import android.content.Context
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviedb.R
import com.example.moviedb.base.BaseFragment
import com.example.moviedb.databinding.FavoriesFragmentBinding
import com.example.moviedb.screen.home.HomeFragment.OnNavigationListener
import com.example.moviedb.screen.movied_edtail_fragment.MovieDetailFragment
import com.example.moviedb.utils.liveData.autoCleared
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : BaseFragment<FavoritesViewModel, FavoriesFragmentBinding>() {
    companion object {
        fun newInstance() = FavoritesFragment()
    }

    var favoritesAdapter by autoCleared<FavoritesAdapter>()

    override val viewModel: FavoritesViewModel by viewModel()

    override fun getLayout(): Int = R.layout.favories_fragment

    private var onNavigationListener: OnNavigationListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnNavigationListener) {
            onNavigationListener = context
        }
    }

    override fun setUpView() {
        favoritesAdapter = FavoritesAdapter({
            onNavigationListener?.navigateToFragment(MovieDetailFragment.newInstance(it.id))
        }, {
            viewModel.unFavoriteMovie(it)
        })
        setUpRecyclerView()
    }

    override fun onDetach() {
        super.onDetach()
        onNavigationListener = null
    }

    override fun bindView() {
        super.bindView()
        viewModel.getGenresLocal()
        viewModel.getFavoriteMovies()
    }

    override fun registerLiveData() {
        viewModel.onMessageError.observe(viewLifecycleOwner, Observer {

        })
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            favoritesAdapter.addData(it)
        })

        viewModel.genres.observe(viewLifecycleOwner, Observer {
            favoritesAdapter.addGenres(it)
        })

        viewModel.onDeleteMovieEvent.observe(viewLifecycleOwner, Observer {
            it?.let {
                favoritesAdapter.deleteMovie(it)
            }
        })
    }

    private fun setUpRecyclerView() {
        binding?.recyclerViewFavorite?.run {
            adapter = favoritesAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
    }
}
