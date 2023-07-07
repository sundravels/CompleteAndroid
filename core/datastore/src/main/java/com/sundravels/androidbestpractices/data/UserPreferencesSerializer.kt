package com.sundravels.androidbestpractices.data

import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.sundravels.androidbestpractices.core.datastore.UserPreferencesOuterClass.UserPreferences
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject


class UserPreferencesSerializer @Inject constructor():Serializer<UserPreferences> {
    override val defaultValue: UserPreferences = UserPreferences.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserPreferences {
        try {
            return UserPreferences.parseFrom(input)
        }catch (e:InvalidProtocolBufferException){
         throw  e
        }

    }

    override suspend fun writeTo(
        t: UserPreferences,
        output: OutputStream
    )  = t.writeTo(output)

}