package com.ganonalabs.munir.electrtech.data.model.JobDetails;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("clientName")
    @Expose
    private String clientName;
    @SerializedName("clientPhone")
    @Expose
    private String clientPhone;
    @SerializedName("clientEmail")
    @Expose
    private String clientEmail;
    @SerializedName("serviceItem")
    @Expose
    private String serviceItem;
    @SerializedName("serviceId")
    @Expose
    private String serviceId;
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
    private Object employeesName;
    @SerializedName("group")
    @Expose
    private Object group;
    @SerializedName("expectedTime")
    @Expose
    private String expectedTime;
    @SerializedName("employeesPhone")
    @Expose
    private Object employeesPhone;
    @SerializedName("TechnicalInput")
    @Expose
    private String technicalInput;
    @SerializedName("requestStatus")
    @Expose
    private RequestStatus requestStatus;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("billing")
    @Expose
    private Object billing;
    @SerializedName("totalAmount")
    @Expose
    private Object totalAmount;

    /**
     * No args constructor for use in serialization
     *
     */
    public Datum() {
    }

    /**
     *
     * @param requestStatus
     * @param serviceItemId
     * @param clientPhone
     * @param expectedTime
     * @param expectedDate
     * @param employeesName
     * @param id
     * @param technicalInput
     * @param problemDescription
     * @param employeesPhone
     * @param serviceId
     * @param clientName
     * @param serviceItem
     * @param group
     * @param clientEmail
     */
    public Datum(String id, String clientName, String clientPhone, String clientEmail, String serviceItem, String serviceId, String serviceItemId, String problemDescription, String expectedDate, Object employeesName, Object group, String expectedTime, Object employeesPhone, String technicalInput, RequestStatus requestStatus, String photo, Object billing, Object totalAmount) {
        super();
        this.id = id;
        this.clientName = clientName;
        this.clientPhone = clientPhone;
        this.clientEmail = clientEmail;
        this.serviceItem = serviceItem;
        this.serviceId = serviceId;
        this.serviceItemId = serviceItemId;
        this.problemDescription = problemDescription;
        this.expectedDate = expectedDate;
        this.employeesName = employeesName;
        this.group = group;
        this.expectedTime = expectedTime;
        this.employeesPhone = employeesPhone;
        this.technicalInput = technicalInput;
        this.requestStatus = requestStatus;
        this.photo = photo;
        this.billing = billing;
        this.totalAmount = totalAmount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
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

    public Object getEmployeesName() {
        return employeesName;
    }

    public void setEmployeesName(Object employeesName) {
        this.employeesName = employeesName;
    }

    public Object getGroup() {
        return group;
    }

    public void setGroup(Object group) {
        this.group = group;
    }

    public String getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(String expectedTime) {
        this.expectedTime = expectedTime;
    }

    public Object getEmployeesPhone() {
        return employeesPhone;
    }

    public void setEmployeesPhone(Object employeesPhone) {
        this.employeesPhone = employeesPhone;
    }

    public String getTechnicalInput() {
        return technicalInput;
    }

    public void setTechnicalInput(String technicalInput) {
        this.technicalInput = technicalInput;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Object getBilling() {
        return billing;
    }

    public void setBilling(Object billing) {
        this.billing = billing;
    }

    public Object getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Object totalAmount) {
        this.totalAmount = totalAmount;
    }
}