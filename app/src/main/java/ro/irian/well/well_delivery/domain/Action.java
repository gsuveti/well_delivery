package ro.irian.well.well_delivery.domain;

public class Action {
    private String name;


    public Action(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
