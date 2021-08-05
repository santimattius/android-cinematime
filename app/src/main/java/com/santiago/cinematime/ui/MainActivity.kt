package com.santiago.cinematime.ui

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.santiago.cinematime.common.PermissionRequester
import com.santiago.cinematime.databinding.ActivityMainBinding
import com.santiago.cinematime.ui.adapter.MovieAdapter
import com.santiago.cinematime.ui.adapter.TvAdapter
import com.santiago.cinematime.ui.adapter.holder.ItemViewHolder
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel


class MainActivity : AppCompatActivity() {

    private lateinit var viewDataBinding: ActivityMainBinding
    private val coarsePermissionRequester =
        PermissionRequester(this, ACCESS_COARSE_LOCATION)

    private val viewModel: MainViewModel by lifecycleScope.viewModel(this)

    private val moviesAdapter: MovieAdapter by lazy {
        MovieAdapter(ItemViewHolder.ItemClick {
            viewModel.onContentClicked(it)
        })
    }

    private val tvAdapter: TvAdapter by lazy {
        TvAdapter(ItemViewHolder.ItemClick {
            viewModel.onContentClicked(it)
        })
    }

    private val principalAdapter: ConcatAdapter = ConcatAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        initRecyclerView(viewDataBinding.listItem)
        setContentView(viewDataBinding.root)
        viewModel.model.observe(this, Observer(::updateUi))
    }

    private fun initRecyclerView(listItem: RecyclerView) {
        principalAdapter.addAdapter(moviesAdapter)
        principalAdapter.addAdapter(tvAdapter)

        val gridLayoutManager = GridLayoutManager(listItem.context, SPAN_COUNT)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return ITEM_SPAN_SIZE
            }
        }
        with(listItem) {
            this.adapter = this@MainActivity.principalAdapter
            this.layoutManager = gridLayoutManager
        }
    }

    private fun updateUi(state: MainState) {
        when (state) {
            is MainState.Movies -> moviesAdapter.submitList(state.movies)
            is MainState.TvShows -> tvAdapter.submitList(state.tvShows)
            is MainState.Navigation -> {
                Toast.makeText(this, "Go to ${state.content.name}", Toast.LENGTH_SHORT).show()
            }
            MainState.RequestLocationPermission -> coarsePermissionRequester.request {
                viewModel.onCoarsePermissionRequested()
            }
            MainState.Loading -> viewDataBinding.loading = true
            MainState.Complete -> viewDataBinding.loading = false
        }
    }

    companion object {
        private const val SPAN_COUNT = 3
        private const val ITEM_SPAN_SIZE = 1
    }
}
