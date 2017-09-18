package jp.arithmath.websample.controller.web

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class IndexController {
    val logger = LoggerFactory.getLogger(javaClass)

    @RequestMapping("/")
    fun index(model: Model): String {
        logger.info("index")
        model.addAttribute("text","sample text")
        return "index";
    }

    @RequestMapping("/binary")
    fun binary(model: Model): String {
        logger.info("index")
        model.addAttribute("text","sample text")
        return "binary";
    }
}