package com.example.fhir_etl_test;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchDbController {
    @PostMapping("/api/searchdb")
    public ResponseEntity<?> getSearchResultViaAjax(
        @Validated @RequestBody String search, Errors errors) {

        String result = "嗨，這邊要長資料庫！！！";

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            return ResponseEntity.badRequest().body(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));

        }

        return ResponseEntity.ok(result);
    }
}

