package com.example.khabeer_task.ui.main

import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.example.khabeer_task.R
import com.example.khabeer_task.domain.entities.User_Entities
import com.example.khabeer_task.ui.main.splash.SplashDirections
import com.example.khabeer_task.util.BaseApplication
import com.example.room.domain.usecase.InputData_Login
import com.example.room.domain.usecase.Login_UseCase
import com.raminabbasiiii.movies.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.navigation.fragment.findNavController



@ExperimentalCoroutinesApi
@HiltViewModel
class MainViewModel
@Inject
constructor(private val loginUsecase: Login_UseCase) : ViewModel() {
    // TODO: Implement the ViewModel


    private val _UserEntities : MutableLiveData<Resource<User_Entities>> = MutableLiveData()
    val movieDetails : LiveData<Resource<User_Entities>> = _UserEntities
    private val TAG :String="MainViewModel"

    var phone = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var context: Context? = BaseApplication.applicationContext()



    fun Login()   {

         if (TextUtils.isEmpty(phone.value)) {
             Toast.makeText(context, R.string.enter_phone, Toast.LENGTH_SHORT).show()
             return
         } else if (phone.value!!.length < 11) {
             Toast.makeText(context, R.string.phone_content, Toast.LENGTH_SHORT).show()
             return
         } else if (TextUtils.isEmpty(password.value)) {
             Toast.makeText(context, R.string.enter_password, Toast.LENGTH_SHORT).show()
             return
         } else if (password.value!!.length < 6) {
             Toast.makeText(context, R.string.password_content, Toast.LENGTH_SHORT).show()
             return
         } else {
        viewModelScope.launch {

            loginUsecase.execute(InputData_Login("${phone.value}","${password.value}")).fold(
                {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()

                },
                {
                    Log.d(TAG, "Login success  :  ${it.Token}")

                    Toast.makeText(context, it.EnglishMessage, Toast.LENGTH_SHORT).show()

                    Resource.success(it)
                    _UserEntities.postValue(Resource.success(it))

                }
            )
        }
    }}

}