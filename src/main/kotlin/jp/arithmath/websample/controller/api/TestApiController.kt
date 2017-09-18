package jp.arithmath.websample.controller.api

import jp.arithmath.websample.dto.request.TestApiRequest
import jp.arithmath.websample.dto.response.TestApiResponse
import org.springframework.web.bind.annotation.*

@RestController
@ResponseBody
class TestApiController {
    @RequestMapping("/api/test", method = arrayOf(RequestMethod.POST))
    fun testApi(@RequestBody request: TestApiRequest): TestApiResponse {
        val resultCode: String
        val message: String
        Thread.sleep(3000)
        if(Math.random() < 0.5) {
            resultCode = "0000"
            message = String.format("success: srcId = %s, merchantId = %s, amount = %s", request.header?.srcId, request.body?.merchantId, request.body?.amount)
        } else {
            resultCode = "0001"
            message = "fail"
        }
        return TestApiResponse(resultCode, message)
    }
}