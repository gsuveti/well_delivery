package ro.irian.well.well_delivery.domain;

public class Task {

    private String id;

    private String pickupCity;
    private String pickupAddress;
    private String pickupContactName;
    private String pickupContactPhone;
    private String pickupStartTime;
    private String pickupEndTime;

    private String deliveryAddress;
    private String deliveryCity;
    private String deliveryContactName;
    private String deliveryContactPhone;
    private String deliveryStartTime;
    private String deliveryEndTime;

    private String awb;

    private String sender;
    private String consignee;

    private boolean isNew;
    private boolean isFinalTask;

    private Integer totalPieces;
    private Integer totalWeight;

    public Task() {
    }

    public Task(String id, String pickupCity, String pickupAddress, String pickupContactName, String pickupContactPhone, String pickupStartTime, String pickupEndTime, String deliveryAddress, String deliveryCity, String deliveryContactName, String deliveryContactPhone, String deliveryStartTime, String deliveryEndTime, String awb, String sender, String consignee, boolean isNew, boolean isFinalTask, Integer totalPieces, Integer totalWeight) {
        this.id = id;
        this.pickupCity = pickupCity;
        this.pickupAddress = pickupAddress;
        this.pickupContactName = pickupContactName;
        this.pickupContactPhone = pickupContactPhone;
        this.pickupStartTime = pickupStartTime;
        this.pickupEndTime = pickupEndTime;
        this.deliveryAddress = deliveryAddress;
        this.deliveryCity = deliveryCity;
        this.deliveryContactName = deliveryContactName;
        this.deliveryContactPhone = deliveryContactPhone;
        this.deliveryStartTime = deliveryStartTime;
        this.deliveryEndTime = deliveryEndTime;
        this.awb = awb;
        this.sender = sender;
        this.consignee = consignee;
        this.isNew = isNew;
        this.isFinalTask = isFinalTask;
        this.totalPieces = totalPieces;
        this.totalWeight = totalWeight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPickupCity() {
        return pickupCity;
    }

    public void setPickupCity(String pickupCity) {
        this.pickupCity = pickupCity;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getPickupContactName() {
        return pickupContactName;
    }

    public void setPickupContactName(String pickupContactName) {
        this.pickupContactName = pickupContactName;
    }

    public String getPickupContactPhone() {
        return pickupContactPhone;
    }

    public void setPickupContactPhone(String pickupContactPhone) {
        this.pickupContactPhone = pickupContactPhone;
    }

    public String getPickupStartTime() {
        return pickupStartTime;
    }

    public void setPickupStartTime(String pickupStartTime) {
        this.pickupStartTime = pickupStartTime;
    }

    public String getPickupEndTime() {
        return pickupEndTime;
    }

    public void setPickupEndTime(String pickupEndTime) {
        this.pickupEndTime = pickupEndTime;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    public String getDeliveryContactName() {
        return deliveryContactName;
    }

    public void setDeliveryContactName(String deliveryContactName) {
        this.deliveryContactName = deliveryContactName;
    }

    public String getDeliveryContactPhone() {
        return deliveryContactPhone;
    }

    public void setDeliveryContactPhone(String deliveryContactPhone) {
        this.deliveryContactPhone = deliveryContactPhone;
    }

    public String getDeliveryStartTime() {
        return deliveryStartTime;
    }

    public void setDeliveryStartTime(String deliveryStartTime) {
        this.deliveryStartTime = deliveryStartTime;
    }

    public String getDeliveryEndTime() {
        return deliveryEndTime;
    }

    public void setDeliveryEndTime(String deliveryEndTime) {
        this.deliveryEndTime = deliveryEndTime;
    }

    public String getAwb() {
        return awb;
    }

    public void setAwb(String awb) {
        this.awb = awb;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public boolean isFinalTask() {
        return isFinalTask;
    }

    public void setFinalTask(boolean finalTask) {
        isFinalTask = finalTask;
    }

    public Integer getTotalPieces() {
        return totalPieces;
    }

    public void setTotalPieces(Integer totalPieces) {
        this.totalPieces = totalPieces;
    }

    public Integer getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Integer totalWeight) {
        this.totalWeight = totalWeight;
    }
}
