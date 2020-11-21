package com.santiago.cinematime.ui.adapter.diff

import androidx.recyclerview.widget.DiffUtil
import com.santiago.cinematime.ui.model.TvUiModel

object DiffItemTv : DiffUtil.ItemCallback<TvUiModel>() {
    override fun areItemsTheSame(oldItem: TvUiModel, newItem: TvUiModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TvUiModel, newItem: TvUiModel): Boolean {
        return oldItem.id == newItem.id
    }

}