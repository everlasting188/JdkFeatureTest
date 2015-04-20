package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * JDBC相关的测试类
 */
public class TransactionTest {

	public static void main(String[] args) {
		Connection connect = null;
		
		try {
			Class.forName("org.gjt.mm.mysql.Driver"); // 加载MYSQL JDBC驱动程序
			System.out.println("Success loading Mysql Driver!");
			
			// 连接URL为 jdbc:mysql//服务器地址/数据库名；后面的2个参数分别是登陆用户名和密码
			connect = DriverManager.getConnection("jdbc:mysql://localhost/regist", "root", "root");
			System.out.println("Success connect Mysql server!");
			
			//设置手动提交
			connect.setAutoCommit(false);
			connect.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			
			//查询数据
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery("select * from device");
			while (rs.next()) {
				System.out.println(rs.getString("name"));

			}
			
			//提交
			connect.commit();
		} catch (Exception e) {
			System.out.print("get data error!");
			e.printStackTrace();
		}
	}
}
