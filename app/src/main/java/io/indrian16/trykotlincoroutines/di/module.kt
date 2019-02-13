package io.indrian16.trykotlincoroutines.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import io.indrian16.trykotlincoroutines.data.repository.Repository
import io.indrian16.trykotlincoroutines.data.service.PostService
import io.indrian16.trykotlincoroutines.ui.post.PostViewModel
import io.indrian16.trykotlincoroutines.util.Constant
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val netModule = module {

    single {

        Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(PostService::class.java) }
}

val repoModule = module {

    single { Repository(get()) }
}

val appModule = module {

    factory { PostViewModel(get()) }
}