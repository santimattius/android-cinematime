package com.santiago.cinematime.ui.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class ItemViewHolder<T, V>(protected val viewDataBinding: V) :
    RecyclerView.ViewHolder(viewDataBinding.root) where V : ViewDataBinding {

    var onClick: ItemClick<T>? = null

    open fun bind(item: T) {}

    fun onItemClick(item: T) {
        onClick?.onClick(item)
    }

    class ItemClick<T>(val block: (T) -> Unit) {
        /**
         * Called when a item is clicked
         *
         * @param item the item that was clicked
         */
        fun onClick(item: T) = block(item)
    }
}