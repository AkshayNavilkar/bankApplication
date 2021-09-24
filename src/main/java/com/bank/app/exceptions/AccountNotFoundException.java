package com.bank.app.exceptions;

    public class AccountNotFoundException extends RuntimeException {
        private Integer accountNo;

        public AccountNotFoundException() {
            super();
        }

        public AccountNotFoundException(String msg, Integer accountNo) {
            super(msg);
            this.accountNo = accountNo;
        }

        public AccountNotFoundException(String msg, Integer accountNo, Throwable cause) {
            super(msg, cause);
            this.accountNo = accountNo;
        }

        public String toString() {
            return super.toString();
        }

        public String getMessage() {
            return super.getMessage() + "for the accountNumber" + accountNo;
        }

        public String getLocalizedMessage() {
            return "Account : " + accountNo + "doesn't exist";
        }

}