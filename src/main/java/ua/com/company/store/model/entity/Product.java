package ua.com.company.store.model.entity;

/**
 * Created by Владислав on 17.11.2017.
 */
public class Product extends Entity{
    private String title;
    private String description;
    private int price;
    private int imageId;

    public Product(int id, String title, String description, int price, int imageId) {
        super(id);
        this.title = title;
        this.description = description;
        this.price = price;
        this.imageId = imageId;
    }

    public Product() {
    }

    public Product(int id) {
        super(id);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", imageId=" + imageId +
                '}';
    }
}
