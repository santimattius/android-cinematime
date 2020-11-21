package com.santiago.cinematime.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

fun ViewGroup.inflater(): LayoutInflater = LayoutInflater.from(this.context)

fun <T:ViewDataBinding> ViewGroup.inflateViewDataBinding(@LayoutRes layout:Int,attach:Boolean = false): T {
    return DataBindingUtil.inflate(inflater(),layout,this,attach)
}