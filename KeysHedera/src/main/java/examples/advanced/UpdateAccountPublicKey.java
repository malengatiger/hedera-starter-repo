package examples.advanced;

import com.hedera.hashgraph.sdk.Client;
import com.hedera.hashgraph.sdk.HederaException;
import com.hedera.hashgraph.sdk.account.AccountId;
import com.hedera.hashgraph.sdk.account.AccountInfo;
import com.hedera.hashgraph.sdk.account.AccountUpdateTransaction;
import com.hedera.hashgraph.sdk.crypto.ed25519.Ed25519PrivateKey;
import examples.ExampleHelper;


public final class UpdateAccountPublicKey {
    private UpdateAccountPublicKey() { }

    public static void main(String[] args) throws HederaException {
        Client client = ExampleHelper.createHederaClient();
        client.setMaxTransactionFee(800000000);

        // First, we create a new account so we don't affect our account

        Ed25519PrivateKey originalKey = Ed25519PrivateKey.generate();
        AccountId accountId = client.createAccount(originalKey.getPublicKey(), 0);

        // Next, we update the key

        Ed25519PrivateKey newKey = Ed25519PrivateKey.generate();

        System.out.println(" :: update public key of account " + accountId);
        System.out.println("set key = " + newKey.getPublicKey());

        new AccountUpdateTransaction(client).setAccountForUpdate(accountId)
            .setKey(newKey.getPublicKey())
            // Sign with the previous key and the new key
            .sign(originalKey)
            .sign(newKey)
            .executeForReceipt();

        // Now we fetch the account information to check if the key was changed

        System.out.println(" :: getAccount and check our current key");

        AccountInfo info = client.getAccount(accountId);

        System.out.println("key = " + info.getKey());
    }
}
