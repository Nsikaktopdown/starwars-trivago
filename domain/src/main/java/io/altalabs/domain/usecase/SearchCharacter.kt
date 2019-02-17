package io.altalabs.domain.usecase

import io.altalabs.domain.executor.PostExecutionThread
import io.altalabs.domain.executor.ThreadExecutor
import io.altalabs.domain.model.CharacterData
import io.altalabs.domain.repository.FilmRepository
import io.altalabs.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class SearchCharacter @Inject constructor(private val repository: FilmRepository,
                                          private val postExecutionThread: PostExecutionThread,
                                          private val threadExecutor: ThreadExecutor): SingleUseCase<List<CharacterData>, String>(threadExecutor, postExecutionThread){
    override fun buildUseCaseObservable(params: String?): Single<List<CharacterData>> {
        return  repository.search(params!!)
    }

}