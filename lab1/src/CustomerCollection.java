import java.util.HashMap;

public class CustomerCollection {
    HashMap<Integer, Customer> hashId = new HashMap<Integer, Customer>();
    HashMap<String, Customer> hashSsn = new HashMap<String, Customer>();

    public CustomerCollection() {
    }

    public void addCustomer(
            String ssn,
            String name,
            String address,
            String phonenumber) {
        if (!hashSsn.containsKey(ssn)) {
            int nextIdx = this.hashId.size() + 1;
            Customer newCustomer = new Customer(
                    nextIdx,
                    ssn,
                    name,
                    address,
                    phonenumber);
            this.hashId.put(nextIdx, newCustomer);
            this.hashSsn.put(ssn, newCustomer);
        }
    }

    public HashMap<Integer, Customer> getHashId() {
        return this.hashId;
    }

    public HashMap<String, Customer> getHashSsn() {
        return this.hashSsn;
    }

    // query
    public void queryByCustomerSsn(String ssn) {
        if (this.hashSsn.containsKey(ssn)) {
            int id = this.hashSsn.get(ssn).getId();
            String ssNumber = this.hashSsn.get(ssn).getSsn();
            String name = this.hashSsn.get(ssn).getName();
            String address = this.hashSsn.get(ssn).getAddress();
            String phone = this.hashSsn.get(ssn).getPhoneNumber();
            System.out.format("\n - id: %d ssn: %s name: %s address: %s phone: %s.\n",
                    id, ssNumber, name, address, phone);
        }
    }

    public void queryByCustomerId(int customerId) {
        if (this.hashId.containsKey(customerId)) {
            int id = this.hashId.get(customerId).getId();
            String ssn = this.hashId.get(customerId).getSsn();
            String name = this.hashId.get(customerId).getName();
            String address = this.hashId.get(customerId).getAddress();
            String phone = this.hashId.get(customerId).getPhoneNumber();
            System.out.format("\n - id: %d ssn: %s name: %s address: %s phone: %s.\n",
                    id, ssn, name, address, phone);
        }
    }

    public void display() {
        this.hashId.forEach((key, value) -> System.out.println(value));
    }

}
