package dz2_task4;

public class Customer {
    String fullname;
    String address;
    String phone;

    public Customer(String fullname, String address) {
        this.fullname = fullname;
        this.address = address;
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
