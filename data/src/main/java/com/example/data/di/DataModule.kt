package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.api.CourseApi
import com.example.data.db.CourseDao
import com.example.data.db.CourseDataBase
import com.example.data.repository.CourseRepositoryImpl
import com.example.domain.repository.CourseRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindCourseRepository(
        impl: CourseRepositoryImpl
    ): CourseRepository

    companion object {

        private const val BASE_URL = "https://drive.google.com/"
        @Provides
        @Singleton
        fun provideGson(): Gson {
            return GsonBuilder()
                .setLenient()
                .create()
        }

        @Provides
        @Singleton
        fun provideOkHttpClient(): OkHttpClient {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()
        }

        @Provides
        @Singleton
        fun provideRetrofit(
            okHttpClient: OkHttpClient,
            gson: Gson
        ): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

        @Provides
        @Singleton
        fun provideCourseApi(retrofit: Retrofit): CourseApi {
            return retrofit.create(CourseApi::class.java)
        }

        @Provides
        @Singleton
        fun provideDataBase(
            @ApplicationContext context: Context
        ) : CourseDataBase {
            return Room.databaseBuilder(
                context = context,
                klass = CourseDataBase::class.java,
                name = "course_db"
            ).fallbackToDestructiveMigration(false)
                .build()
        }

        @Provides
        @Singleton
        fun provideCourseDao(
            courseDataBase: CourseDataBase
        ) : CourseDao {
            return courseDataBase.courseDao()
        }
    }
}