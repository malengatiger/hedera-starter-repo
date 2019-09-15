import com.hedera.hashgraph.sdk.Client;
import com.hedera.hashgraph.sdk.HederaException;
import com.hedera.hashgraph.sdk.account.AccountId;
import com.hedera.hashgraph.sdk.crypto.ed25519.Ed25519PrivateKey;
import com.hedera.hashgraph.sdk.crypto.ed25519.Ed25519PublicKey;
import examples.ExampleHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GenerateKeys {

    private final static Logger logger = LoggerFactory.getLogger(GenerateKeys.class);
    public static void main(String[] args) throws HederaException {

        Ed25519PrivateKey newKey = Ed25519PrivateKey.generate();
        Ed25519PublicKey newPublicKey = newKey.getPublicKey();

        logger.info("\uD83E\uDDE9 \uD83E\uDDE9 private key = \uD83E\uDD6C \uD83E\uDD6C " + newKey + " \uD83C\uDF3A ");
        logger.info("\uD83E\uDDE9 \uD83E\uDDE9 public key  = \uD83C\uDF3A \uD83C\uDF3A " + newPublicKey + " \uD83C\uDF3A ");

        getBalance();

    }
    public static void getBalance()throws HederaException {
        AccountId operatorId = ExampleHelper.getOperatorId();
        logger.info("\uD83E\uDDE9 \uD83E\uDDE9 operatorId = " + operatorId.toString());

        Client client = ExampleHelper.createHederaClient();
        logger.info("\uD83E\uDDE9 \uD83E\uDDE9 client = " + client.toString());
        long balance = client.getAccountBalance(operatorId);

        logger.info("\uD83E\uDDE9 \uD83E\uDDE9 balance = " + balance);
    }
}
