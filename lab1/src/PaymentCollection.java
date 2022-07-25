import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

public class PaymentCollection {

    LinkedList<Payment> payments = new LinkedList<Payment>();

    public PaymentCollection() {
    }

    public void addPayment(
            Date date,
            String cardNumber,
            double payed,
            HashMap<String, CreditCard> cards) {
        if (cards.containsKey(cardNumber) && payed > 0) {
            Payment newPayment = new Payment(date, cardNumber, payed);
            this.payments.add(newPayment);
            cards.get(cardNumber).setCurrentBalance(-payed);
        }
    }

    public LinkedList<Payment> getPayments() {
        return this.getPayments();
    }

    public void display() {
        for (Payment payment : payments)
            System.out.println(payment);
    }

}
