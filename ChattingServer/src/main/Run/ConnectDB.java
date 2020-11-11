package Run;
import java.sql.*;

public class ConnectDB {

	private static String url= "172.31.38.181:80/Carrot_Market";
	
	//데이터베이스 연결을 위한 생성자
	public ConnectDB() {
		try {
			Class.forName("con.mysql.cj.jdbc.Driver");
		
			}catch(ClassNotFoundException e){
				System.out.println("JDBC 에러 "+e);
			}
	
		try {
			//DB 연결
			Connection con= DriverManager.getConnection(url,"root","stver841012");
			System.out.println("DB 연결 성공");
			
			//커넥션 연결상태 변수
			Statement stmt = con.createStatement();
			//ResultSet rs = stmt.executeQuery("");
			
			
			
		}catch(java.lang.Exception e) {
			
			System.out.println("DB 연결 에러"+e);
		
		}
	
	}
	
	
	


}
