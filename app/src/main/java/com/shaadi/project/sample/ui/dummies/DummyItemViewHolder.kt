package com.shaadi.project.sample.ui.dummies

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.shaadi.project.sample.R
import com.shaadi.project.sample.data.local.db.entity.NewDummy
import com.shaadi.project.sample.di.component.ViewHolderComponent
import com.shaadi.project.sample.ui.base.BaseItemViewHolder
import kotlinx.android.synthetic.main.item_view_dummies.view.*

class DummyItemViewHolder(parent: ViewGroup) :
        BaseItemViewHolder<NewDummy, DummyItemViewModel>(R.layout.item_view_dummies, parent) {

    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) {
        viewHolderComponent.inject(this)
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.name.observe(this, Observer {
            itemView.tv_head_line_dummy.text = it
        })

        viewModel.url.observe(this, Observer {
            Glide.with(itemView.context).load(it).into(itemView.iv_dummy)
        })

        viewModel.email.observe(this, Observer {
            itemView.tv_email.text = it
        })
    }


    override fun setupView(view: View) {
        view.accept.setOnClickListener {
            view.accept.text = itemView.context.getString(R.string.member_accepted)
            view.decline.visibility = View.GONE

        }

        view.decline.setOnClickListener {
            view.decline.text = itemView.context.getString(R.string.member_declined)
            view.accept.visibility = View.GONE
        }
    }
}