package com.example.swipeproducts.domain.local.manager

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {

    suspend fun saveUserEntry()
    fun readUserEntry()  : Flow<Boolean>

}