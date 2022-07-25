import java.util.Date;

public class Payment {

    Date date;
    String cardNumber;
    double payed;

    public Payment(
            Date date,
            String cardNumber,
            double payed) {
        this.date = date;
        this.cardNumber = cardNumber;
        this.payed = payed;
    }

    // getters
    public Date getDate() {
        return this.date;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public double getPayed() {
        return this.payed;
    }

    @Override
    public String toString() {
        return "- date: " + this.date +
                " cardNumber: " + this.cardNumber +
                " payed: " + this.payed + "\n";
    }

}
