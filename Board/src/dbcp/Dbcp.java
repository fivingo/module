package dbcp;
// DBCP (DataBaseConnectionPool)
import java.sql.*;
import javax.naming.*;
import javax.sql.DataSource;

public class Dbcp {
	static DataSource ds;
	
	static {
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static public Connection getConn() throws Exception {
		return ds.getConnection();
	}
}

// 호출 코드
//conn = db.Dbcp.getConn();