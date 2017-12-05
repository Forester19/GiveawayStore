package ua.com.company.store.model.entity;

/**
 * Created by Владислав on 17.11.2017.
 */
public class Image extends Entity {
    private String path;
    private String data;

    public Image(int id, String path, String data) {
        super(id);
        this.path = path;
        this.data = data;
    }

    public Image(int id) {
        super(id);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Image{" +
                "path='" + path + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
