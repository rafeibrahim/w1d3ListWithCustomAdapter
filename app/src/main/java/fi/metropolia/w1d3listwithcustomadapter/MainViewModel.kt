package fi.metropolia.w1d3listwithcustomadapter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import kotlinx.coroutines.Dispatchers


class WikiRepository() {
    private  val call = WikiApi.service

    suspend fun hitCountCheck(name: String) = call.userName(name)
}

class MainViewModel: ViewModel() {
    val repository: WikiRepository = WikiRepository()
    private val query = MutableLiveData<String>()

    fun queryName(name: String) { query.value = name}

    val hitCount = query.switchMap {
        liveData(Dispatchers.IO) { emit(repository.hitCountCheck(it)) }
    }
}

