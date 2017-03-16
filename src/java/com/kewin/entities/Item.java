/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kewin.entities;

/**
 *
 * @author Kelv
 */
public class Item {
    private Element[] elements;

    public Item() {
    }

    public Item(Element[] elements) {
        this.elements = elements;
    }
    
    

    public Element[] getElements() {
        return elements;
    }

    public void setElements(Element[] elements) {
        this.elements = elements;
    }
    
    
}
