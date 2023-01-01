package com.raminabbasiiii.movies.data.repository

import Network
import arrow.core.Either
import com.example.khabeer_task.data.responeses.employee.DataEmployee_Response
import com.example.khabeer_task.domain.entities.DataEmployee_Entities
import com.example.khabeer_task.domain.entities.User_Entities
import com.example.khabeer_task.util.BaseApplication
import com.example.room.data.network.Failure
import com.raminabbasiiii.movies.data.data_source.RemoteDataSource
import com.raminabbasiiii.movies.data.mapper.toUser
import com.raminabbasiiii.movies.data.network.ResponseMessage
import com.raminabbasiiii.movies.data.responeses.Login_Request_Response
import com.raminabbasiiii.movies.domain.repository.Repository
import java.lang.Exception
import javax.inject.Inject

class MovieRepositoryImpl
@Inject
constructor(
    private val remoteDataSource: RemoteDataSource
    ): Repository {

    private val TAG="getMovieDetails";


    override suspend fun Login(
        MobileNumber: String,
        Password: String
    ): Either<Failure, User_Entities> {

        if (Network.checkConnectivity(BaseApplication.applicationContext())) {

            try {

                var response=remoteDataSource.Login(Login_Request_Response(MobileNumber,Password))
                if (response.Success==true){

                return Either.Right(remoteDataSource.Login(Login_Request_Response(MobileNumber,Password)).toUser())

                }else{
                    return Either.Left(Failure(-1,response.EnglishMessage))

                }

            }catch (e:Exception){
                return Either.Left(Failure(-1,e.message.toString()))
            }
        }
        return Either.Left(Failure(-1, ResponseMessage.SEND_TIMEOUT))

        }

    override suspend fun get_Employee_Data(token: String): Either<Failure, DataEmployee_Response> {
        if (Network.checkConnectivity(BaseApplication.applicationContext())) {

            try {

                var response=remoteDataSource.get_Employee_Data(token)
                if (response.Success==true){

                    return Either.Right(remoteDataSource.get_Employee_Data(token))

                }else{
                    return Either.Left(Failure(-1,response.EnglishMessage.toString()))

                }

            }catch (e:Exception){
                return Either.Left(Failure(-1,e.message.toString()))
            }
        }else{
        return Either.Left(Failure(-1, ResponseMessage.SEND_TIMEOUT))
    }

    }


}
