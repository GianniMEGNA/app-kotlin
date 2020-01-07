package megna.gianni.myapplication
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MyListAdapter()

        val adapter = MyListAdapter()
        recyclerView.adapter = adapter

        val okHttpClient by lazy {
            OkHttpClient.Builder()
                .addInterceptor{
                    chain -> val newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoxNzgsImV4cCI6MTYwOTg1OTI2Mn0.oM43rIj-3HyXrixGcn7nKdQZxj1WXBZiruhefzYecj8")
                    .build()
                    chain.proceed(newRequest)
                }
                .build()
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("http://android-tasks-api.herokuapp.com/api/v1/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var api = retrofit.create<API>()

        api.getTasks().enqueue(object : Callback<List<Tasks>> {

            override fun onResponse(call: Call<List<Tasks>>, response: Response<List<Tasks>>) {
                Log.d("DL" , "good")
                val posts = response.body()!!
                Log.d("DL" , posts[0].id )
                adapter.list = posts
                adapter.notifyDataSetChanged()

            }

            override fun onFailure(call: Call<List<Tasks>>, t : Throwable) {
                Log.d( "DL" , "Fail", t)
            }
        })

    }

}
