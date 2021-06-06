package com.sliusar.storageteamvoytask.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int price;
    private int quantity;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    @JsonIgnore
    private List<Orders> orders = new ArrayList<>();

    public Item(int id, String name, int price, List<Orders> orders, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.orders = orders;
        this.quantity = quantity;
    }

    public Item(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.price = item.getPrice();
        this.quantity = item.getQuantity();
        this.orders = item.getOrders();
    }

    public Item() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

}
