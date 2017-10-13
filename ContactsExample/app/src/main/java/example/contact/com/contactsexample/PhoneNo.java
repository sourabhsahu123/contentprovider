package example.contact.com.contactsexample;

/**
 * Created by sourabh on 10/13/2017.
 */

public class PhoneNo {
    String name,number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public PhoneNo(String name, String number) {

        this.name = name;
        this.number = number;
    }
}
