package examples.advanced;

import com.hedera.hashgraph.sdk.Client;
import com.hedera.hashgraph.sdk.HederaException;
import com.hedera.hashgraph.sdk.TransactionRecord;
import com.hedera.hashgraph.sdk.account.AccountId;
import com.hedera.hashgraph.sdk.account.CryptoTransferTransaction;
import examples.ExampleHelper;


public final class TransferCrypto {
    private TransferCrypto() { }

    public static void main(String[] args) throws HederaException {
        AccountId operatorId = ExampleHelper.getOperatorId();
        Client client = ExampleHelper.createHederaClient();

        AccountId recipientId = AccountId.fromString("0.0.3");
        int amount = 10_000;

        long senderBalanceBefore = client.getAccountBalance(operatorId);
        long receiptBalanceBefore = client.getAccountBalance(recipientId);

        System.out.println("" + operatorId + " balance = " + senderBalanceBefore);
        System.out.println("" + recipientId + " balance = " + receiptBalanceBefore);

        TransactionRecord record = new CryptoTransferTransaction(client)
            // .addSender and .addRecipient can be called as many times as you want as long as the total sum from
            // both sides is equivalent
            .addSender(operatorId, amount)
            .addRecipient(recipientId, amount)
            .setMemo("transfer test")
            // As we are sending from the operator we do not need to explicitly sign the transaction
            .executeForRecord();

        System.out.println("transferred " + amount + "...");

        long senderBalanceAfter = client.getAccountBalance(operatorId);
        long receiptBalanceAfter = client.getAccountBalance(recipientId);

        System.out.println("" + operatorId + " balance = " + senderBalanceAfter);
        System.out.println("" + recipientId + " balance = " + receiptBalanceAfter);
        System.out.println("Transfer memo: " + record.getMemo());
    }
}
