package chainOrResp;

public abstract class Account {

    protected float balance;
    protected Account successor;

    public Account(float balance) {
        this.balance = balance;
    }

    public void setNext(Account account) {
        this.successor = account;
    }

    public void pay(float amountToPay) throws Exception {
        if (canPay(amountToPay)) {
            System.out.println("paid by " + getClass().getName());
        } else if (successor != null) {
            System.out.println("can't pay by " + getClass().getName());
            successor.pay(amountToPay);
        } else {
            throw new Exception("none can pay");
        }
    }

    private boolean canPay(float amountToPay) {
        return balance >= amountToPay;
    }
}
