package com.example.testapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testapp.data.AppDatabase
import com.example.testapp.data.dao.CategoryDao
import com.example.testapp.data.dao.ThemeDao
import com.example.testapp.data.model.ANIMAL_CATEGORY
import com.example.testapp.data.model.FUNNY_CATEGORY
import com.example.testapp.data.model.GALAXY_CATEGORY
import com.example.testapp.data.model.Theme
import com.example.testapp.data.repo.CategoryRepository
import com.example.testapp.data.repo.ThemeRepository
import com.example.testapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val database: AppDatabase
        get() = AppDatabase.provideAppDatabase(applicationContext)

    private val themDao: ThemeDao by lazy {
        database.themeDao()
    }

    private val categoryDao: CategoryDao by lazy {
        database.categoryDao()
    }

    private val themeRepository: ThemeRepository by lazy {
        ThemeRepository(themDao)
    }

    private val categoryRepository: CategoryRepository by lazy {
        CategoryRepository(categoryDao)
    }

    private val viewModel: MainViewModel by lazy {
        MainViewModel(themeRepository, categoryRepository)
    }

    private lateinit var vpAdapter: ListItemPagerAdapter
    private val listAllTheme = arrayListOf<Theme>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vpAdapter = ListItemPagerAdapter(this)
        observeViewModelLiveData()
        viewModel.getAllTheme()
    }

    private fun observeViewModelLiveData() {
        viewModel.themeData.observe(this) {
            listAllTheme.clear()
            listAllTheme.addAll(it)
            vpAdapter.populateData(listAllTheme)
        }

        viewModel.categoryData.observe(this) {
            viewModel.categoryData.value?.let { categoryList ->

                if (categoryList.any { it.name.contains(GALAXY_CATEGORY) }) {
                    val category = categoryList.first { it.name.contains(GALAXY_CATEGORY) }
                    vpAdapter.populateData(listAllTheme.filter { it.categoryId == category.id })
                }

                if (categoryList.any { it.name.contains(ANIMAL_CATEGORY) }) {
                    val category = categoryList.first { it.name.contains(ANIMAL_CATEGORY) }
                    vpAdapter.populateData(listAllTheme.filter { it.categoryId == category.id })
                }

                if (categoryList.any { it.name.contains(FUNNY_CATEGORY) }) {
                    val category = categoryList.first { it.name.contains(FUNNY_CATEGORY) }
                    vpAdapter.populateData(listAllTheme.filter { it.categoryId == category.id })
                }
            }
            initViewPager()
        }
    }

    private fun initViewPager() {
        binding.vpContent.adapter = vpAdapter
        TabLayoutMediator(binding.tlTabItems, binding.vpContent) { tab, pos ->
            when (pos) {
                ALL_CATEGORY_POSITION -> tab.text = "All"
                GALAXY_CATEGORY_POSITION -> tab.text = GALAXY_CATEGORY
                ANIMAL_CATEGORY_POSITION -> tab.text = ANIMAL_CATEGORY
                else -> tab.text = FUNNY_CATEGORY
            }
        }.attach()
    }

    companion object {
        const val ALL_CATEGORY_POSITION = 0
        const val GALAXY_CATEGORY_POSITION = 1
        const val ANIMAL_CATEGORY_POSITION = 2
        const val FUNNY_CATEGORY_POSITION = 3
    }
}