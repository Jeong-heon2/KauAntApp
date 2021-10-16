package com.onban.kauantapp.repo

import com.onban.network.datasource.RemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {
}