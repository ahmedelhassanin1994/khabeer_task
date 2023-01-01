package com.example.room.domain.usecase

import arrow.core.Either
import com.example.khabeer_task.data.responeses.employee.DataEmployee_Response
import com.example.khabeer_task.domain.entities.User_Entities
import com.example.room.data.network.Failure
import com.raminabbasiiii.movies.domain.repository.Repository
import com.raminabbasiiii.movies.domain.usecase.BaseCase

class Employee_Usecase(private val repository : Repository) : BaseCase<InputData_Employee, DataEmployee_Response> {

    override suspend fun execute(input: InputData_Employee): Either<Failure,DataEmployee_Response> {
       return repository.get_Employee_Data(input.token)
    }
}

data class  InputData_Employee(
    val token :String,
    )