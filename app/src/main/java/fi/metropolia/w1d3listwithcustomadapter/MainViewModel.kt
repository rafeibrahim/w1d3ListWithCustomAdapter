package fi.metropolia.w1d3listwithcustomadapter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import kotlinx.coroutines.Dispatchers


class WikiRepository() {
    private  val call = WikiApi.service
    suspend fun getHitValue(name: String) = call.getHitCount(name)
}

class MainViewModel: ViewModel() {
    val repository: WikiRepository = WikiRepository()
    private val query = MutableLiveData<String>()

    fun queryName(name: String) { query.value = name}

    val hitCount = query.switchMap {
        liveData(Dispatchers.IO) {
            val hits = repository.getHitValue(it)
            emit(hits)
        }
    }
}

