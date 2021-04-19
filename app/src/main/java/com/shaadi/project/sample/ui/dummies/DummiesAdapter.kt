package com.shaadi.project.sample.ui.dummies

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.shaadi.project.sample.data.local.db.entity.NewDummy
import com.shaadi.project.sample.ui.base.BaseAdapter

class DummiesAdapter(
    parentLifecycle: Lifecycle,
    private val dummies: ArrayList<NewDummy>
) : BaseAdapter<NewDummy, DummyItemViewHolder>(parentLifecycle, dummies) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DummyItemViewHolder(parent)
}