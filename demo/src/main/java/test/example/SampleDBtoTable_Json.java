package test.example;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultRegistry;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class SampleDBtoTable_Json {
	public static final Logger log = Logger.getLogger(SampleDBtoTable_Json.class);

	public static void main(String[] args) throws Exception {
		// log檔設定請參考SQLDatabaseConnection.java
		String LogConfigPath = "src\\main\\java\\test\\example\\log4j.properties";
		PropertyConfigurator.configure(LogConfigPath);
		run();
		System.out.println("end");
	}

	static void run() {
		// sql連線設定請參考SQLDatabaseConnection.java
		SQLServerDataSource ds = new SQLServerDataSource();
		ds.setUser("june");
		ds.setPassword("0000");
		ds.setServerName("localhost\\\\\\\\SQLEXPRESS;");
		ds.setPortNumber(1433);
		ds.setDatabaseName("SampleDB");
		ds.setTrustServerCertificate(true);

		DataSource dataSource = ds;

		DefaultRegistry reg = new DefaultRegistry();
		reg.bind("SampleDBSource", dataSource);

		// 要另外設定一個新的SQLServerDataSource
		// (直接用ds去改會出錯，至於有沒有更簡潔的方法...現在懶得研究:3)
		SQLServerDataSource ds2 = new SQLServerDataSource();
		ds2.setUser("june");
		ds2.setPassword("0000");
		ds2.setServerName("localhost\\\\\\\\SQLEXPRESS;");
		ds2.setPortNumber(1433);
		ds2.setDatabaseName("testdb");
		ds2.setTrustServerCertificate(true);

		DataSource dataSource2 = ds2;
		reg.bind("testdbSource", dataSource2);

		CamelContext context = new DefaultCamelContext(reg);

		// 在這邊要直接自己手動建立一個Table_Json資料表(懶得寫sql判斷新增)
		// 裡面只需要一個欄位jsondata，就可以把SampleDB的Employees資料表的資料包成Json存在一欄
		try {
			context.addRoutes(new RouteBuilder() {

				public void configure() throws Exception {
					String sql = "SELECT [Name],[Location] FROM [Employees]";
					String insertSqlStr = "insert into [Table_Json] (jsondata) values('${body}'); ";
					from("timer://foo?repeatCount=1")
							.setBody(constant(sql))
							.to("jdbc:SampleDBSource")
							// 用split切開，將一次只存一筆(員工+國家)的資料；沒切將會把所有資料包成一筆
							.split(body())
							.process(
									new Processor() {
										public void process(Exchange exchange) throws Exception {
											String body = exchange.getIn().getBody(String.class);// 官方固定用法
											System.out.println(body);
											SampleDBtoTable_Json.log.debug(body);
										}

									})
							.setBody(simple(insertSqlStr))
							.to("jdbc:testdbSource");
				}
			});
			context.start();
			Thread.sleep(10000);
			context.close();

			System.out.println(getTimeNow());
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			context.stop();
			log.info("finally!");
		}
	}

	static String getTimeNow() {
		Date nowTime = new Date();
		SimpleDateFormat timeFormate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		return timeFormate.format(nowTime);
	}
}
