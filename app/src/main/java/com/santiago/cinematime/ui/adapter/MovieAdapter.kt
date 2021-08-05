package com.santiago.cinematime.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.santiago.cinematime.R
import com.santiago.cinematime.ui.adapter.diff.DiffItemMovie
import com.santiago.cinematime.ui.model.MovieUiModel
import com.santiago.cinematime.ui.adapter.holder.MovieViewHolder
import com.santiago.cinematime.ui.adapter.holder.ItemViewHolder as Item

class MovieAdapter(private val callback: Item.ItemClick<MovieUiModel>): ListAdapter<MovieUiModel, MovieViewHolder>(
    DiffItemMovie
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.from(parent).apply { this.onClick = callback }
    }
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_movie
    }
}