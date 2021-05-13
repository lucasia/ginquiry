package com.lucasia.ginquiry.dao;

import com.lucasia.ginquiry.domain.DomainFactory
import com.lucasia.ginquiry.domain.User
import org.springframework.beans.factory.annotation.Autowired

class UserRepositoryTest : AbstractRepositoryTest<User>(DomainFactory.UserDomainFactory()) {

    @Autowired
    val userRepository: UserRepository? = null

    override fun getRepository() : UserRepository? {
        return userRepository
    }

}
