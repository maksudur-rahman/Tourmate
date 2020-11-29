package com.ju.rotno.tourmate;

/**
 * Created by Mac on 24-10-17.
 */
public class Expense {
    private int id;

    private String expDetails;
    private  int amount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public Expense(int id, String expDetails, int amount, String name) {
        this.id = id;
        this.expDetails = expDetails;
        this.amount = amount;
        this.name = name;
    }

    public Expense(String expDetails, int amount) {
        this.expDetails = expDetails;
        this.amount=amount;
    }

    public Expense(int id, String expDetails, int amount) {
        this.amount = amount;
        this.expDetails = expDetails;
        this.id=id;

    }

    public Expense() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getExpDetails() {
        return expDetails;
    }

    public void setExpDetails(String expDetails) {
        this.expDetails = expDetails;
    }
}
