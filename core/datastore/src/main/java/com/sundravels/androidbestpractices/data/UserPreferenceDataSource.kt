package com.sundravels.androidbestpractices.data

import androidx.datastore.core.DataStore
import com.example.model.data.FavouritesDesserts
import com.sundravels.androidbestpractices.core.datastore.UserPreferencesOuterClass.UserPreferences
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class UserPreferenceDataSource @Inject constructor(private val dataStore: DataStore<UserPreferences>) {


    val data = dataStore.data.map {
        FavouritesDesserts(favouriteImagesIds = it.favouritesIdMap.keys)
    }


    suspend fun setFavourites(name: String, isFavourites: Boolean) {
        dataStore.updateData {
            with(it.toBuilder()) {
                if (isFavourites) {
                    putFavouritesId(name, isFavourites).build()
                } else removeFavouritesId(name).build()
            }
        }
    }

}