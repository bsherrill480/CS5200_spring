package sample.tomcat7.jsp.dal;

import sample.tomcat7.jsp.model.User;

/**
 * Created by brian on 11/10/16.
 */
public interface UserDao {
    User create(User user);
    User getUserByUserName(String userName);
    User delete(User user);
}
