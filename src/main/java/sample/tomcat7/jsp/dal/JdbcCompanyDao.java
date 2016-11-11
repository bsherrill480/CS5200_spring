package sample.tomcat7.jsp.dal;

import sample.tomcat7.jsp.model.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by brian on 11/11/16.
 */
public class JdbcCompanyDao extends MyJdbcDaoSupport implements CompanyDao {

    @Override
    public Company create(Company company) {
		String sql = "INSERT INTO Companies VALUES (?, ?)";
		Connection conn = null;
		try {
		    conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, company.getCompanyName());
			ps.setString(2, company.getAbout());
			int affectedRows = ps.executeUpdate();
			if(affectedRows == 0) {
				throw new SQLException("Creating company failed, no rows affected.");
			}
			ps.close();
		    return company;
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
    public Company getCompanyByCompanyName(String companyName) {
		String sql = "SELECT * FROM Companies WHERE CompanyName = ?";

		Connection conn = null;

		try {
            conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, companyName);
			Company company = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				company = new Company(
						rs.getString("CompanyName"),
						rs.getString("About")
				);
			}
			rs.close();
			ps.close();
			return company;
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
    public Company updateAbout(Company company, String newAbout) {
		String sql = "UPDATE Companies SET About = ?";
		Connection conn = null;
		try {
            conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, company.getCompanyName());
			ps.executeUpdate();
			ps.close();
            company.setAbout(newAbout);
            return company;
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
    public Company delete(Company company) {
		String sql = "DELETE FROM Companies WHERE CompanyName = ?";

		Connection conn = null;

		try {
            conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, company.getCompanyName());
			ps.executeUpdate();
			ps.close();
			return company;
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
