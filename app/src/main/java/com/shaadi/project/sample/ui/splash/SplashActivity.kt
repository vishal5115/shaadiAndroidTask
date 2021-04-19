package com.shaadi.project.sample.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.shaadi.project.sample.R
import com.shaadi.project.sample.di.component.ActivityComponent
import com.shaadi.project.sample.ui.base.BaseActivity
import com.shaadi.project.sample.ui.dummy.DummyActivity
import com.shaadi.project.sample.utils.common.Event

class SplashActivity : BaseActivity<SplashViewModel>() {

    companion object {
        const val TAG = "SplashActivity"
    }

    override fun provideLayoutId(): Int = R.layout.activity_splash

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
    }

    override fun setupObservers() {
        super.setupObservers()
        viewModel.launchDummy.observe(this, Observer<Event<Map<String, String>>> {
            it.getIfNotHandled()?.run {
                startActivity(Intent(applicationContext, DummyActivity::class.java))
            }
        })
    }
}