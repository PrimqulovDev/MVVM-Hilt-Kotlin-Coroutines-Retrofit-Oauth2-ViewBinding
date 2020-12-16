package ilyos.app.mvvm_hilt_example.di.modules

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class BindingsModule {

    @Singleton
    @Binds
    abstract  fun bindContext(@ApplicationContext context: Context): Context

}