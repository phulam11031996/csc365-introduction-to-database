public class CreditCard {

    public String number; // unique
    public double currentBalance; // less than creditLimit
    public double creditLimit;
    public boolean isActive = false;
    public CCType type;

    public CreditCard(
            String number,
            double creditLimit,
            CCType type) {
        this.number = number;
        this.creditLimit = creditLimit;
        this.type = type;
        this.currentBalance = 0.0;
    }

    public String getNumber() {
        return this.number;
    }

    public double getCurrentBalance() {
        return this.currentBalance;
    }

    public double getCreditLimit() {
        return this.creditLimit;
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public void setCurrentBalance(double worthPurchase) {
        this.currentBalance = this.currentBalance + worthPurchase;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getCardInfo() {
        return " - number: " + this.number +
                " currentBalance: " + this.currentBalance +
                " creditLimit: " + this.creditLimit + "\n";
    }

    @Override
    public String toString() {
        return "- cardNumber: " + this.number +
                " currentBalance: " + this.currentBalance +
                " creditLimit: " + this.creditLimit +
                " isActive: " + this.isActive +
                " type: " + this.type + "\n";
    }

}
