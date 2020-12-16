package ilyos.app.mvvm_hilt_example.di.modules

import androidx.lifecycle.MutableLiveData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


/**
 * Developed by Ilyos
 */

@InstallIn(ApplicationComponent::class)
@Module
object ViewModelFieldsModule {

    @Provides
    @Singleton
    fun provideMutableLiveData() = MutableLiveData<Any>()

}