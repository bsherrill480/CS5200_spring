package sample.tomcat7.jsp.model;

/**
 * Created by brian on 11/11/16.
 */
public class Company {
    private String companyName;
    private String about;

    public Company(String companyName, String about) {
        this.companyName = companyName;
        this.about = about;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
