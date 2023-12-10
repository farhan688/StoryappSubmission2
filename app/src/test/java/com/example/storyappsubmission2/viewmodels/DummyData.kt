package com.example.storyappsubmission2.viewmodels

import com.example.storyappsubmission2.response.ListStoryItem

object DummyData {
    fun genDummyListOfStory(): List<ListStoryItem> {
        val items: MutableList<ListStoryItem> = arrayListOf()
        for (i in 0..100) {
            val story = ListStoryItem(
                "My Photo URL Story $i",
                "2023-11-22T22:22:22Z",
                "My Story $i ",
                "My Story Description",
                "$i",
                "0.2",
                "0.1"
            )
            items.add(story)
        }
        return items
    }
}