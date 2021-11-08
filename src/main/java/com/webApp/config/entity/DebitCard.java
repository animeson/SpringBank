package com.webApp.config.entity;

import java.util.Date;
import java.util.Objects;

public class DebitCard  {
        private double currentBalance;
        private String cardNumber;
        private Date expirationDate;
        private int CVV;

        public DebitCard(double currentBalance, String cardNumber, Date expirationDate, int CVV) {
            this.currentBalance = currentBalance;
            this.cardNumber = cardNumber;
            this.expirationDate = expirationDate;
            this.CVV = CVV;
        }

        public double getCurrentBalance() {
            return currentBalance;
        }

        public void setCurrentBalance(double currentBalance) {
            this.currentBalance = currentBalance;
        }

        public String getCardNumber() {
            return cardNumber;
        }

        public void setCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
        }

        public Date getExpirationDate() {
            return expirationDate;
        }

        public void setExpirationDate(Date expirationDate) {
            this.expirationDate = expirationDate;
        }

        public int getCVV() {
            return CVV;
        }

        public void setCVV(int CVV) {
            this.CVV = CVV;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DebitCard debitCard = (DebitCard) o;
            return Double.compare(debitCard.currentBalance, currentBalance) == 0 && CVV == debitCard.CVV && cardNumber.equals(debitCard.cardNumber) && expirationDate.equals(debitCard.expirationDate);
        }

        @Override
        public int hashCode() {
            return Objects.hash(currentBalance, cardNumber, expirationDate, CVV);
        }
}
