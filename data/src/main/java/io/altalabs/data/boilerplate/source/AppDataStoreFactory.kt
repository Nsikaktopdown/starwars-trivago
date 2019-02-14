package io.altalabs.data.boilerplate.source

import javax.inject.Inject

class AppDataStoreFactory @Inject constructor(private val appCacheDataStore: AppCacheDataStore,
                                              private  val appRemoteDataStore: AppRemoteDataStore){


    /**
     * Return an instance of Cache Data Store
     */
    open fun retrieveCacheDataStore(): AppCacheDataStore{
        return  appCacheDataStore
    }


    /**
     * Return an instance of Remote Data Store
     */
    open fun retrieveRemoteDataStore(): AppRemoteDataStore{
        return appRemoteDataStore
    }
}