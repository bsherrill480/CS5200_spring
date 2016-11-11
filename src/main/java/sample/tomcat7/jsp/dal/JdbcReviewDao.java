package sample.tomcat7.jsp.dal;

import sample.tomcat7.jsp.model.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by brian on 11/11/16.
 */
public class JdbcReviewDao extends MyJdbcDaoSupport implements ReviewDao {
    @Override
    public Review create(Review review) {
		String sql = "INSERT INTO Reviews (Content, Rating, UserName, RestaurantId) " +
				"VALUES (?, ?, ?, ?)";
		Connection conn = null;
		try {
		    conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, review.getContent());
			ps.setBigDecimal(2, review.getRating());
			ps.setString(3, review.getUserName());
			ps.setInt(4, review.getRestaurantId());
			int affectedRows = ps.executeUpdate();
			if(affectedRows == 0) {
				throw new SQLException("Creating restaurant failed, no rows affected.");
			}
			ResultSet generatedKeys = ps.getGeneratedKeys();
			if(generatedKeys.next()) {
				review.setReviewId(generatedKeys.getInt(1));
			} else {
				throw new SQLException("Creating restaurant failed, no ID obtained.");
			}
			ps.close();
		    return review;
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
    public Review getReviewById(int reviewId) {
		String sql = "SELECT * FROM Reviews WHERE ReviewId = ?";

		Connection conn = null;

		try {
            conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, reviewId);
			Review review = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				review = new Review(
						rs.getInt("ReviewId"),
						rs.getTimestamp("Created"),
						rs.getString("Content"),
						rs.getBigDecimal("Rating"),
						rs.getString("UserName"),
						rs.getInt("RestaurantId")
				);
			}
			rs.close();
			ps.close();
			return review;
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
    public List<Review> getReviewByUserName(String userName) {
		String sql = "SELECT * FROM Reviews WHERE UserName = ?";

		Connection conn = null;

		try {
            conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			List<Review> reviews = new ArrayList<Review>();
			while(rs.next()) {
				Review review = new Review(
						rs.getInt("ReviewId"),
						rs.getTimestamp("Created"),
						rs.getString("Content"),
						rs.getBigDecimal("Rating"),
						rs.getString("UserName"),
						rs.getInt("RestaurantId")
				);
				reviews.add(review);
			}
			rs.close();
			ps.close();
			return reviews;
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
    public List<Review> getReviewByRestaurantId(int restaurantId) {
		String sql = "SELECT * FROM Reviews WHERE RestaurandId = ?";

		Connection conn = null;

		try {
            conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, restaurantId);
			ResultSet rs = ps.executeQuery();
			List<Review> reviews = new ArrayList<Review>();
			while(rs.next()) {
				Review review = new Review(
						rs.getInt("ReviewId"),
						rs.getTimestamp("Created"),
						rs.getString("Content"),
						rs.getBigDecimal("Rating"),
						rs.getString("UserName"),
						rs.getInt("RestaurantId")
				);
				reviews.add(review);
			}
			rs.close();
			ps.close();
			return reviews;
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
    public Review delete(Review review) {
		String sql = "DELETE FROM Reviews WHERE ReviewId = ?";

		Connection conn = null;

		try {
            conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, review.getReviewId());
			ps.executeUpdate();
			ps.close();
			return review;
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
