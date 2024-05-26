package com.example.swipeproducts.domain.usecases.app_entry

import com.example.swipeproducts.domain.local.manager.LocalUserManager


class SaveUserEntry(
    private val localUserManager: LocalUserManager
){
    suspend operator fun invoke(){
        localUserManager.saveUserEntry()
    }
}