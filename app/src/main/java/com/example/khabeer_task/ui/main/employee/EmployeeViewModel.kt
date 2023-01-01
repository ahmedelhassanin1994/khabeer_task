package com.example.khabeer_task.ui.main.employee

import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.khabeer_task.R
import com.example.khabeer_task.data.responeses.employee.DataEmployee_Response
import com.example.khabeer_task.domain.entities.User_Entities
import com.example.room.domain.usecase.Employee_Usecase
import com.example.room.domain.usecase.InputData_Employee
import com.example.room.domain.usecase.InputData_Login
import com.example.room.domain.usecase.Login_UseCase
import com.raminabbasiiii.movies.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject


@ExperimentalCoroutinesApi
@HiltViewModel
class EmployeeViewModel @Inject
constructor(private val employee_Usecase: Employee_Usecase)  : ViewModel() {



    private val _UserEntities : MutableLiveData<Resource<DataEmployee_Response>> = MutableLiveData()
    val movieDetails : LiveData<Resource<DataEmployee_Response>> = _UserEntities
    private val TAG :String="EmployeeViewModel"



    fun setToken(token : String) {
        get_data(token)
    }

    fun get_data(token : String)   {
        
            viewModelScope.launch {

                employee_Usecase.execute(InputData_Employee("${token}")).fold(
                    {

                        Log.d(TAG, "get_data:  ${it.message}")

                    },
                    {
                        Log.d(TAG, "get_data:  ${it.EnglishMessage}")
                        Resource.success(it)
                        _UserEntities.postValue(Resource.success(it))

                    }
                )
            }
        }


}