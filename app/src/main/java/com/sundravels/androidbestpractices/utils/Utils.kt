package com.sundravels.androidbestpractices.utils

import com.sundravels.androidbestpractices.state.UiState


fun<T> transformResponse(data:T) = UiState(data,true)
