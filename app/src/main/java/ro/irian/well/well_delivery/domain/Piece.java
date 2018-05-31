package ro.irian.well.well_delivery.domain;

import ro.irian.well.well_delivery.repository.BaseEntity;

public class Piece extends BaseEntity {
    private String awb;
    private Integer length;
    private Integer weight;
    private Integer width;
    private Integer height;

    public Piece(String awb, Integer length, Integer weight, Integer width, Integer height) {
        this.awb = awb;
        this.length = length;
        this.weight = weight;
        this.width = width;
        this.height = height;
    }

    public Piece() {
    }

    public String getAwb() {
        return awb;
    }

    public void setAwb(String awb) {
        this.awb = awb;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}
