package arete.arete.volleyballstatkeeper.di

import arete.arete.volleyballstatkeeper.repositories.GameRepository
import arete.arete.volleyballstatkeeper.repositories.GameRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGameRepository(): GameRepository {
        return GameRepositoryImpl()
    }

}