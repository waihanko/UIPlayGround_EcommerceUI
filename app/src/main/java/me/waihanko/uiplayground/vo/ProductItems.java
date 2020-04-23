package me.waihanko.uiplayground.vo;

public class ProductItems {
    String name, shopName,price;
    int image;

    public ProductItems(String name, String shopName, String price, int image) {
        this.name = name;
        this.shopName = shopName;
        this.price = price;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getShopName() {
        return shopName;
    }

    public String getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
