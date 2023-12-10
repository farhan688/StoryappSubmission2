package com.example.storyappsubmission2.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.best.myappstory.myPaging.myAdapter.LoadingStateAdapter
import com.best.myappstory.myPaging.myAdapter.StoryListAdapter
import com.example.storyappsubmission2.R
import com.example.storyappsubmission2.databinding.ActivityMainBinding
import com.example.storyappsubmission2.stories.Story
import com.example.storyappsubmission2.viewmodels.MainViewModel
import com.example.storyappsubmission2.viewmodels.ViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels {
        ViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvStories.layoutManager = LinearLayoutManager(this)

        getData()

        binding.fabAddStory.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

        val menuSettingFab: FloatingActionButton = findViewById(R.id.menu_setting)
        val menuMapsFab: FloatingActionButton = findViewById(R.id.menu_maps)

        menuSettingFab.setOnClickListener {
            getSharedPreferences("LoginSession", Context.MODE_PRIVATE).edit().clear().apply()
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }

        menuMapsFab.setOnClickListener {
            val intent = Intent(this@MainActivity, MapsActivity::class.java)
            intent.putExtra("origin", "FromActionBar")
            startActivity(intent)
        }
    }

    private fun getData() {
        val adapter = StoryListAdapter()
        binding.rvStories.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                adapter.retry()
            }
        )

        viewModel.story.observe(this) { pagingData ->
            adapter.submitData(lifecycle, pagingData)
        }

        adapter.setOnItemClickCallback(object: StoryListAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Story) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_STORY, data)
                startActivity(intent)
            }

            override fun onItemMapsClicked(data: Story) {
                val intent = Intent(this@MainActivity, MapsActivity::class.java)
                intent.putExtra("origin", "FromListStory")
                intent.putExtra(MapsActivity.EXTRA_STORY, data)
                startActivity(intent)
            }
        })
    }
}