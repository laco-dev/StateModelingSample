package com.laco.sample.state.data

import kotlinx.coroutines.delay
import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor() : UserRepository {

    override suspend fun getUser(): Result<User> = runCatching {
        delay(1000L)
        User("Name", 5)
    }
}
