package Run;
import java.sql.*;

public class ConnectDB {

	private static String url= "172.31.38.181:80/Carrot_Market";
	
	//�����ͺ��̽� ������ ���� ������
	public ConnectDB() {
		try {
			Class.forName("con.mysql.cj.jdbc.Driver");
		
			}catch(ClassNotFoundException e){
				System.out.println("JDBC ���� "+e);
			}
	
		try {
			//DB ����
			Connection con= DriverManager.getConnection(url,"root","stver841012");
			System.out.println("DB ���� ����");
			
			//Ŀ�ؼ� ������� ����
			Statement stmt = con.createStatement();
			//ResultSet rs = stmt.executeQuery("");
			
			
			
		}catch(java.lang.Exception e) {
			
			System.out.println("DB ���� ����"+e);
		
		}
	
	}
	
	
	


}
