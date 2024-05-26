package com.example.swipeproducts.domain.usecases.app_entry.data_classes

import com.example.swipeproducts.domain.usecases.app_entry.ReadUserEntry
import com.example.swipeproducts.domain.usecases.app_entry.SaveUserEntry

data class AppEntryUseCases(
    val readUserEntry: ReadUserEntry,
    val saveUserEntry: SaveUserEntry

)