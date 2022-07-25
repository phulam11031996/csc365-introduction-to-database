import java.util.HashMap;

public class CreditCardCollection {
    HashMap<String, CreditCard> cards = new HashMap<String, CreditCard>();

    public CreditCardCollection() {
    }

    public void addCreditCard(
            int customerId,
            String cardNumber,
            double creditLimit,
            CCType type,
            OwnershipCollection ownershipCollection,
            HashMap<Integer, Customer> customers) {
        if (this.cards.containsKey(cardNumber) && customers.containsKey(customerId)) {
            ownershipCollection.addOwnership(customerId, cardNumber);
        } else if (customers.containsKey(customerId)) {
            CreditCard newCreditCard = new CreditCard(
                    cardNumber,
                    creditLimit,
                    type);
            this.cards.put(cardNumber, newCreditCard);
            ownershipCollection.addOwnership(customerId, cardNumber);
        }
    }

    public HashMap<String, CreditCard> getCreditCards() {
        return this.cards;
    }

    public void activateCard(String cardNumber) {
        if (this.cards.containsKey(cardNumber)) {
            this.cards.get(cardNumber).setIsActive(true);
        }
    }

    public void display() {
        this.cards.forEach((key, value) -> System.out.println(value));
    }

}
