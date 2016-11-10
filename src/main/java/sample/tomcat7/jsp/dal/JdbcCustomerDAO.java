package sample.tomcat7.jsp.dal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import sample.tomcat7.jsp.model.Customer;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@Repository
public class JdbcCustomerDAO implements CustomerDAO{
	private DataSource dataSource;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		System.out.println(dataSource);
	}

	public void insert(Customer customer){

		String sql = "INSERT INTO CUSTOMER " +
				"(CUST_ID, NAME, AGE) VALUES (?, ?, ?)";
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
//            conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, customer.getCustId());
			ps.setString(2, customer.getName());
			ps.setInt(3, customer.getAge());
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}

	public Customer findByCustomerId(int custId){

		String sql = "SELECT * FROM customer WHERE CUST_ID = ?";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
//            conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, custId);
			Customer customer = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				customer = new Customer(
					rs.getInt("CUST_ID"),
					rs.getString("NAME"),
					rs.getInt("AGE")
				);
			}
			rs.close();
			ps.close();
			return customer;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
	}
}
