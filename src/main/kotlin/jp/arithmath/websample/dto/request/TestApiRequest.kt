package jp.arithmath.websample.dto.request

class TestApiRequest() {
    var header: Header? = null
    var body: Body? = null

    class Header(var srcId: String = "")
    class Body(var merchantId: String = "", var amount: String = "")
}