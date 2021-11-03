package com.onban.kauantapp.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.onban.kauantapp.databinding.AnalysisVp2ItemBinding
import com.onban.kauantapp.view.DummyData

class AnalysisListAdapter : BaseListAdapter<DummyData, AnalysisVp2ItemBinding>(AnalysisDiffCallback) {
    override fun createBinding(parent: ViewGroup): AnalysisVp2ItemBinding {
        val inflater = LayoutInflater.from(parent.context)
        return AnalysisVp2ItemBinding.inflate(inflater, parent, false)
    }

    override fun initViewHolder(binding: AnalysisVp2ItemBinding, getItemPosition: () -> Int) {
    }

    override fun bind(holder: BaseViewHolder<AnalysisVp2ItemBinding>, position: Int) {
        with(holder.binding) {
            dummyData = getItem(position)
            executePendingBindings()
        }
    }
}

object AnalysisDiffCallback : DiffUtil.ItemCallback<DummyData>() {

    override fun areItemsTheSame(oldItem: DummyData, newItem: DummyData): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: DummyData, newItem: DummyData): Boolean {
        return oldItem.title == newItem.title
    }
}