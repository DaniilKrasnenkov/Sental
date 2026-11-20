package model;

public class Customer {
    private String fullname;
    private String address;
    private String phone;

    public Customer(String fullname, String address) {
        this.fullname = fullname;
        this.address = address;
    }

    public Customer(String fullname, String address, String phone) {
        this.fullname = fullname;
        this.address = address;
        this.phone=phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullname() {
        return fullname;
    }

    public String getAddress() {
        return address;
    }
}
