package com.santiago.cinematime.ui.adapter.holder

import android.view.ViewGroup
import com.santiago.cinematime.common.inflater
import com.santiago.cinematime.databinding.ItemMovieBinding
import com.santiago.cinematime.ui.model.MovieUiModel
import com.santiago.cinematime.ui.base.ItemViewHolder

class MovieViewHolder(binding: ItemMovieBinding) :
    ItemViewHolder<MovieUiModel, ItemMovieBinding>(binding) {

    override fun bind(item: MovieUiModel) {
        super.bind(item)
        viewDataBinding.setOnItemClick { onItemClick(item) }
        viewDataBinding.item = item
    }

    companion object {

        fun from(parent: ViewGroup): MovieViewHolder {
            val viewDataBinding = ItemMovieBinding.inflate(parent.inflater(), parent, false)
            return MovieViewHolder(
                viewDataBinding
            )
        }
    }

}
