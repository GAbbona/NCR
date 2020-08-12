/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wm.entity;

/**
 *
 * @author WM
 */
public class Denominations {
    
    private int id;
    private Double value;
    private String level;
    private Integer cuantity;
    
    public Denominations (int id, double v, String l, int c) {
        this.id = id;
        this.value = v;
        this.level = l;
        this.cuantity = c;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getCuantity() {
        return cuantity;
    }

    public void setCuantity(Integer cuantity) {
        this.cuantity = cuantity;
    }
    
    
    
}
