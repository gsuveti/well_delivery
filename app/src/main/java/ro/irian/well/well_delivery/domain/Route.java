package ro.irian.well.well_delivery.domain;

public class Route {

    String id;
    String originAddress;
    String destinationAddress;
    Integer km;

    public Route(String id, String originAddress, String destinationAddress, Integer km) {
        this.id = id;
        this.originAddress = originAddress;
        this.destinationAddress = destinationAddress;
        this.km = km;
    }

    public Route() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginAddress() {
        return originAddress;
    }

    public void setOriginAddress(String originAddress) {
        this.originAddress = originAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public Integer getKm() {
        return km;
    }

    public void setKm(Integer km) {
        this.km = km;
    }
}
