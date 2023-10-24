package PrototypePattern.CustomPrototypePattern;

public class Admin implements User {

    private String username;
    private Authority authority;

    public Admin(String username) {
        this.username = username;
    }

    @Override
    public Admin clone() {
        Admin admin = new Admin(this.getUsername());//create a new admin object and give it own data
        admin.setAuthority(this.getAuthority());
        return admin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }
}
