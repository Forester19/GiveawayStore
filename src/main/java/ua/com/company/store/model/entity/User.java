package ua.com.company.store.model.entity;

/**
 * Created by Владислав on 17.11.2017.
 */
public class User extends Entity{

    private String nickname;
    private String password;
    private String email;
    private boolean role;
    private boolean isDefaulter;

    public User(int id, String nickname, String password, String email, boolean role, boolean isDefaulter) {
        super(id);
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.role = role;
        this.isDefaulter = isDefaulter;
    }

    public User(){

    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    public boolean isDefaulter() {
        return isDefaulter;
    }

    public void setDefaulter(boolean defaulter) {
        isDefaulter = defaulter;
    }
}
