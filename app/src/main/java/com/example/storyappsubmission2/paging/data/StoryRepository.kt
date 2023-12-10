package com.best.myappstory.myPaging.myData

import androidx.paging.*
import com.example.storyappsubmission2.api.ApiService
import com.example.storyappsubmission2.response.ListStoryItem
import kotlinx.coroutines.flow.Flow

class StoryRepository(private val apiService: ApiService) {
    fun getStory(): Flow<PagingData<ListStoryItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                StoryPagingSource(apiService)
            }
        ).flow
    }
}