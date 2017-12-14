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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (role != user.role) return false;
        if (isDefaulter != user.isDefaulter) return false;
        if (nickname != null ? !nickname.equals(user.nickname) : user.nickname != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        return email != null ? email.equals(user.email) : user.email == null;
    }

    @Override
    public int hashCode() {
        int result = nickname != null ? nickname.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (role ? 1 : 0);
        result = 31 * result + (isDefaulter ? 1 : 0);
        return result;
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

    @Override
    public String toString() {
        return "User:"+ nickname + " "+ password + " "+ email + " "+ role +" "+ isDefaulter;
    }
}
