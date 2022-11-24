package test.example;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {

        System.out.println("Hello World!");
        // 這邊使用了SQLDatabaseConnection這類別來進行他的run2()函數
        SQLDatabaseConnection a = new SQLDatabaseConnection();
        a.run2();

        System.out.println("end!");
    }
}
