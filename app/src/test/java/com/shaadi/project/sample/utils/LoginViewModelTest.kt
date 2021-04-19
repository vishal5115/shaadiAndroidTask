package com.shaadi.project.sample.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.shaadi.project.sample.data.model.User
import com.shaadi.project.sample.data.repository.UserRepository
import com.shaadi.project.sample.ui.login.LoginViewModel
import com.shaadi.project.sample.utils.common.Event
import com.shaadi.project.sample.utils.common.Resource
import com.shaadi.project.sample.utils.network.NetworkHelper
import com.shaadi.project.sample.utils.rx.TestSchedulerProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var networkHelper: NetworkHelper

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var loggingInObserver: Observer<Boolean>

    @Mock
    private lateinit var launchDummyObserver: Observer<Event<Map<String,String>>>

    @Mock
    private lateinit var messageStringIdObserver: Observer<Resource<Int>>

    private lateinit var testScheduler: TestScheduler

    private lateinit var loginViewModel: LoginViewModel

    @Before
    fun setUp(){
        val compositeDisposable =  CompositeDisposable()
        testScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(testScheduler)
        loginViewModel = LoginViewModel(
            testSchedulerProvider,
            compositeDisposable,
            networkHelper,
            userRepository
        )
        loginViewModel.loggingIn.observeForever(loggingInObserver)
        loginViewModel.launchDummy.observeForever(launchDummyObserver)
        loginViewModel.messageStringId.observeForever(messageStringIdObserver)
    }


    @Test
    fun givenServerResponse200_whenLogin_shouldLaunchDummyActivity(){
        val email = "garg@gmail.com"
        val password = "password"
        val user = User("id","test",email,password,"accessToken")

        loginViewModel.emailField.value = email
        loginViewModel.passwordField.value = password

        doReturn(true).`when`(networkHelper).isNetworkConnected()

        doReturn(Single.just(user))
                .`when`(userRepository)
                .doUserLogin(email,password)

        loginViewModel.onLogin()
        testScheduler.triggerActions()
        verify(userRepository).saveCurrentUser(user)
        assert(loginViewModel.loggingIn.value == false)
        assert(loginViewModel.launchDummy.value == Event(hashMapOf<String,String>()))
        verify(loggingInObserver).onChanged(true)
        verify(loggingInObserver).onChanged(false)
        verify(launchDummyObserver).onChanged(Event(hashMapOf()))

    }




    @After
    fun tearDown(){
        loginViewModel.loggingIn.removeObserver(loggingInObserver)
        loginViewModel.launchDummy.removeObserver(launchDummyObserver)
        loginViewModel.messageStringId.removeObserver(messageStringIdObserver)
    }

}