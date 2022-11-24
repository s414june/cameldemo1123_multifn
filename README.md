# cameldemo1123_multifn
## Camel整合框架Demo

自2022/11/23開始記錄

一個功能一個Java檔

檔案初始化

請使用指令 `mvn clean install`

(先清除再安裝)

### Spring Boot 專案運行

使用命令行運行請輸入

`mvn spring-boot:run`

### 一般 Java 專案運行

`mvn exec:java`

請在pom.xml設定初始專案(exec-maven-plugin中，mainClass的值)

或是使用

`mvn exec:java -Dexec.mainClass="test.example.RunCamelConnectDB"` (test.example.RunCamelConnectDB改成你要執行的專案)