package com.example.inventory.exception

import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.server.ResponseStatusException
import javax.servlet.RequestDispatcher
import javax.servlet.http.HttpServletRequest

@Controller
class CustomErrorController : ErrorController {

    @RequestMapping("/error")
    fun error(request: HttpServletRequest): String {
        val status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)

        if (status != null) {
            val statusCode = Integer.valueOf(status.toString())
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error-404"
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error-500"
            }
        }
        return "error"
    }

//    @RequestMapping("/error")
//    fun error(request: HttpServletRequest): ResponseStatusException {
//        val status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)
//        val statusCode = Integer.valueOf(status.toString())
//
//        if (statusCode == HttpStatus.NOT_FOUND.value()) {
//            return ResponseStatusException(HttpStatus.NOT_FOUND)
//        } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
//            return ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR)
//        }
//        return ResponseStatusException(HttpStatus.NOT_FOUND)
//    }
}