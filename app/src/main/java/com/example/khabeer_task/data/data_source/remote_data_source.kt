package com.raminabbasiiii.movies.data.data_source


import com.example.khabeer_task.data.responeses.employee.DataEmployee_Response
import com.raminabbasiiii.movies.data.network.Api
import com.raminabbasiiii.movies.data.responeses.Login_Request_Response
import com.raminabbasiiii.movies.data.responeses.User_Response
import javax.inject.Inject

class RemoteDataSourceImplementer
@Inject
constructor(
    private val api: Api
) : RemoteDataSource {


    override suspend fun Login(loginRequestResponse: Login_Request_Response): User_Response {
        return api.Login(loginRequestResponse);
    }

    override suspend fun get_Employee_Data(token: String): DataEmployee_Response {

        return api.get_Employee_Data(token)
    }


}
