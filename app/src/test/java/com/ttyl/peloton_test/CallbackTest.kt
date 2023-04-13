package com.ttyl.peloton_test

import android.util.Log
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.Test
import retrofit2.Call
import retrofit2.Response
import java.lang.Exception

@RunWith(AndroidJUnit4::class)
class CallbackTest {

    @MockK
    var call: Call<Array<Item>> = mockk()

    @MockK
    var response: Response<Array<Item>> = mockk()

    @MockK
    var viewContract: ViewContract = mockk()

    @Test
    fun testOnResponse() {
        val ic = ItemCallback(viewContract)
        every { response.body() }returns arrayOf(Item("a", "b", "c", "d"))
        every { viewContract.updateAdapter(any()) } just Runs
        every { viewContract.dismissProgressBar() } just Runs
        ic.onResponse(call, response)
        verify { viewContract.updateAdapter(arrayOf(Item("a", "b", "c", "d"))) }
        verify {viewContract.dismissProgressBar()}
    }

    @Test
    fun testOnFailure() {
        val ic = ItemCallback(viewContract)
        mockkStatic(Log::class)
        every { response.body() } returns arrayOf(Item("a", "b", "c", "d"))
        every { viewContract.updateAdapter(any()) } just Runs
        every { viewContract.showEmptyList() } just Runs
        every { viewContract.dismissProgressBar() } just Runs
        ic.onFailure(call, Exception("failure"))
        verify { viewContract.updateAdapter(arrayOf()) }
        verify { viewContract.showEmptyList() }
        verify { viewContract.dismissProgressBar() }
        verify { Log.e("item callback failure", "error: failure") }
        unmockkStatic(Log::class)
    }

}