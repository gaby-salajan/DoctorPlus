package com.ns.doctorplus.model;

public class TimeSlot {
    private Long slot;
    private String type ;
    private String info;

    public TimeSlot() {}

    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public Long getSlot() {
        return slot;
    }
    public void setSlot(Long slot) {
        this.slot = slot;
    }
}
