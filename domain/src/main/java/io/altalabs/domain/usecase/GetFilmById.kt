package io.altalabs.domain.usecase

import io.altalabs.domain.executor.PostExecutionThread
import io.altalabs.domain.executor.ThreadExecutor
import io.altalabs.domain.model.FilmData
import io.altalabs.domain.repository.FilmRepository
import io.altalabs.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetFilmById @Inject constructor(private val repository: FilmRepository,
                                      private val postExecutionThread: PostExecutionThread,
                                      private val threadExecutor: ThreadExecutor): SingleUseCase<FilmData, Int>(threadExecutor, postExecutionThread){
    override fun buildUseCaseObservable(params: Int?): Single<FilmData> {
        return repository.getCharacterFilmWithId(params!!)
    }

}