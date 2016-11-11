package sample.tomcat7.jsp.dal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.sql.DataSource;
import javax.annotation.PostConstruct;

/**
 * Created by brian on 11/10/16.
 */
public class MyJdbcDaoSupport extends JdbcDaoSupport {
	@Autowired
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}
}
