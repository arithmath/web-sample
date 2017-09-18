package jp.arithmath.websample.dto.response

class WebSocketResponse<T>(
        var resultCode: String = "",
        var resultMessage: String = "",
        var data: T? = null
)