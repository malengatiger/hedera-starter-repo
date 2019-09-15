package examples.simple;

import com.hedera.hashgraph.sdk.Client;
import com.hedera.hashgraph.sdk.HederaException;
import com.hedera.hashgraph.sdk.account.AccountId;
import examples.ExampleHelper;

public final class GetAccountBalance {
    private GetAccountBalance() { }

    public static void main(String[] args) throws HederaException {
        AccountId operatorId = ExampleHelper.getOperatorId();
        System.out.println("\uD83E\uDDE9 \uD83E\uDDE9 operatorId = " + operatorId.toString());

        Client client = ExampleHelper.createHederaClient();
        System.out.println("\uD83E\uDDE9 \uD83E\uDDE9 client = " + client.toString());
        long balance = client.getAccountBalance(operatorId);

        System.out.println("\uD83E\uDDE9 \uD83E\uDDE9 balance = " + balance);
    }
}
