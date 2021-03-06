package com.ganonalabs.munir.electrtech.data.model.JobRequests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyJobRequest {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("serviceId")
    @Expose
    private String serviceId;
    @SerializedName("jobAmount")
    @Expose
    private String jobAmount;
    @SerializedName("clientName")
    @Expose
    private String clientName;
    @SerializedName("clientPhone")
    @Expose
    private String clientPhone;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("clientEmail")
    @Expose
    private String clientEmail;
    @SerializedName("serviceItem")
    @Expose
    private String serviceItem;
    @SerializedName("serviceItemId")
    @Expose
    private String serviceItemId;
    @SerializedName("problemDescription")
    @Expose
    private String problemDescription;
    @SerializedName("expectedDate")
    @Expose
    private String expectedDate;
    @SerializedName("employeesName")
    @Expose
    private String employeesName;
    @SerializedName("group")
    @Expose
    private String group;
    @SerializedName("expectedTime")
    @Expose
    private String expectedTime;
    @SerializedName("employeesPhone")
    @Expose
    private String employeesPhone;
    @SerializedName("TechnicalInput")
    @Expose
    private String technicalInput;
    @SerializedName("requestStatus")
    @Expose
    private String requestStatus;
    @SerializedName("requestStatusDate")
    @Expose
    private String requestStatusDate;
    @SerializedName("ImageUrl")
    @Expose
    private String ImageUrl;



    /**
     * No args constructor for use in serialization
     *
     */
    public MyJobRequest() {
    }

    /**
     *
     * @param requestStatus
     * @param serviceItemId
     * @param jobAmount
     * @param clientPhone
     * @param expectedTime
     * @param expectedDate
     * @param employeesName
     * @param id
     * @param technicalInput
     * @param problemDescription
     * @param employeesPhone
     * @param serviceId
     * @param address
     * @param clientName
     * @param requestStatusDate
     * @param serviceItem
     * @param group
     * @param clientEmail
     */
    public MyJobRequest(String id, String serviceId, String jobAmount, String clientName, String clientPhone, String address, String clientEmail, String serviceItem, String serviceItemId, String problemDescription, String expectedDate, String employeesName, String group, String expectedTime, String employeesPhone, String technicalInput, String requestStatus, String requestStatusDate, String ImageUrl) {
        super();
        this.id = id;
        this.serviceId = serviceId;
        this.jobAmount = jobAmount;
        this.clientName = clientName;
        this.clientPhone = clientPhone;
        this.address = address;
        this.clientEmail = clientEmail;
        this.serviceItem = serviceItem;
        this.serviceItemId = serviceItemId;
        this.problemDescription = problemDescription;
        this.expectedDate = expectedDate;
        this.employeesName = employeesName;
        this.group = group;
        this.expectedTime = expectedTime;
        this.employeesPhone = employeesPhone;
        this.technicalInput = technicalInput;
        this.requestStatus = requestStatus;
        this.requestStatusDate = requestStatusDate;
        this.ImageUrl = ImageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getJobAmount() {
        return jobAmount;
    }

    public void setJobAmount(String jobAmount) {
        this.jobAmount = jobAmount;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getServiceItem() {
        return serviceItem;
    }

    public void setServiceItem(String serviceItem) {
        this.serviceItem = serviceItem;
    }

    public String getServiceItemId() {
        return serviceItemId;
    }

    public void setServiceItemId(String serviceItemId) {
        this.serviceItemId = serviceItemId;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(String expectedDate) {
        this.expectedDate = expectedDate;
    }

    public String getEmployeesName() {
        return employeesName;
    }

    public void setEmployeesName(String employeesName) {
        this.employeesName = employeesName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(String expectedTime) {
        this.expectedTime = expectedTime;
    }

    public String getEmployeesPhone() {
        return employeesPhone;
    }

    public void setEmployeesPhone(String employeesPhone) {
        this.employeesPhone = employeesPhone;
    }

    public String getTechnicalInput() {
        return technicalInput;
    }

    public void setTechnicalInput(String technicalInput) {
        this.technicalInput = technicalInput;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getRequestStatusDate() {
        return requestStatusDate;
    }

    public void setRequestStatusDate(String requestStatusDate) {
        this.requestStatusDate = requestStatusDate;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}
