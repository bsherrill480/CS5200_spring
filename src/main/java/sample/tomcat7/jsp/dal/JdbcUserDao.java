package sample.tomcat7.jsp.dal;

import org.springframework.stereotype.Service;
import sample.tomcat7.jsp.model.User;
import sample.tomcat7.jsp.model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Created by brian on 11/10/16.
 */
@Service
public class JdbcUserDao extends MyJdbcDaoSupport implements UserDao {
    @Override
    public User create(User user) {
        return null;
    }

    @Override
	public User getUserByUserName(String userName) {
		String sql = "SELECT * FROM Users WHERE UserName = ?";

		Connection conn = null;

		try {
            conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			User user = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new User(
						rs.getString("UserName"),
						rs.getString("Password"),
						rs.getString("FirstName"),
						rs.getString("LastName"),
						rs.getString("Email"),
						rs.getString("Phone")
				);
			}
			rs.close();
			ps.close();
			return user;
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

    @Override
    public User delete(User user) {
		String sql = "DELETE FROM Users WHERE UserName = ?";

		Connection conn = null;

		try {
            conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.executeUpdate();
			ps.close();
			return user;
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
