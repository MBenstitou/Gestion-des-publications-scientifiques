package connection;
import java.sql.*;
public class MyConnection {

	//Cette classe permet d'etablir la connection avec la base de données
	
	//Information d'accès à la base de données
	String url = "jdbc:mysql://localhost/dbgestionarticles";
	String login = "root";
	String passwd = "";
	private  Connection cn  ;
	private  Statement st ;
	private  ResultSet rs;
	
	
	public MyConnection() {
		
		
		try {
			
			//Etape 1 : chargement du driver 
			Class.forName("com.mysql.jdbc.Driver");
			//Etape 2 : recupération de la connection
			cn = DriverManager.getConnection(url, login, passwd);
			
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void fermerConnection(){
		
		try {
			cn.close();
			st.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	public Connection getCn() {
		return cn;
	}

	public void setCn(Connection cn) {
		this.cn = cn;
	}

	public Statement getSt() {
		return st;
	}

	public void setSt(Statement st) {
		this.st = st;
	}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
	
		
	}

	



