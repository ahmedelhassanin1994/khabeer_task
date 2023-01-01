package com.raminabbasiiii.movies.domain.repository

import arrow.core.Either
import com.example.khabeer_task.data.responeses.employee.DataEmployee_Response
import com.example.khabeer_task.domain.entities.DataEmployee_Entities
import com.example.khabeer_task.domain.entities.User_Entities
import com.example.room.data.network.Failure

interface Repository {


    suspend  fun Login(MobileNumber: String,Password : String): Either<Failure, User_Entities>
    suspend  fun get_Employee_Data(token :String): Either<Failure, DataEmployee_Response>


}