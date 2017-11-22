package ua.com.company.store.model.entity;

import java.util.Date;

/**
 * Created by Владислав on 17.11.2017.
 */
public class Order extends Entity {
    private int productId;
    private int entityId;
    private Date date;

    public Order(int id, int productId, int entityId, Date date) {
        super(id);
        this.productId = productId;
        this.entityId = entityId;
        this.date = date;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "productId=" + productId +
                ", entityId=" + entityId +
                ", date=" + date +
                '}';
    }
}
