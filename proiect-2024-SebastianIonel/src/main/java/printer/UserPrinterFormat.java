package printer;

import java.util.ArrayList;

public class UserPrinterFormat {
    private String email;
    private String firstname;
    private String lastname;
    private String address;
    private ArrayList<String> friends;

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<String> getFriends() {
        return friends;
    }

    public UserPrinterFormat(String email, String firstname, String lastname, String address, ArrayList<String> friends) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.friends = friends;
    }
}