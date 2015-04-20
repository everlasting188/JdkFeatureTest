package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * JDBC��صĲ�����
 */
public class TransactionTest {

	public static void main(String[] args) {
		Connection connect = null;
		
		try {
			Class.forName("org.gjt.mm.mysql.Driver"); // ����MYSQL JDBC��������
			System.out.println("Success loading Mysql Driver!");
			
			// ����URLΪ jdbc:mysql//��������ַ/���ݿ����������2�������ֱ��ǵ�½�û���������
			connect = DriverManager.getConnection("jdbc:mysql://localhost/regist", "root", "root");
			System.out.println("Success connect Mysql server!");
			
			//�����ֶ��ύ
			connect.setAutoCommit(false);
			connect.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			
			//��ѯ����
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery("select * from device");
			while (rs.next()) {
				System.out.println(rs.getString("name"));

			}
			
			//�ύ
			connect.commit();
		} catch (Exception e) {
			System.out.print("get data error!");
			e.printStackTrace();
		}
	}
}
