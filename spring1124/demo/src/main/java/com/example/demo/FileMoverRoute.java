package com.example.demo;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//定義springframework的@Component，將會執行；否則不會執行(現不執行)
// @Component
public class FileMoverRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        try {
            // noop=true表示將不刪掉此檔案
            from("file:///Users/june.wu/Desktop/cameltest/?FileName=a.txt&noop=true")
                    // .log("${headers}")
                    // .log("${body}")
                    .setBody(simple("${body}"))
                    .to("file:///Users/june.wu/Desktop/cameltest/?FileName=b.txt&noop=true")
                    .end();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
