package com.santiago.cinematime.ui.adapter.holder

import android.view.ViewGroup
import com.santiago.cinematime.common.inflater
import com.santiago.cinematime.databinding.ItemTvBinding
import com.santiago.cinematime.ui.base.ItemViewHolder
import com.santiago.cinematime.ui.model.TvUiModel

class TvViewHolder(binding: ItemTvBinding) :
    ItemViewHolder<TvUiModel, ItemTvBinding>(binding) {

    override fun bind(item: TvUiModel) {
        super.bind(item)
        viewDataBinding.setOnItemClick { onItemClick(item) }
        viewDataBinding.item = item
    }

    companion object {

        fun from(parent: ViewGroup): TvViewHolder {
            val viewDataBinding = ItemTvBinding.inflate(parent.inflater(), parent, false)
            return TvViewHolder(
                viewDataBinding
            )
        }
    }

}
