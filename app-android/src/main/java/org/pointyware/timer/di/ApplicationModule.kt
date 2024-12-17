package org.pointyware.timer.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.koin.core.Koin
import org.koin.mp.KoinPlatform.getKoin
import org.pointyware.timer.data.TaskRepository
import org.pointyware.timer.interactors.CreateRecordUseCase
import javax.inject.Singleton

/**
 */
@Module
@InstallIn(SingletonComponent::class)
interface ApplicationModule {

    companion object {
        @Provides
        @Singleton
        fun provideKoin(): Koin = getKoin()

        @Provides
        @Singleton
        fun provideTaskRepository(koin: Koin): TaskRepository {
            return koin.get()
        }

        @Provides
        fun provideCreateRecordUseCase(koin: Koin): CreateRecordUseCase {
            return koin.get()
        }
    }
}
