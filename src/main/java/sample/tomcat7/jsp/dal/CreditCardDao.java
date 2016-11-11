package sample.tomcat7.jsp.dal;

import sample.tomcat7.jsp.model.CreditCard;

import java.sql.Date;
import java.util.List;

/**
 * Created by brian on 11/10/16.
 */
public interface CreditCardDao {
    public CreditCard create(CreditCard creditCard);
    public CreditCard getCreditCardByCardNumber(long cardNumber);
    public List<CreditCard> getCreditCardsByUserName(String userName);
    public CreditCard updateExpiration(CreditCard creditCard, Date newExpiration);
    public CreditCard delete(CreditCard creditCard);

}
