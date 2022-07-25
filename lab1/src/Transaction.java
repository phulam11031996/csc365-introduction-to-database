import java.util.Date;

public class Transaction {

    public int customerId;
    public int venderId;
    public double worthPurchase;
    public String cardNumber;
    public Date date;

    public Transaction(
            int venderId,
            int customerId,
            String cardNumber,
            double worthPurchase,
            Date date) {
        this.venderId = venderId;
        this.customerId = customerId;
        this.cardNumber = cardNumber;
        this.worthPurchase = worthPurchase;
        this.date = date;
    }

    // getters
    public int getCustomerId() {
        return this.customerId;
    }

    public int getVenderId() {
        return this.venderId;
    }

    public double getWorthPurchase() {
        return this.worthPurchase;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public Date getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return "- venderId: " + this.date +
                " customerId: " + this.customerId +
                " cardNumber: " + this.cardNumber +
                " worthPurchase: " + this.worthPurchase +
                " date: " + this.date + "\n";
    }

}
