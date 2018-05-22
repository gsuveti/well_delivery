package ro.irian.well.well_delivery.domain;

public class Task {

    private String id;
    private String destinationAddress;

    public Task(String id, String destinationAddress) {
        this.id = id;
        this.destinationAddress = destinationAddress;
    }

    public Task() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }
}
