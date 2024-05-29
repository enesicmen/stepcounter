package com.icmen.stepcounter.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.icmen.stepcounter.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class SerializationModule {

    @Provides
    fun provideGson(gsonBuilder: GsonBuilder): Gson = gsonBuilder.create()

    @Provides
    fun provideGsonBuilder(): GsonBuilder {
        val gsonBuilder = GsonBuilder()
            .disableHtmlEscaping()
            .serializeNulls()
        if (BuildConfig.DEBUG) {
            gsonBuilder.setPrettyPrinting()
        }
        return gsonBuilder
    }
}