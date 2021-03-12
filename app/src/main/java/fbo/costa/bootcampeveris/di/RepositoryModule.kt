package fbo.costa.bootcampeveris.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fbo.costa.bootcampeveris.repository.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepositoryChallenge1(
    ) = RepositoryChallenge1()

    @Singleton
    @Provides
    fun provideRepositoryChallenge2(
    ) = RepositoryChallenge2()
}
