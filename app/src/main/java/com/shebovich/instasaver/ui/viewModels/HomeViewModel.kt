package com.shebovich.instasaver.ui.viewModels

import androidx.lifecycle.ViewModel
import com.shebovich.instasaver.repository.FilesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val filesRepository: FilesRepository) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    fun parseMediaInstagram(url : String){
        println("RESULT parseMediaInstagram")

        compositeDisposable.add(filesRepository.downloadImage(url)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                       println("RESULT $it")
            },{}))
    }

    override fun onCleared() {
        compositeDisposable.dispose()
    }
}