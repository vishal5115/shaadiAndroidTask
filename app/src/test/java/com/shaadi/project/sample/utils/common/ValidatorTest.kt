package com.shaadi.project.sample.utils.common

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.hamcrest.Matchers.hasSize
import org.junit.Test

class ValidatorTest{


    @Test
    fun givenValidEmailAndValidPwd_whenValidate_shouldReturnSuccess(){
        val email = "email@gmail.com"
        val password = "123456"
        val validations = Validator.validateLoginFields(email,password)
        assertThat(validations,hasSize(2))
        assertThat(
            validations,
            contains(
                Validation(Validation.Field.EMAIL, Resource.success()),
                Validation(Validation.Field.PASSWORD, Resource.success())
            )
        )
    }
}