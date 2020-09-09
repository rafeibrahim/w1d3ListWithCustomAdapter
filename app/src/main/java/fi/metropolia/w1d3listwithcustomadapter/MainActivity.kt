package fi.metropolia.w1d3listwithcustomadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var presidentToDisplay = GlobalModel.presidents[0]
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.queryName(presidentToDisplay.name.replace(" ", "%20", true))
        viewModel.hitCount.observe(this, Observer {
            presidentToDisplay.hits = it.query.searchinfo.totalhits
            presidentTextView.text = presidentToDisplay.toString()

        })
        presidentListView.adapter = PresidentCustomAdapter(this, GlobalModel.presidents)

        presidentListView.setOnItemClickListener{ _, _, p0, _ ->
            presidentTextView.text = GlobalModel.presidents[p0].toString()
            var presidentName = GlobalModel.presidents[p0].name
            presidentName = presidentName.replace(" ", "%20", true)
            viewModel.queryName(presidentName)
            Log.d("FYT", presidentName)
            viewModel.hitCount.observe(this, Observer {
                Log.d("FYT", it.query.searchinfo.totalhits.toString())
                GlobalModel.presidents[p0].hits = it.query.searchinfo.totalhits
                presidentTextView.text = GlobalModel.presidents[p0].toString()
            })
        }


    }
}
