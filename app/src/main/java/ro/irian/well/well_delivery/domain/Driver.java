package ro.irian.well.well_delivery.domain;

public class Driver {
    private String id;
    private String name;
    private String phone;
    private String carRef;
    private String activeRouteRef;


    public Driver() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCarRef() {
        return carRef;
    }

    public void setCarRef(String carRef) {
        this.carRef = carRef;
    }

    public String getActiveRouteRef() {
        return activeRouteRef;
    }

    public void setActiveRouteRef(String activeRouteRef) {
        this.activeRouteRef = activeRouteRef;
    }
}
