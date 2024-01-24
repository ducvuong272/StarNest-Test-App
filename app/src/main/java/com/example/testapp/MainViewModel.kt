package com.example.testapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testapp.data.model.Category
import com.example.testapp.data.model.Theme
import com.example.testapp.data.repo.CategoryRepository
import com.example.testapp.data.repo.ThemeRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(private val themeRepository: ThemeRepository, private val categoryRepository: CategoryRepository) : ViewModel() {

    val themeData = MutableLiveData<List<Theme>>()
    val categoryData = MutableLiveData<List<Category>>()
    private val disposeOnClear = CompositeDisposable()

    fun getAllTheme() {
        themeRepository.getAllTheme()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                getAllCategory()
                themeData.postValue(it)
            }, {
                print(it.message)
            })
            .let { disposeOnClear.add(it) }
    }

    fun getAllCategory() {
        categoryRepository.getAllCategory()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                categoryData.postValue(it)
            }, {
                print(it.message)
            })
            .let { disposeOnClear.add(it) }
    }

    override fun onCleared() {
        super.onCleared()
        disposeOnClear.clear()
    }
}