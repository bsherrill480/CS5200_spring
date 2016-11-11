package sample.tomcat7.jsp.dal;

import sample.tomcat7.jsp.model.Company;

/**
 * Created by brian on 11/11/16.
 */
public interface CompanyDao {
    Company create(Company company);
    Company getCompanyByCompanyName(String companyName);
    Company updateAbout(Company company, String newAbout);
    Company delete(Company company);
}
