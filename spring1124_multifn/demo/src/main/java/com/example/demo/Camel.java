package com.example.demo;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class Camel {
    CamelContext context = new DefaultCamelContext();
    // private static final Logger log = Logger.getLogger(Camel.class);
    private static final Logger log = LoggerFactory.getLogger(Camel.class);
    public void run1() {
        System.out.println("哈囉");
        log.info("哈囉");
        try {
            context.addRoutes(new RouteBuilder() {
                public void configure() throws Exception {
                    try {
                        // noop=true表示將不刪掉此檔案
                        from("file:///Users/june.wu/Desktop/cameltest/fileMove?noop=true")
                                .log("${headers}")
                                .log("${body}")
                                // 若無fileMovePlace資料夾會自動新增
                                .to("file:///Users/june.wu/Desktop/cameltest/fileMove/fileMovePlace")
                                .end();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            });
            context.start();
            Thread.sleep(10000);
            context.close();
        } catch (Exception e) {
            log.error(e.getStackTrace().toString());
        } finally {
            context.stop();
            log.info("finally!");
        }
    }
}
