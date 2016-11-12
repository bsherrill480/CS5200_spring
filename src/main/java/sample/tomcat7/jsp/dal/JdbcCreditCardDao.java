package sample.tomcat7.jsp.dal;

import org.springframework.stereotype.Service;
import sample.tomcat7.jsp.model.CreditCard;
import sample.tomcat7.jsp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 * Created by brian on 11/10/16.
 */
@Service
public class JdbcCreditCardDao extends MyJdbcDaoSupport implements CreditCardDao {
    @Override
    public CreditCard create(CreditCard creditCard) {
		String sql = "INSERT INTO CreditCards VALUES (?, ?, ?)";
		Connection conn = null;
		try {
            conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, creditCard.getCardNumber());
			ps.setDate(2, creditCard.getExpiration());
			ps.setString(3, creditCard.getUserName());
			int affectedRows = ps.executeUpdate();
			if(affectedRows == 0) {
				throw new SQLException("Creating credit card failed, no rows affected.");
			}
			ps.close();
            return creditCard;
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
    public CreditCard getCreditCardByCardNumber(long cardNumber) {
		String sql = "SELECT * FROM CreditCards WHERE CardNumber = ?";

		Connection conn = null;

		try {
            conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, cardNumber);
			CreditCard creditCard = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				creditCard = new CreditCard(
						rs.getLong("CardNumber"),
						rs.getDate("Expiration"),
						rs.getString("UserName")
				);
			}
			rs.close();
			ps.close();
			return creditCard;
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
    public List<CreditCard> getCreditCardsByUserName(String userName) {
		String sql = "SELECT * FROM CreditCards WHERE UserName = ?";

		Connection conn = null;

		try {
            conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
            List<CreditCard> creditCards = new ArrayList<CreditCard>();
			while(rs.next()) {
				CreditCard creditCard = new CreditCard(
						rs.getLong("CardNumber"),
						rs.getDate("Expiration"),
						rs.getString("UserName")
				);
				creditCards.add(creditCard);
			}
			rs.close();
			ps.close();
			return creditCards;
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
    public CreditCard updateExpiration(CreditCard creditCard, Date newExpiration) {
		String sql = "UPDATE CreditCards SET Expiration = ?";
		Connection conn = null;
		try {
            conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDate(1, creditCard.getExpiration());
			ps.executeUpdate();
			ps.close();
            creditCard.setExpiration(newExpiration);
            return creditCard;
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
    public CreditCard delete(CreditCard creditCard) {
		String sql = "DELETE FROM CreditCards WHERE CardNumber = ?";

		Connection conn = null;

		try {
            conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, creditCard.getCardNumber());
			ps.executeUpdate();
			ps.close();
			return creditCard;
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
