package com.santiago.cinematime.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.santiago.cinematime.R
import com.santiago.cinematime.ui.adapter.diff.DiffItemTv
import com.santiago.cinematime.ui.adapter.holder.TvViewHolder
import com.santiago.cinematime.ui.model.TvUiModel
import com.santiago.cinematime.ui.base.ItemViewHolder as Item

class TvAdapter(private val callback: Item.ItemClick<TvUiModel>): ListAdapter<TvUiModel, TvViewHolder>(
    DiffItemTv
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        return TvViewHolder.from(parent).apply { this.onClick = callback }
    }
    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_tv
    }
}