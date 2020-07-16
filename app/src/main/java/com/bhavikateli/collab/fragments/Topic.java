package com.bhavikateli.collab.fragments;

public class Topic {

    private int img;
    private boolean selected;

    public Topic(int img, boolean selected) {
        this.img = img;
        this.selected = selected;
    }

    public void setSelected(Boolean selectedState){
        selected = selectedState;
    }

    public Boolean getSelected(){
        return selected;
    }

    public int getImg(){
        return img;
    }

}
