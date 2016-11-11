package sample.tomcat7.jsp.dal;

import sample.tomcat7.jsp.model.Recommendation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by brian on 11/11/16.
 */
public class JdbcRecommendationDao extends MyJdbcDaoSupport implements RecommendationDao {
    @Override
    public Recommendation create(Recommendation recommendation) {
		String sql = "INSERT INTO Recommendations VALUES (?, ?)";
		Connection conn = null;
		try {
		    conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, recommendation.getUserName());
			ps.setInt(2, recommendation.getRestaurantId());
			int affectedRows = ps.executeUpdate();
			if(affectedRows == 0) {
				throw new SQLException("Creating restaurant failed, no rows affected.");
			}
			ResultSet generatedKeys = ps.getGeneratedKeys();
			if(generatedKeys.next()) {
				recommendation.setRecommendationId(generatedKeys.getInt(1));
			} else {
				throw new SQLException("Creating restaurant failed, no ID obtained.");
			}
			ps.close();
		    return recommendation;
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
    public Recommendation getRecommendationById(int recommendationId) {
		String sql = "SELECT * FROM Recommendations WHERE RecommendationId = ?";

		Connection conn = null;

		try {
            conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, recommendationId);
			Recommendation recommendation = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				recommendation = new Recommendation(
						rs.getInt("RecommendationId"),
						rs.getString("UserName"),
						rs.getInt("RestaurantId")
				);
			}
			rs.close();
			ps.close();
			return recommendation;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
    }

    @Override
    public List<Recommendation> getRecommendationByUserName(String userName) {
		String sql = "SELECT * FROM Recommendations WHERE UserName = ?";

		Connection conn = null;

		try {
            conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			List<Recommendation> recommendations = new ArrayList<Recommendation>();
			while(rs.next()) {
				Recommendation recommendation = new Recommendation(
						rs.getInt("RecommendationId"),
						rs.getString("UserName"),
						rs.getInt("RestaurantId")
				);
                recommendations.add(recommendation);
			}
			rs.close();
			ps.close();
			return recommendations;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
    }

    @Override
    public List<Recommendation> getRecommendationByRestaurantId(int restaurantId) {
		String sql = "SELECT * FROM Recommendations WHERE UserName = ?";

		Connection conn = null;

		try {
            conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, restaurantId);
			ResultSet rs = ps.executeQuery();
			List<Recommendation> recommendations = new ArrayList<Recommendation>();
			while(rs.next()) {
				Recommendation recommendation = new Recommendation(
						rs.getInt("RecommendationId"),
						rs.getString("UserName"),
						rs.getInt("RestaurantId")
				);
                recommendations.add(recommendation);
			}
			rs.close();
			ps.close();
			return recommendations;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
    }

    @Override
    public Recommendation delete(Recommendation recommendation) {
		String sql = "DELETE FROM Recommendations WHERE RecommendationId = ?";

		Connection conn = null;

		try {
            conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, recommendation.getRecommendationId());
			ps.executeUpdate();
			ps.close();
			return recommendation;
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
