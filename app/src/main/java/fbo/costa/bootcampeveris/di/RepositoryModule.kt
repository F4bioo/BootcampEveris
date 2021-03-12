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

    @Singleton
    @Provides
    fun provideRepositoryChallenge3(
    ) = RepositoryChallenge3()

    @Singleton
    @Provides
    fun provideRepositoryChallenge4(
    ) = RepositoryChallenge4()

    @Singleton
    @Provides
    fun provideRepositoryChallenge5(
    ) = RepositoryChallenge5()

    @Singleton
    @Provides
    fun provideRepositoryChallenge6(
    ) = RepositoryChallenge6()

    @Singleton
    @Provides
    fun provideRepositoryChallenge7(
    ) = RepositoryChallenge7()

    @Singleton
    @Provides
    fun provideRepositoryChallenge8(
    ) = RepositoryChallenge8()

    @Singleton
    @Provides
    fun provideRepositoryChallenge9(
    ) = RepositoryChallenge9()

    @Singleton
    @Provides
    fun provideRepositoryChallenge10(
    ) = RepositoryChallenge10()
}
