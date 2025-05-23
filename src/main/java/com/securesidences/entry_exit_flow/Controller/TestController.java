package com.securesidences.entry_exit_flow.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test-origin")
    public String testOrigin(HttpServletRequest request) {
        String origin = request.getHeader("Origin");
        System.out.println("Request Origin: " + origin);
        return "Origin logged";
    }
}
