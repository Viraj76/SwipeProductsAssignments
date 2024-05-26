package com.example.swipeproducts.domain.usecases.app_entry

import com.example.swipeproducts.domain.local.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadUserEntry (
    private val localUserManager: LocalUserManager
){
    operator fun invoke() : Flow<Boolean> {
        return localUserManager.readUserEntry()
    }
}