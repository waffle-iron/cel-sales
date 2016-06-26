
package com.mx.kiibal.celsales.web.rest.dto;

import java.io.Serializable;

/**
 *
 * @author cbautista
 */
public class AppDataDTO implements Serializable{
    private static final long serialVersionUID = 3170509101854110230L;
    
    private String appName;
    private String appPackage;
    private String appVersion;
    private String appInstalled;
    private String appLastModify;

    public AppDataDTO() {}

    public AppDataDTO(String appName, String appPackage, String appVersion, 
            String appInstalled, String appLastModify) {
        this.appName = appName;
        this.appPackage = appPackage;
        this.appVersion = appVersion;
        this.appInstalled = appInstalled;
        this.appLastModify = appLastModify;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppInstalled() {
        return appInstalled;
    }

    public void setAppInstalled(String appInstalled) {
        this.appInstalled = appInstalled;
    }

    public String getAppLastModify() {
        return appLastModify;
    }

    public void setAppLastModify(String appLastModify) {
        this.appLastModify = appLastModify;
    }

    @Override
    public String toString() {
        return "AppDataDTO{" + "appName=" + appName + ", appPackage=" + appPackage 
                + ", appVersion=" + appVersion + ", appInstalled=" + appInstalled 
                + ", appLastModify=" + appLastModify + '}';
    }
    
    
}
