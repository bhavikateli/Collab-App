package com.bhavikateli.collab;

public class Topic {

    private int img;
    private boolean selected;

    public Topic(int img, boolean selected) {
        this.img = img;
        this.selected = selected;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selectedState) {
        selected = selectedState;
    }

    public int getImg() {
        return img;
    }

}
