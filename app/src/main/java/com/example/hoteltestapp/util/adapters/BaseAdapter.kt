package com.example.hoteltestapp.util.adapters

import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

@Suppress("PropertyName")
open class BaseAdapter<T: ViewBinding>:  RecyclerView.Adapter<BaseViewHolder<T>>() {

    var _binding: T? = null
    val binding: T get() = requireNotNull(_binding)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        return super.createViewHolder(parent,viewType)
    }

    override fun getItemCount(): Int = 0

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {}

}

class BaseViewHolder<T: ViewBinding> (binding: T) : RecyclerView.ViewHolder(binding.root)