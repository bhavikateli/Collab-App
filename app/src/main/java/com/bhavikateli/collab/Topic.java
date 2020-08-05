package com.bhavikateli.collab;

public class Topic {

    private int img;
    private boolean selected;
    private String name;

    public Topic(int img, boolean selected, String name) {
        this.img = img;
        this.selected = selected;
        this.name = name;
    }

    public String getName(){
        return name;
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
