package io.altalabs.androidbase.ui.search

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.altalabs.androidbase.Data
import io.altalabs.androidbase.DataState
import io.altalabs.domain.model.CharacterData
import io.altalabs.domain.usecase.SearchCharacter
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class SearchActivityViewModel @Inject constructor(private val searchCharacter: SearchCharacter,
                                                  private  val searchCharaterMapper: SearcCharacterMapper): ViewModel(){

    var results = MutableLiveData<Data<List<CharacterModel>>>()


    fun searchForCharacter(query: String){
        results.postValue(Data(dataState = DataState.LOADING, data = null, message = " " ))
        searchCharacter.execute(SearchSubscriber(), query)
    }

    inner  class SearchSubscriber: DisposableSingleObserver<List<CharacterData>>(){
        override fun onSuccess(t: List<CharacterData>) {
            results.postValue(Data(dataState = DataState.SUCCESS, data = searchCharaterMapper.mapFromDomain(t), message = "     " ))
        }

        override fun onError(e: Throwable) {
            results.postValue(Data(dataState = DataState.ERROR, data = null, message =e.message ))
        }

    }
}