package fi.metropolia.w1d3listwithcustomadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presidentListView.adapter = PresidentCustomAdapter(this, GlobalModel.presidents)


        presidentListView.setOnItemClickListener{ _, _, p0, _ ->
            presidentTextView.text = GlobalModel.presidents[p0].toString()
        }
    }
}