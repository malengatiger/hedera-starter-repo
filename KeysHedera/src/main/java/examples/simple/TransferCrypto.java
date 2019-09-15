package examples.simple;

import com.hedera.hashgraph.sdk.Client;
import com.hedera.hashgraph.sdk.HederaException;
import com.hedera.hashgraph.sdk.account.AccountId;
import examples.ExampleHelper;

public final class TransferCrypto {
    private TransferCrypto() { }

    public static void main(String[] args) throws HederaException {
        Client client = ExampleHelper.createHederaClient();

        // Transfer X hbar from the operator of the client to the given account ID
        client.transferCryptoTo(AccountId.fromString("0.0.3"), 10_000);

        System.out.println("transferred 10_000 tinybar...");
    }
}
