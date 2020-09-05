package fi.metropolia.w1d3listwithcustomadapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.view.LayoutInflater
import android.widget.TextView


class PresidentCustomAdapter(context: Context, val presidents: MutableList<President>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return presidents.size
    }

    override fun getItem(p0: Int): Any {
        return presidents[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val row: View = inflater.inflate(R.layout.president_list_item, p2, false)

        val currentPresident: President = presidents[p0]

        var nameTextView = row.findViewById(R.id.presidentName) as TextView
        nameTextView.text = currentPresident.name

        var startYearView = row.findViewById(R.id.startDutyYear) as TextView
        startYearView.text = Integer.toString(currentPresident.startDuty)

        var endYearView = row.findViewById(R.id.endDutyYear) as TextView
        endYearView.text = Integer.toString(currentPresident.endDuty)

        return row
    }
}