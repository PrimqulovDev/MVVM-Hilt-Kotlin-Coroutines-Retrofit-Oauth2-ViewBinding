package ilyos.app.mvvm_hilt_example.di.modules

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.createDataStore
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import ilyos.app.mvvm_hilt_example.App
import ilyos.app.mvvm_hilt_example.BuildConfig
import ilyos.app.mvvm_hilt_example.repo.api.auth.AuthServices
import ilyos.app.mvvm_hilt_example.repo.api.oauth2.AuthenticationInterceptor
import ilyos.app.mvvm_hilt_example.utils.preferences.SharedManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun provideJson(): Gson = Gson()

    @Singleton
    @Provides
    fun provideDateStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.createDataStore(name = "shared_data_store")
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext app: Context): SharedPreferences =
        app.getSharedPreferences("mvvm-hilt-example", Context.MODE_PRIVATE)


    fun buildOkHttpClient(context: Context, sharedManager: SharedManager): OkHttpClient {
        val builder = OkHttpClient.Builder()

        /**
         *
         * Build OkHttpClient
         *
         */

        // Adding OAuth2
        val authenticationInterceptor = AuthenticationInterceptor(sharedManager)
        builder.addInterceptor(authenticationInterceptor)
        return builder.build()
    }


    @Singleton
    @Provides
    fun provideOkHttpClient(@ApplicationContext context: Context, sharedManager: SharedManager): OkHttpClient =
        buildOkHttpClient(context, sharedManager)

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        val baseUrl = BuildConfig.BASE_URL
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun provideAuthServices(retrofit: Retrofit): AuthServices =
        retrofit.create(AuthServices::class.java)

}