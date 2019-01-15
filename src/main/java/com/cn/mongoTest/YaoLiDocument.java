package com.cn.mongoTest;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.Date;

/**
 * @Auther: YUANEL
 * @Date: 2018/12/26 16:53
 * @Description:
 */

@Document(collection = "yaoli")
public class YaoLiDocument {

    private String CaseId;
    private String CaseAddressString;
    private Date CaseOpenDatetime;
    private Date CaseBeginDatetime;
    private String CaseDescription;
    private String RequestorFirstName;
    private String RequestorLastName;
    private int age;
    private String gender;
    private double[] location;
    public YaoLiDocument() {
    }
    public YaoLiDocument(String CaseId, double lon, double lat) {
        this.CaseId = CaseId;
        double[] p = new double[]{lon, lat};
        this.location = p;
    }


    public YaoLiDocument(String caseId, String caseAddressString, Date caseOpenDatetime, Date caseBeginDatetime, String caseDescription, String requestorFirstName, String requestorLastName, int age, String gender, double[] location) {
        CaseId = caseId;
        CaseAddressString = caseAddressString;
        CaseOpenDatetime = caseOpenDatetime;
        CaseBeginDatetime = caseBeginDatetime;
        CaseDescription = caseDescription;
        RequestorFirstName = requestorFirstName;
        RequestorLastName = requestorLastName;
        this.age = age;
        this.gender = gender;
        this.location = location;
    }


    public String getCaseId() {
        return CaseId;
    }

    public void setCaseId(String caseId) {
        CaseId = caseId;
    }

    public String getCaseAddressString() {
        return CaseAddressString;
    }

    public void setCaseAddressString(String caseAddressString) {
        CaseAddressString = caseAddressString;
    }

    public Date getCaseOpenDatetime() {
        return CaseOpenDatetime;
    }

    public void setCaseOpenDatetime(Date caseOpenDatetime) {
        CaseOpenDatetime = caseOpenDatetime;
    }

    public Date getCaseBeginDatetime() {
        return CaseBeginDatetime;
    }

    public void setCaseBeginDatetime(Date caseBeginDatetime) {
        CaseBeginDatetime = caseBeginDatetime;
    }

    public String getCaseDescription() {
        return CaseDescription;
    }

    public void setCaseDescription(String caseDescription) {
        CaseDescription = caseDescription;
    }

    public String getRequestorFirstName() {
        return RequestorFirstName;
    }

    public void setRequestorFirstName(String requestorFirstName) {
        RequestorFirstName = requestorFirstName;
    }

    public String getRequestorLastName() {
        return RequestorLastName;
    }

    public void setRequestorLastName(String requestorLastName) {
        RequestorLastName = requestorLastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double[] getLocation() {
        return location;
    }

    public void setLocation(double[] location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "YaoLiDocument{" +
                "CaseId='" + CaseId + '\'' +
                ", CaseAddressString='" + CaseAddressString + '\'' +
                ", CaseOpenDatetime=" + CaseOpenDatetime +
                ", CaseBeginDatetime=" + CaseBeginDatetime +
                ", CaseDescription='" + CaseDescription + '\'' +
                ", RequestorFirstName='" + RequestorFirstName + '\'' +
                ", RequestorLastName='" + RequestorLastName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", location=" + Arrays.toString(location) +
                '}';
    }
}
