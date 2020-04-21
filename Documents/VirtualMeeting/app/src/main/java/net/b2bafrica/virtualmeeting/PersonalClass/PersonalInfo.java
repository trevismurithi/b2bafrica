package net.b2bafrica.virtualmeeting.PersonalClass;

public class PersonalInfo {
    private String name;
    private String designation;
    private String firstMail;
    private String secMail;
    private String mobile;
    private String mobile2;

    public PersonalInfo(){}

    public PersonalInfo(String name, String designation, String firstMail, String secMail, String mobile, String mobile2) {
        this.name = name;
        this.designation = designation;
        this.firstMail = firstMail;
        this.secMail = secMail;
        this.mobile = mobile;
        this.mobile2 = mobile2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getFirstMail() {
        return firstMail;
    }

    public void setFirstMail(String firstMail) {
        this.firstMail = firstMail;
    }

    public String getSecMail() {
        return secMail;
    }

    public void setSecMail(String secMail) {
        this.secMail = secMail;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile2() {
        return mobile2;
    }

    public void setMobile2(String mobile2) {
        this.mobile2 = mobile2;
    }
}
