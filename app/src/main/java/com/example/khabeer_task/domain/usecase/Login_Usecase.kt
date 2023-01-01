package com.example.room.domain.usecase

import arrow.core.Either
import com.example.khabeer_task.domain.entities.User_Entities
import com.example.room.data.network.Failure
import com.raminabbasiiii.movies.domain.repository.Repository
import com.raminabbasiiii.movies.domain.usecase.BaseCase
import com.raminabbasiiii.movies.util.Resource
import kotlinx.coroutines.flow.Flow

class Login_UseCase(private val repository : Repository) : BaseCase<InputData_Login, User_Entities> {

    override suspend fun execute(input: InputData_Login): Either<Failure,User_Entities> {
       return repository.Login(input.MobileNumber,input.Password)
    }
}

data class  InputData_Login(
    val MobileNumber :String,
    val Password :String,


    )