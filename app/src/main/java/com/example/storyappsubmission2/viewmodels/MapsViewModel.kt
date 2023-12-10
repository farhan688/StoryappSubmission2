package com.example.storyappsubmission2.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storyappsubmission2.activity.getToken
import com.best.myappstory.myApi.ApiConfig
import com.example.storyappsubmission2.response.ListStoryItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MapsViewModel (context: Context): ViewModel() {

    private val _listStories = MutableLiveData<List<ListStoryItem>>()
    val listStories: LiveData<List<ListStoryItem>> = _listStories

    private fun getStoriesWithLoc(token: String) {
        try {
            viewModelScope.launch(Dispatchers.Default) {
                val client = ApiConfig.getApiService(token).getStoriesWithLoc()
                val response = client.listStory
                withContext(Dispatchers.Main) {
                    _listStories.value = response
                }
                Log.d(TAG, response.toString())
            }
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    init {
        getStoriesWithLoc(getToken(context))
    }

    companion object {
        const val TAG = "MapsActivity"
    }
}