//Class to handle Customer data in CSV
public class Customer {

    //Data Points included in file:
    private String userID;
    private String fName;
    private String lName;
    private int versionNumber;
    private String insuranceCompany;


    //Member functions

    //Get
    public String getUserID()
    {
        return userID;
    }

    public String getFirstName()
    {
        return fName;
    }
    public String getLastName()
    {
        return lName;
    }

    public int getVersion() {
        return versionNumber;
    }

    public String getInsuranceCompany()
    {
        return insuranceCompany;
    }

    //Set
    public void setUserID(String userID)
    {
        this.userID = userID;
    }

    public void setFirstName(String fName)
    {
        this.fName = fName;
    }

    public void setLastName(String lName)
    {
        this.lName = lName;
    }

    public void setVersionNumber(int versionNumber)
    {
        this.versionNumber = versionNumber;
    }

    public void setInsuranceCompany(String insuranceCompany)
    {
        this.insuranceCompany = insuranceCompany;
    }
}
