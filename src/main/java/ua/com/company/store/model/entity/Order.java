package ua.com.company.store.model.entity;

import java.util.Date;

/**
 * Created by Владислав on 17.11.2017.
 */
public class Order extends Entity {
    private int productId;
    private int entityId;
    private Date date;

    public Order(OrderBuilder orderBuilder) {
        super(orderBuilder.getId());
        this.productId = orderBuilder.getProductId();
        this.entityId = orderBuilder.getEntityId();
        this.date = orderBuilder.getDate();
    }
    public static class OrderBuilder{
        private int id;
        private int productId;
        private int entityId;
        private Date date;

        public OrderBuilder setId (final int id){
            this.id = id;
            return this;
        }
        public OrderBuilder setProductId (final int id){
            this.productId = id;
            return this;
        }
        public OrderBuilder setEntityId (final int id){
            this.entityId = id;
            return this;
        }

        public OrderBuilder setData (final Date data){
            this.date = data;
            return this;
        }

        public int getId() {
            return id;
        }

        public int getProductId() {
            return productId;
        }

        public int getEntityId() {
            return entityId;
        }

        public Date getDate() {
            return date;
        }
        public Order build(){
            return new Order(this);
        }
    }

    public int getProductId() {
        return productId;
    }

    public int getEntityId() {
        return entityId;
    }

    public Date getDate() {
        return date;
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
