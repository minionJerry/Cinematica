package com.kanykeinu.cinematica.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T>(
    @LayoutRes val layoutId: Int,
    var listener: ItemClickListener<T>? = null
) : RecyclerView.Adapter<BaseAdapter.BaseHolder>() {

    var list: List<T> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    constructor(@LayoutRes layoutId: Int, list: List<T>, listener: ItemClickListener<T>? = null) : this(layoutId, listener) {
        this.list = list
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        return BaseHolder(
            LayoutInflater.from(parent.context).inflate(
                layoutId,
                parent,
                false
            )
        )
    }

    abstract fun onBindViewHolder(holder: BaseHolder, position: Int, item: T)

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        val item = list[position]
        holder.itemView.setOnClickListener {
            listener?.onClick(position, item)
        }
        onBindViewHolder(holder, position, item)
    }

    fun add(s: T, position: Int) {
        var position = position
        position = if (position == -1) itemCount else position
        (list as ArrayList).add(position, s)
        notifyItemInserted(position)
    }

    fun remove(position: Int) {
        if (position < itemCount) {
            (list as ArrayList).removeAt(position)
            notifyItemRemoved(position)
        }
    }

    class BaseHolder(private val containerView: View) : RecyclerView.ViewHolder(containerView){}

    interface ItemClickListener<T> {
        fun onClick(position: Int, item: T)
    }

}