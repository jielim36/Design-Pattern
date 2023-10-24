package PrototypePattern.CustomPrototypePattern;

public class Client {
    public static void main(String[] args) {
        Admin admin = new Admin("jielim36");
        admin.setAuthority(new Authority(1,"Highest Authority"));

        Admin cloneAdmin = admin.clone();
        cloneAdmin.getAuthority().setDescription("Lowest Authority");

        System.out.println("Existing Admin authority Description:" + admin.getAuthority().getDescription());
        System.out.println("Clone Admin authority Description:" + cloneAdmin.getAuthority().getDescription());
    }
}
