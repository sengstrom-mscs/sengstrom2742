import java.text.NumberFormat;

import java.text.NumberFormat;

public class Product implements Printable, ProductWriter,DepartmentConstants{

    private String code;
    private String description;
    private double price;

    public Product() {
        code = "";
        description = "";
        price = 0;
    }

    public Product(String code, String description, double price) {
        this.code = code;
        this.description = description;
        this.price = price;
    }
    public void print(){
//        System.out.println(description);
        String message = "\nPRODUCT\n" +
                    "Code:        " + getCode() + "\n" +
                    "Description: " + getDescription() + "\n" +
                    "Price:       " + getPriceFormatted() + "\n" +
                    "Editorial:   " + EDITORIAL + "\n";
        System.out.println(message);
    }

    public boolean add(Product p){
        return false;
    }
    public boolean update(Product p){
        return false;
    }
    public boolean delete(Product p){
        return false;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getPriceFormatted() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        String priceFormatted = currency.format(price);
        return priceFormatted;
    }
}
