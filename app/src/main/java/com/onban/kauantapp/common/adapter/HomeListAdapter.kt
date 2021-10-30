package com.onban.kauantapp.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.onban.kauantapp.data.CompanyLogo
import com.onban.kauantapp.databinding.HomeLogoItemBinding

class HomeListAdapter : BaseListAdapter<CompanyLogo, HomeLogoItemBinding>(CompanyLogoDiffCallback) {

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

object CompanyLogoDiffCallback : DiffUtil.ItemCallback<CompanyLogo>() {

    override fun areItemsTheSame(oldItem: CompanyLogo, newItem: CompanyLogo): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CompanyLogo, newItem: CompanyLogo): Boolean {
        return oldItem.name == newItem.name
    }
}