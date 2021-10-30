package com.onban.kauantapp.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.onban.kauantapp.databinding.HomeLogoItemBinding
import com.onban.network.data.CompanyEntity

class HomeListAdapter : BaseListAdapter<CompanyEntity, HomeLogoItemBinding>(CompanyLogoDiffCallback) {

    override fun createBinding(parent: ViewGroup): HomeLogoItemBinding {
        val inflater = LayoutInflater.from(parent.context)
        return HomeLogoItemBinding.inflate(inflater, parent, false)
    }

    override fun initViewHolder(binding: HomeLogoItemBinding, getItemPosition: () -> Int) {
    }

    override fun bind(holder: BaseViewHolder<HomeLogoItemBinding>, position: Int) {
        with(holder.binding) {
            companyLogo = getItem(position)
            executePendingBindings()
        }
    }
}

object CompanyLogoDiffCallback : DiffUtil.ItemCallback<CompanyEntity>() {

    override fun areItemsTheSame(oldItem: CompanyEntity, newItem: CompanyEntity): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CompanyEntity, newItem: CompanyEntity): Boolean {
        return oldItem.name == newItem.name
    }
}