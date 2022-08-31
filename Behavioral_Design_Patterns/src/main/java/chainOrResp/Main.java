package chainOrResp;

public class Main {
    public static void main(String[] args) throws Exception {
        Bank bank = new Bank(100);
        PayPal payPal = new PayPal(200);
        BitCoin bitCoin = new BitCoin(300);

        bank.setNext(payPal);
        payPal.setNext(bitCoin);

        bank.pay(259);
    }
}
