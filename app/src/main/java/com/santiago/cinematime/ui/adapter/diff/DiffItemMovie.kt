package com.santiago.cinematime.ui.adapter.diff

import androidx.recyclerview.widget.DiffUtil
import com.santiago.cinematime.ui.model.MovieUiModel

object DiffItemMovie : DiffUtil.ItemCallback<MovieUiModel>() {
    override fun areItemsTheSame(oldItem: MovieUiModel, newItem: MovieUiModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieUiModel, newItem: MovieUiModel): Boolean {
        return oldItem.id == newItem.id
    }

}