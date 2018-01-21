package ua.com.company.store.model.entity;

/**
 * Created by Владислав on 21.01.2018.
 */
public class Review extends Entity{
    private String userName;
    private String review;

    public static class ReviewBuilder{
        private int id;
        private String userName;
        private String review;

        public ReviewBuilder id(int id){
            this.id = id;
            return this;
        }
        public ReviewBuilder userName(String name){
            this.userName = name;
            return this;
        }
        public ReviewBuilder review(String review){
            this.review = review;
            return this;
        }
        public Review build(){
            return  new Review(this);
        }

        public int getId() {
            return id;
        }

        public String getUserName() {
            return userName;
        }

        public String getReview() {
            return review;
        }
    }

    public Review(ReviewBuilder reviewBuilder) {
    this.id = reviewBuilder.getId();
    this.userName = reviewBuilder.getUserName();
    this.review = reviewBuilder.getReview();
    }

    public String getUserName() {
        return userName;
    }

    public String getReview() {
        return review;
    }
}
