import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class App {

    public Scanner myObj;
    public String input;

    public CustomerCollection customer = new CustomerCollection();
    public OwnershipCollection ownership = new OwnershipCollection();
    public CreditCardCollection creditcard = new CreditCardCollection();
    public VenderCollection vender = new VenderCollection();
    public TransactionCollection transaction = new TransactionCollection();
    public PaymentCollection payment = new PaymentCollection();

    // get user input methods
    public int getInputInt(String prompt) {
        myObj = new Scanner(System.in); // Create a Scanner object
        System.out.print(prompt);
        input = myObj.nextLine(); // Read user input
        int inputInt = Integer.parseInt(input);
        return inputInt;
    }

    public double getInputDouble(String prompt) {
        myObj = new Scanner(System.in); // Create a Scanner object
        System.out.print(prompt);
        input = myObj.nextLine(); // Read user input
        double inputDouble = Double.parseDouble(input);
        return inputDouble;
    }

    public String getInputString(String prompt) {
        myObj = new Scanner(System.in); // Create a Scanner object
        System.out.print(prompt);
        input = myObj.nextLine(); // Read user input
        return input;
    }

    public CCType cardTypeInput() {
        myObj = new Scanner(System.in); // Create a Scanner object
        System.out.print("Enter type (1. VISA, 2.MC, 3.American, 4.Express, Other.Discover): ");
        input = myObj.nextLine(); // Read user input
        if (input.equals("1"))
            return CCType.VISA;

        if (input.equals("2"))
            return CCType.MC;

        if (input.equals("3"))
            return CCType.AMERICAN;

        if (input.equals("4"))
            return CCType.EXPRESS;

        return CCType.DISCORVER;

    }

    public int monthInput(String prompt) {
        myObj = new Scanner(System.in); // Create a Scanner object
        System.out.print("enter 1st month (1. Jan, 2. Feb, .... other. Dec)");
        input = myObj.nextLine(); // Read user input
        if (input.equals("1"))
            return Calendar.JANUARY;

        if (input.equals("2"))
            return Calendar.FEBRUARY;

        if (input.equals("3"))
            return Calendar.MARCH;

        if (input.equals("4"))
            return Calendar.APRIL;

        if (input.equals("5"))
            return Calendar.MAY;

        if (input.equals("6"))
            return Calendar.JUNE;

        if (input.equals("7"))
            return Calendar.JULY;

        if (input.equals("8"))
            return Calendar.AUGUST;

        if (input.equals("9"))
            return Calendar.SEPTEMBER;

        if (input.equals("10"))
            return Calendar.OCTOBER;

        if (input.equals("11"))
            return Calendar.DECEMBER;

        return Calendar.DECEMBER;
    }

    // methods
    public void addCustomer() {
        System.out.println("\nAdding customer.");
        String ssn = getInputString("enter ssn: ");
        String name = getInputString("enter name: ");
        String address = getInputString("enter address: ");
        String phoneNumber = getInputString("enter phone number: ");
        this.customer.addCustomer(ssn, name, address, phoneNumber);
    }

    public void addCard() {
        System.out.println("\nAdding credit card.");
        String cardNumber = getInputString("enter card number: ");
        double creditLimit = getInputDouble("enter credit limit: ");
        CCType type = cardTypeInput();
        int customerId = getInputInt("enter owner customerId: ");

        this.creditcard.addCreditCard(customerId, cardNumber, creditLimit, type, this.ownership, this.customer.getHashId());
    }

    public void addVender() {
        System.out.println("\nAdding vender.");
        String name = getInputString("enter vender name: ");
        String location = getInputString("enter vender locatoin: ");

        this.vender.addVender(name, location);
    }

    public void cancelCard() {
        System.out.println("\nCancelling card.");
        int customerId = getInputInt("enter customer id: ");
        String cardNumber = getInputString("enter card number: ");

        this.ownership.cancelCard(customerId, cardNumber, this.customer.getHashId());
    }

    public void activateCard() {
        System.out.println("\nActivating card.");
        String cardNumber = getInputString("enter card number: ");

        this.creditcard.activateCard(cardNumber);
    }

    public void addTransaction() {
        Date date = new GregorianCalendar(2022, Calendar.JULY, 11).getTime();

        System.out.println("\nAdding transaction.");
        int venderId = getInputInt("enter venderId: ");
        int customerId = getInputInt("enter customerId: ");
        String cardNumber = getInputString("enter card number: ");
        double worthPurchase = getInputDouble("enter worth purchase: ");

        this.transaction.addTransaction(
                venderId,
                customerId,
                cardNumber,
                worthPurchase,
                date,
                this.vender.getVenders(),
                this.customer.getHashId(),
                this.creditcard.getCreditCards());
    }

    public void addPayment() {
        Date date = new GregorianCalendar(2022, Calendar.JULY, 11).getTime();

        System.out.println("\nAdding payment.");
        String cardNumber = getInputString("enter card number: ");
        double payed = getInputDouble("enter pay amount: ");

        this.payment.addPayment(date, cardNumber, payed, this.creditcard.getCreditCards());
    }

    public void queryByCustomerId() {
        System.out.println("\nqueryCustomerById.");
        int customerId = getInputInt("enter customerId: ");

        this.customer.queryByCustomerId(customerId);
    }

    public void queryByCustomerSsn() {
        System.out.println("\nqueryCustomerBySsn.");
        String ssn = getInputString("enter ssn: ");

        this.customer.queryByCustomerSsn(ssn);
    }

    public void queryCreditByCustomerId() {
        System.out.println("\nqueryCreditByCustomerId.");
        int customerId = getInputInt("enter customerId: ");

        this.ownership.queryCreditByCustomerId(customerId, this.creditcard.getCreditCards());
    }

    public void queryCreditByCustomerSSN() {
        System.out.println("\nqueryCreditByCustomerSSN.");
        String ssn = getInputString("enter ssn: ");
        int customerId = this.customer.getHashSsn().get(ssn).getId();

        this.ownership.queryCreditByCustomerId(customerId, this.creditcard.getCreditCards());
    }

    public void queryCardCustomerbyCardNumber() {
        System.out.println("\nqueryCardCustomerByCardNumber.");
        String cardNumber = getInputString("enter card number: ");

        this.ownership.queryCardCustomerbyCardNumber(cardNumber, this.creditcard.getCreditCards(), this.customer.getHashId());
    }

    public void queryTransactionByDate() {
        System.out.println("\nqueryTransactionByDate.");
        Date date1 = new GregorianCalendar(
            getInputInt("enter 1st year: "), 
            monthInput("enter 1st month (1. Jan, 2. Feb, .... other. Dec)"),
            getInputInt("enter 1st day: "))
            .getTime();

        Date date2 = new GregorianCalendar(
            getInputInt("enter 2st year: "), 
            monthInput("enter 2st month (1. Jan, 2. Feb, .... other. Dec)"),
            getInputInt("enter 2st day: "))
            .getTime();
        this.transaction.queryDate(date1, date2);
    }


    public static void main(String[] args) throws Exception {

        App myApp = new App();

        boolean flag = true;

        while (flag) {
            System.out.println("1. Add a new customer.");
            System.out.println("2. Add a new credit card.");
            System.out.println("3. Add a new vender.");
            System.out.println("4. Cancel a  card.");
            System.out.println("5. Activate a card.");
            System.out.println("6. Add a new transaction.");
            System.out.println("7. Add a new payment.");
            System.out.println("8. queryCustomerById.");
            System.out.println("9. queryCustomerBySsn.");
            System.out.println("10. queryCreditByCustomerId.");
            System.out.println("11. queryCreditByCustomerSSN.");
            System.out.println("12. queryCardCustomerByCardNumber.");

            // myApp.customer.display();
            // myApp.creditcard.display();
            // myApp.vender.display();
            // myApp.payment.display();
            // myApp.transaction.display();

            String option = myApp.getInputString("Enter option (1-13): ");

            switch (option) {
                case "1":
                    myApp.addCustomer();
                    break;

                case "2":
                    myApp.addCard();
                    break;

                case "3":
                    myApp.addVender();
                    break;

                case "4":
                    myApp.cancelCard();
                    break;

                case "5":
                    myApp.activateCard();
                    break;

                case "6":
                    myApp.addTransaction();
                    break;

                case "7":
                    myApp.addPayment();
                    break;

                case "8":
                    myApp.queryByCustomerId();
                    break;

                case "9":
                    myApp.queryByCustomerSsn();
                    break;

                case "10":
                    myApp.queryCreditByCustomerId();
                    break;

                case "11":
                    myApp.queryCreditByCustomerSSN();
                    break;

                case "12":
                    myApp.queryCardCustomerbyCardNumber();
                    break;

                case "13":
                    myApp.queryTransactionByDate();
                    break;

                default:
                    break;
            }
        }
    }

}
