import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class OwnershipCollection {

    HashMap<Integer, Ownership> collectionCustomers = new HashMap<Integer, Ownership>();
    HashMap<String, Ownership> collectionCardNumber = new HashMap<String, Ownership>();

    public OwnershipCollection() {
    }

    // getters
    public HashMap<String, Ownership> getCollectionCardNumbers() {
        return this.collectionCardNumber;
    }

    public HashMap<Integer, Ownership> getCollectionCustomers() {
        return this.collectionCustomers;
    }

    // methods
    public void addOwnership(int customerId, String ccNumber) {
        if (!collectionCustomers.containsKey(customerId)) {
            Ownership newOwnership = new Ownership(new HashMap<String, Boolean>(), null);
            this.collectionCustomers.put(customerId, newOwnership);
        }

        if (!collectionCardNumber.containsKey(ccNumber)) {
            Ownership newOwnership = new Ownership(null, new HashSet<Integer>());
            this.collectionCardNumber.put(ccNumber, newOwnership);
        }

        this.collectionCustomers.get(customerId).addCCNumber(ccNumber, true);
        this.collectionCardNumber.get(ccNumber).addCustomerId(customerId);
    }

    // query
    public void cancelCard(int customerId, String cardNumber, HashMap<Integer, Customer> customers) {
        if (this.collectionCustomers.containsKey(customerId) && customers.containsKey(customerId))
            this.collectionCustomers.get(customerId).getOwnerCardNumbers().put(cardNumber, false);
    }

    public void queryCardCustomerbyCardNumber(String cardNumber, HashMap<String, CreditCard> creditCard,
            HashMap<Integer, Customer> customers) {
        if (creditCard.containsKey(cardNumber)) {
            String number = creditCard.get(cardNumber).getNumber();
            double balance = creditCard.get(cardNumber).getCurrentBalance();
            double limit = creditCard.get(cardNumber).getCreditLimit();
            StringBuilder resultString = new StringBuilder();

            if (this.collectionCardNumber.containsKey(cardNumber)) {
                HashSet<Integer> ownerCustomerIds = this.collectionCardNumber.get(cardNumber).getOwnnerCustomerIds();
                Iterator hsIterator = ownerCustomerIds.iterator();
                resultString.append("customerIds: ");
                while (hsIterator.hasNext()) {
                    int customerId = (int) hsIterator.next();
                    String name = customers.get(customerId).getName();
                    resultString.append(name + ", ");
                }
            }

            System.out.format("card number: %s balance: %f limit: %f owners: %s\n",
                    number, balance, limit, resultString.toString());
        }
    }

    public void queryCreditByCustomerId(int customerId, HashMap<String, CreditCard> creditCards) {

        if (this.collectionCustomers.containsKey(customerId)) {
            HashMap<String, Boolean> ownerCardNumbers = this.collectionCustomers.get(customerId).getOwnerCardNumbers();
            StringBuilder resultString = new StringBuilder();
            Iterator hmIterator = ownerCardNumbers.entrySet().iterator();

            while (hmIterator.hasNext()) {
                Map.Entry mapElement = (Map.Entry) hmIterator.next();
                if (creditCards.containsKey(mapElement.getKey())) {
                    resultString.append(creditCards.get(mapElement.getKey()).getCardInfo());
                }
            }
            System.out.println(resultString.toString());
        }
    }

    public void displayCustomers() {
        this.collectionCustomers.forEach((key, value) -> System.out.println(key + "-" + value));
    }

    public void displayCCNumbers() {
        this.collectionCardNumber.forEach((key, value) -> System.out.println(key + "-" + value));
    }

}
