package com.raminabbasiiii.movies.data.data_source

import com.example.khabeer_task.data.responeses.employee.DataEmployee_Response
import com.raminabbasiiii.movies.data.responeses.Login_Request_Response
import com.raminabbasiiii.movies.data.responeses.MovieDto
import com.raminabbasiiii.movies.data.responeses.User_Response
import com.raminabbasiiii.movies.domain.entities.Movie
import com.raminabbasiiii.movies.util.Resource
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    suspend fun Login(loginRequestResponse: Login_Request_Response): User_Response
    suspend fun get_Employee_Data(token :String): DataEmployee_Response


}