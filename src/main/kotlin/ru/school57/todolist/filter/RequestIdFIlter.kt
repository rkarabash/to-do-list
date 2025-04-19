package ru.school57.todolist.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.MDC
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.UUID

@Component
class RequestIdFIlter: OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        try {
            MDC.put("requestId", UUID.randomUUID().toString())
            logger.debug("Получили ${request.method}-запрос по пути ${request.requestURI}")
            filterChain.doFilter(request, response)
        } finally {
            MDC.clear()
        }
    }
}