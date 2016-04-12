package licenseModule;

/**
 * Created by joshoy on 16/4/12.
 */
public interface License {
    void setLicenseTypename(String typeName);
    String getLicenseTypename();
    void onReceiveNewRequest();

    boolean checkThroughput();
    boolean checkCapacity();

    void setMaxThroughput(int maxThroughput) throws InvalidLicenseNumberException;
    void setMaxCapacity(int maxCapacity) throws InvalidLicenseNumberException;
    void setClearPeriodSeconds(double clearPeriodSecond) throws InvalidLicenseNumberException;
}
