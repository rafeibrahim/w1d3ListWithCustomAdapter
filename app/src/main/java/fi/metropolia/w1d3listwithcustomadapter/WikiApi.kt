package fi.metropolia.w1d3listwithcustomadapter

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object WikiApi {
    const val URL = "https://en.wikipedia.org/w/api.php?action=query&format=json&list=search&srsearch=Trump"

    object ModelWiki {
        data class Employee (
            val name: String,
            val email: String,
            val address: EmployeeAddress
        )

        data class EmployeeAddress (
            val city: String,
            val state: String
        )
    }

    interface Service {
        @GET("api.php")
    suspend fun userName(@Query("name") action: String): ModelWiki.Employee
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(Service::class.java)!!
}