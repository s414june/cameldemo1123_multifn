package com.example.demo;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//定義springframework的@Component，將會執行；否則不會執行(現會執行)
@Component
public class FolderMoverRoute extends RouteBuilder {
    @Override
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
}
