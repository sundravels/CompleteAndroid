package com.sundravels.androidbestpractices.state

import kotlinx.coroutines.flow.Flow


interface UserIntent{
    val  userIntent: Flow<UiIntent>

}