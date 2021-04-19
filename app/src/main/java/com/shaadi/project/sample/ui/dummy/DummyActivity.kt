package com.shaadi.project.sample.ui.dummy

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.shaadi.project.sample.R
import com.shaadi.project.sample.di.component.ActivityComponent
import com.shaadi.project.sample.ui.base.BaseActivity
import com.shaadi.project.sample.ui.dummies.DummiesAdapter
import kotlinx.android.synthetic.main.activity_dummy.*
import javax.inject.Inject

class DummyActivity : BaseActivity<DummyViewModel>() {

    companion object {
        const val TAG = "DummyActivity"
    }

    override fun provideLayoutId(): Int = R.layout.activity_dummy

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        rv_dummy.layoutManager = linearLayoutManager
        rv_dummy.adapter = dummiesAdapter
    }

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var dummiesAdapter: DummiesAdapter

    private fun addDummiesFragment() {

    }

    override fun setupObservers() {
        super.setupObservers()
        viewModel.getDummies().observe(this, Observer {
            it?.run { dummiesAdapter.appendData(this) }
        })
    }

}