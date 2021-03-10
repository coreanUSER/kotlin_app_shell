package com.ghn.shell.viewmodels

import androidx.lifecycle.*
import com.ghn.shell.data.Sample
import com.ghn.shell.data.SampleRepository
import kotlinx.coroutines.launch

class SampleViewModel(private val repository: SampleRepository) : ViewModel() {

    // - We can put an observer on the data (instead of polling for changes) and only update the the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allSamples: LiveData<List<Sample>> = repository.allSamples().asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(sample: Sample) = viewModelScope.launch {
        repository.insert(sample)
    }
}

class SampleViewModelFactory(private val repository: SampleRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SampleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SampleViewModel(repository) as T
        }
        throw IllegalAccessException("Unknown ViewModel Class")
    }
}