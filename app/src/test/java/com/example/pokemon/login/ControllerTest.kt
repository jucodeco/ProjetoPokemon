package com.example.pokemon.login

import com.example.pokemon.login.userRepository.UserRepository
import com.nhaarman.mockitokotlin2.*
import org.junit.Assert
import org.junit.Test

class ControllerTest {

    private val userRepository = mock<UserRepository>()
    private val controller = LoginController(userRepository = userRepository)


    @Test
    fun `test login if success`() {

        controller.onClikEntrar("jucodeco@gmail.com", "1234")
        verify(userRepository).save("jucodeco@gmail.com", 1234)
    }

    @Test(expected = InvalidLoginException::class)
    fun `test login when wrong email`() {
        controller.onClikEntrar("jucodeco9@gmail.com", "1234")
        Assert.fail()
    }

    @Test(expected = InvalidLoginException::class)
    fun `test login when wrong password`() {
        controller.onClikEntrar("jucodeco@gmail.com", "123444")
        Assert.fail()
    }
    @Test(expected = Exception::class)
    fun `test login when empty email and password `() {
        controller.onClikEntrar("", "")
        Assert.fail()
    }

    @Test
    fun `test getSenha when user repository have password saved `() {
        whenever(userRepository.getSenha()).doReturn(1234)
        Assert.assertEquals("1234", controller.getsenha())
    }
    @Test
    fun `test getSenha when user repository do not have password saved`() {
        whenever(userRepository.getSenha()).doReturn(Int.MIN_VALUE)
        Assert.assertEquals("", controller.getsenha())
    }

    @Test
    fun `test getEmail when user repository have password saved`() {
        whenever(userRepository.getEmail()).doReturn("jucodeco@gmail.com")
        Assert.assertEquals("jucodeco@gmail.com", controller.getemail())
    }
    @Test
    fun `test getEmail when user repository do not have email saved`() {
        whenever(userRepository.getEmail()).doReturn(null)
        Assert.assertEquals("", controller.getemail())
    }

}






