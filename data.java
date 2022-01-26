package QL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class data {
    static Connection connection;
	    public static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	//localhost:<PORT>  databaseName=<Tên của database vừa tạo>
	    public static String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=QLMaybay";

	// Vẫn chừa trống, mặc dù không dùng
	    public static String dbUser = "sa";
	    public static String dbPass = "232003";
	    
	    public static Connection getConnect() throws ClassNotFoundException, SQLException {
	    	Class.forName(data.driverName);
	    	connection = DriverManager.getConnection(data.dbURL, data.dbUser, data.dbPass);
	    	return connection;
	        }       
}
    