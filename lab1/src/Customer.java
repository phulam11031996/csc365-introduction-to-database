public class Customer {
    public int id; // unique, automatically assigned
    public String ssn; // unique
    public String name;
    public String address;
    public String phoneNumber;

    public Customer(
            int id,
            String ssn,
            String name,
            String address,
            String phoneNumber) {
        this.id = id;
        this.ssn = ssn;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return this.id;
    }

    public String getSsn() {
        return this.ssn;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    @Override
    public String toString() {
        return "- customerId: " + this.id +
                " ssn: " + this.ssn +
                " name: " + this.name +
                " address: " + this.address +
                " phoneNumber: " + this.phoneNumber + "\n";
    }

}
