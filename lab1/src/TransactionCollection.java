import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

public class TransactionCollection {

    TreeMap<Date, Transaction> collectionTrans = new TreeMap<Date, Transaction>();

    public TransactionCollection() {
    }

    public void addTransaction(
            int venderId,
            int customerId,
            String cardNumber,
            double worthPurchase,
            Date date,
            HashMap<Integer, Vender> venders,
            HashMap<Integer, Customer> customers,
            HashMap<String, CreditCard> creditcards) {
        double balance = creditcards.get(cardNumber).getCurrentBalance();
        double limit = creditcards.get(cardNumber).getCreditLimit();

        if (!this.collectionTrans.containsKey(date) &&
                venders.containsKey(venderId) &&
                customers.containsKey(customerId) &&
                creditcards.containsKey(cardNumber) &&
                limit >= worthPurchase + balance) {
            Transaction newTransaction = new Transaction(
                    venderId,
                    customerId,
                    cardNumber,
                    worthPurchase,
                    date);
            this.collectionTrans.put(date, newTransaction);
            creditcards.get(cardNumber).setCurrentBalance(worthPurchase);
            System.out.println("hello");
        }
    }

    public TreeMap<Date, Transaction> getCollectionTrans() {
        return this.collectionTrans;
    }

    public void queryDate(Date from, Date to) {
        this.collectionTrans.subMap(from, to).forEach((key, value) -> System.out.println(value));
    }

    public void display() {
        this.collectionTrans.forEach((key, value) -> System.out.println(value));
    }

}