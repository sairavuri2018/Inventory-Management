package assesment.DTO;

import java.util.Objects;

public class InventoryDetailsDTO {


    public InventoryDetailsDTO() {
    }

    private  String itemName;

    private double costPrice;

    private double sellingPrice;

    private int quantity;


    public InventoryDetailsDTO(String itemName, double costPrice, double sellingPrice) {
        this.itemName = itemName;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
    }


    public InventoryDetailsDTO(String itemName, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public InventoryDetailsDTO(String itemName) {
        this.itemName = itemName;
    }


    public String getItemName() {
        return itemName;
    }

    public InventoryDetailsDTO setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public InventoryDetailsDTO setCostPrice(float costPrice) {
        this.costPrice = costPrice;
        return this;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public InventoryDetailsDTO setSellingPrice( double sellingPrice) {
        this.sellingPrice = sellingPrice;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public InventoryDetailsDTO setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InventoryDetailsDTO)) return false;
        InventoryDetailsDTO that = (InventoryDetailsDTO) o;
        return Double.compare(that.costPrice, costPrice) == 0 &&
                Double.compare(that.sellingPrice, sellingPrice) == 0 &&
                quantity == that.quantity &&
                Objects.equals(itemName, that.itemName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName, costPrice, sellingPrice, quantity);
    }
}
