package models;

import java.sql.Date;

public class ItemTransaction {
    private int transactionId;
    private int itemId;
    private String itemName;
    private String itemDescription;
    private int price;
    private Date boughtDate;
    private Date useDate;
    private boolean isAvailable;
    private boolean isUsed;

    public ItemTransaction(int transactionId, int itemId, String itemName, String itemDescription, Date boughtDate, Date useDate, boolean isAvailable) {
        this.transactionId = transactionId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.boughtDate = boughtDate;
        this.useDate = useDate;
        this.isAvailable = isAvailable;
    }

    public ItemTransaction(int transactionId, int itemId, String itemName, int price, String itemDescription, boolean isAvailable, boolean isUsed) {
        this.transactionId = transactionId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
        this.itemDescription = itemDescription;
        this.isAvailable = isAvailable;
        this.isUsed = isUsed;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getBoughtDate() {
        return boughtDate;
    }

    public void setBoughtDate(Date boughtDate) {
        this.boughtDate = boughtDate;
    }

    public Date getUseDate() {
        return useDate;
    }

    public void setUseDate(Date useDate) {
        this.useDate = useDate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }
}
