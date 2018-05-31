package ro.irian.well.well_delivery.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ro.irian.well.well_delivery.repository.BaseEntity;

public class Task extends BaseEntity {

    private String routeID;
    private boolean active;
    private boolean pickupDone;
    private boolean deliveryDone;

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

    private Integer totalPieces;
    private Integer totalWeight;

    private List<String> tags;
    private List<Piece> pieces = new ArrayList<>();
    private Map<String, Piece> piecesMap = new HashMap();

    public Task() {
        super();
    }

    public String getRouteID() {
        return routeID;
    }

    public void setRouteID(String routeID) {
        this.routeID = routeID;
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

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isPickupDone() {
        return pickupDone;
    }

    public void setPickupDone(boolean pickupDone) {
        this.pickupDone = pickupDone;
    }

    public boolean isDeliveryDone() {
        return deliveryDone;
    }

    public void setDeliveryDone(boolean deliveryDone) {
        this.deliveryDone = deliveryDone;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public Map<String, Piece> getPiecesMap() {
        return piecesMap;
    }
}
