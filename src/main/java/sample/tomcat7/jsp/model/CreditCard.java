package sample.tomcat7.jsp.model;

import java.math.BigInteger;
//import java.util.Date;
import java.sql.Date;

/**
 * Created by brian on 11/10/16.
 */
public class CreditCard {
    // https://www.cis.upenn.edu/~bcpierce/courses/629/jdkdocs/guide/jdbc/getstart/mapping.doc.html
    // "The recommended Java mapping for the BIGINT type is as a Java long."
    private long CardNumber;
    private Date Expiration;
    private String UserName;

    public CreditCard(long cardNumber, Date expiration, String userName) {
        CardNumber = cardNumber;
        Expiration = expiration;
        UserName = userName;
    }

    public long getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(long cardNumber) {
        CardNumber = cardNumber;
    }

    public Date getExpiration() {
        return Expiration;
    }

    public void setExpiration(Date expiration) {
        Expiration = expiration;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }
}
