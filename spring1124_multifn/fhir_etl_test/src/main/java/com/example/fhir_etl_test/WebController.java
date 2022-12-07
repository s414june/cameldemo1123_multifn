package com.example.fhir_etl_test;

// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
/**
 * Created by javadeveloperzone on 15-12-2017.
 */
// @RestController
// // @RequestMapping("/api-2.0")
// public class WebController {
//     @RequestMapping("/demo")
//     public String getData(HttpServletRequest request, HttpServletResponse response){
//         return "a";
//     }
// }
@Controller
public class WebController {
    @GetMapping("/a")
    public String a() {
        return "a"; // 要導入的html
    } 
}