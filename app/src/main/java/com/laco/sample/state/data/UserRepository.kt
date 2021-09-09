package com.laco.sample.state.data

interface UserRepository {

    suspend fun getUser(): Result<User>
}
