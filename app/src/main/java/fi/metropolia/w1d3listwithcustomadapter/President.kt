package fi.metropolia.w1d3listwithcustomadapter

class President (var name: String,
                 var startDuty: Int,
                 var endDuty: Int,
                 var description: String,
                 var hits: Int = 0) : Comparable<President> {
    override fun compareTo(other: President) : Int {
        return Integer.compare(this.startDuty, other.startDuty)
    }

    override fun toString() : String {
        return "$name $startDuty $endDuty        Hits: $hits \n$description"
    }
}