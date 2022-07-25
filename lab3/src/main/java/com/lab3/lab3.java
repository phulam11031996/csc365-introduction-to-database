package com.lab3;

import java.io.FileWriter;
import java.io.IOException;

import com.github.javafaker.Faker;

public class lab3 {

    public static Faker faker = new Faker();
    private String fileName = "output.sql";

    private String[] ssn = new String[1000];
    private String[] cardNumber = new String[1000];
    private String[] ownwershipSsn = new String[1000];
    private String[] ownwershipCardNumber = new String[1000];

    public lab3() {

        try {
            FileWriter myWriter = new FileWriter(this.fileName);

            // customer
            for (int i = 0; i < 1000; i++) {
                ssn[i] = faker.number().digits(7).toString();
                StringBuilder tempBuilder = new StringBuilder();
                tempBuilder.append("\"" + ssn[i] + "\", ");
                tempBuilder.append("\"" + faker.name().fullName() + "\", ");
                tempBuilder.append("\"" + faker.phoneNumber().cellPhone() + "\", ");
                tempBuilder.append("\"" + faker.address().fullAddress() + "\"");
                String tempStr = tempBuilder.toString();
                myWriter.write("INSERT INTO customer (ssn, customerName, phone, address) VALUES (" + tempStr + ");\n");
            }

            // card
            double[] limit = { 1000, 1000, 1000, 1000, 1000, 2000, 2000, 2000, 5000, 5000, 10000 };
            for (int i = 0; i < 1000; i++) {
                cardNumber[i] = faker.number().digits(12).toString();
                StringBuilder tempBuilder = new StringBuilder();
                tempBuilder.append("\"" + cardNumber[i] + "\", ");
                tempBuilder.append(limit[faker.number().numberBetween(0, 10)] + ", ");
                tempBuilder.append(faker.number().numberBetween(1, 5));
                String tempStr = tempBuilder.toString();
                myWriter.write("INSERT INTO card (cardNumber, creditLimit, cardType) VALUES (" + tempStr + ");\n");
            }

            // vender
            for (int i = 0; i < 100; i++) {
                StringBuilder tempBuilder = new StringBuilder();
                tempBuilder.append("\"" + faker.company().industry() + "\", ");
                tempBuilder.append("\"" + faker.address().fullAddress() + "\"");
                String tempStr = tempBuilder.toString();
                myWriter.write("INSERT INTO vender (venderName, location) VALUES (" + tempStr + ");\n");
            }

            // ownership
            for (int i = 0; i < 1000; i++) {
                StringBuilder tempBuilder = new StringBuilder();
                int idx = faker.number().numberBetween(0, 999);
                ownwershipCardNumber[i] = cardNumber[idx];
                ownwershipSsn[i] = ssn[i];
                tempBuilder.append("\"" + ssn[i] + "\", ");
                tempBuilder.append("\"" + ownwershipCardNumber[i] + "\"");
                String tempStr = tempBuilder.toString();
                myWriter.write("INSERT INTO ownership (ssn, cardNumber) VALUES (" + tempStr + ");\n");
            }

            // transaction
            for (int i = 0; i < 2000; i++) {
                String year = String.valueOf(faker.number().numberBetween(2010, 2022));
                String month = String.valueOf(faker.number().numberBetween(1, 12));
                String day = String.valueOf(faker.number().numberBetween(1, 28));
                String date = year + "-" + month + "-" + day;

                StringBuilder tempBuilder = new StringBuilder();
                tempBuilder.append(faker.number().numberBetween(1, 100) + ", ");
                tempBuilder.append("\"" + ownwershipSsn[i / 2] + "\", ");
                tempBuilder.append("\"" + ownwershipCardNumber[i / 2] + "\", ");
                tempBuilder.append("\"" + faker.number().numberBetween(5, 100) + "\", ");
                tempBuilder.append("\"" + date + "\"");
                String tempStr = tempBuilder.toString();
                myWriter.write("INSERT INTO transaction (venderId, ssn, cardNumber, amount, date) VALUES (" + tempStr
                        + ");\n");
            }

            // payment
            for (int i = 0; i < 5000; i++) {
                String year = String.valueOf(faker.number().numberBetween(2010, 2022));
                String month = String.valueOf(faker.number().numberBetween(1, 12));
                String day = String.valueOf(faker.number().numberBetween(1, 28));
                String date = year + "-" + month + "-" + day;

                StringBuilder tempBuilder = new StringBuilder();
                tempBuilder.append("\"" + date + "\", ");
                tempBuilder.append("\"" + cardNumber[faker.number().numberBetween(0, 999)] + "\"");
                String tempStr = tempBuilder.toString();
                myWriter.write("INSERT INTO payment (payDate, cardNumber) VALUES (" + tempStr
                        + ");\n");
            }

            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        lab3 lab3 = new lab3();
    }

}