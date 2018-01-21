package ua.com.company.store.model.entity;

/**
 * Created by Владислав on 21.01.2018.
 */
public class Products_Reviews extends Entity {
    private int product_id;
    private int review_id;

    public static class ProductReviewBuilder {
        private int id;
        private int product_id;
        private int review_id;
        public ProductReviewBuilder id(int id) {
            this.id = id;
            return this;
        }
        public ProductReviewBuilder product_id(int product_id){
            this.product_id = product_id;
            return this;
        }
        public ProductReviewBuilder review_id(int review_id){
            this.review_id= review_id;
            return this;
        }
        public Products_Reviews build(){
            return new Products_Reviews(this);
        }

        public int getId() {
            return id;
        }

        public int getProduct_id() {
            return product_id;
        }

        public int getReview_id() {
            return review_id;
        }
    }

    public Products_Reviews(ProductReviewBuilder productReviewBuilder) {
        super(productReviewBuilder.getId());
        this.product_id = productReviewBuilder.getProduct_id();
        this.review_id = productReviewBuilder.getReview_id();
    }

    public int getProduct_id() {
        return product_id;
    }

    public int getReview_id() {
        return review_id;
    }

    @Override
    public String toString() {
        return "Products_Reviews{" +
                "product_id=" + product_id +
                ", review_id=" + review_id +
                '}';
    }
}
