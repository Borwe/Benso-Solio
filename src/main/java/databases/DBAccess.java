package databases;

import javax.servlet.ServletContext;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class DBAccess {
	
	private static SimpleDriverDataSource simpleDriverDataSource=null;
	private static JdbcTemplate jdbcTemplate;

	public static void init(ServletContext request) {
		if(simpleDriverDataSource==null) {
			simpleDriverDataSource=new SimpleDriverDataSource();
			
			//driver for sqlite
			simpleDriverDataSource.setDriverClass(org.sqlite.JDBC.class);
			
			//connect to sqlite
			String path=request.getRealPath("WEB-INF/solio.db");
			simpleDriverDataSource.setUrl("jdbc:sqlite:"+path);
		}
		
		if(jdbcTemplate==null) {
			jdbcTemplate=new JdbcTemplate(simpleDriverDataSource);
		}
	}
	
	public static JdbcTemplate getQuery() {
		if(jdbcTemplate==null) {
			jdbcTemplate=new JdbcTemplate(simpleDriverDataSource);
		}
		return jdbcTemplate;
	}
}
