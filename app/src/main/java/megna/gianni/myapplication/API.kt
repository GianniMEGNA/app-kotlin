package megna.gianni.myapplication

import retrofit2.Call
import retrofit2.http.GET

interface API {

    @GET("tasks/")
    fun getTasks() : Call<List<Tasks>>

    @GET("users")
    fun getUsers() : Call<List<User>>
}

