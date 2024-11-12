package com.example.hydration

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WaterViewModel(private val repository: WaterRepository): ViewModel() {

    val allRecords = repository.getAllRecords().asLiveData()


    fun insertNewRecord(record: WaterRecord) {  // defines insertNewRecord function that takes a record: WaterRecord parameter
        viewModelScope.launch{  // viewModelScope means associate this suspend function with this particular viewModel.
            repository.insert(record)  // calls a coroutine function using launch because repository.insert(record) is a suspended function
        }
    }

    fun updateRecord(record: WaterRecord) {
        viewModelScope.launch {
            repository.update(record)
        }
    }

    fun deleteRecord(record: WaterRecord) {
        viewModelScope.launch {
            repository.delete(record)
        }
    }

    fun getRecordForDay(day: String): LiveData<WaterRecord> {
        return repository.getRecordForDay(day).asLiveData()
    }

//    fun getAllRecords(): LiveData<List<WaterRecord>> {
//        return repository.getAllRecords().asLiveData()
//    }

class WaterViewModelFactory(private val repository: WaterRepository): ViewModelProvider.Factory {  // Creates a factory class that makes objects.  ViewModelProvider will call the Factory subclass to make view model objects in custom ways
    override fun <T : ViewModel> create(modelClass: Class<T>): T {  // calls this function to create new objects.  T is a generic type (place holder) - it will be replaced with the specific class (WaterRepository in this case)
       if (modelClass.isAssignableFrom(WaterViewModel::class.java)) {  // if statement to check that this is only being called with WaterViewModels.  WaterViewModel::class.java refers to the class definition to make a new ViewModel object
            return WaterViewModel(repository) as T
       }
        throw IllegalArgumentException("$modelClass is not a WaterViewModel")
    }
}


}