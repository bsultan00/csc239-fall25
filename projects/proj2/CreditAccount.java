public class CreditAccount {
    // accounts unique number
    private String accountNum;
    // accounts max credit limit of 500 or 1000, stays the same after account
    // creation
    private double maxLimit;
    // accounts available credit available to make purchase with
    private double available;
    // symbol for card issuer
    private String issuerSymbol;

    // card issuer code symbols
    public static final String ISSUER_AMER_EXPRESS = "AE";
    public static final String ISSUER_VISA = "V";
    public static final String ISSUER_MASTER_CARD = "MC";
    public static final String ISSUER_DISCOVER = "DIS";
    public static final String ISSUER_DINERS_CLUB = "DINE";

    // card issuer starting digit for account number
    public static final int ISSUER_CODE_AE = 3;
    public static final int ISSUER_CODE_V = 4;
    public static final int ISSUER_CODE_MC = 5;
    public static final int ISSUER_CODE_DIS = 6;
    public static final int ISSUER_CODE_DINE = 7;

    // used everytime new account is requested to be created, takes user provided
    // card issuer symbol to start creating account object
    public CreditAccount(String issuerSymbol) {
        this.issuerSymbol = issuerSymbol;
    }

    // used when loading existing account object
    public CreditAccount(String accountNum, double available, double maxLimit) {
        this.accountNum = accountNum;
        this.available = available;
        this.maxLimit = maxLimit;
    }

    // once account num generation is done assign it to account object
    public void setAccountNum(String num) {
        this.accountNum = num;
    }

    // assigns max limit to account object and initalize avail. credit with same
    // value
    public void setMaxLimit(double limit) {
        // initialize available limit
        this.available = limit;
        this.maxLimit = limit;
    }

    public String getAccountNum() {
        return this.accountNum;
    }

    public double getMaxLimit() {
        return this.maxLimit;
    }

    public double getAvailableCredit() {
        return this.available;
    }

    public String getIssuerSymbol() {
        return this.issuerSymbol;
    }

    // makes sure amount requested for purchase doesnt exhaust available credit
    public boolean hasSufficientFunds(double amountRequested) {
        return amountRequested <= this.getAvailableCredit();
    }

    // updates available credit after a purchase or return transaction is made
    public void updateLimit(double amount) {
        if (amount > 0) { // for purchases, decrease available credit
            this.available -= amount;
        } else {
            // for refunds, add back value but only up to the max limit
            this.available = Math.min(this.maxLimit, this.available + Math.abs(amount));
        }
    }
}
