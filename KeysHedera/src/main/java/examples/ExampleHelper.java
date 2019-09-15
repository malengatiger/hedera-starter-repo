package examples;

import com.hedera.hashgraph.sdk.Client;
import com.hedera.hashgraph.sdk.account.AccountId;
import com.hedera.hashgraph.sdk.crypto.ed25519.Ed25519PrivateKey;
import io.github.cdimascio.dotenv.Dotenv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Objects;

public final class ExampleHelper {
    private ExampleHelper() { }
    private final static Logger logger = LoggerFactory.getLogger(ExampleHelper.class);
    private static Dotenv getEnv() {
        // Load configuration from the environment or a $projectRoot/.env file, if present
        // See .env.sample for an example of what it is looking for
        return Dotenv.load();
    }

    public static AccountId getNodeId() {
        AccountId accountId = AccountId.fromString(Objects.requireNonNull(getEnv().get("NODE_ID")));
        logger.info("\uD83C\uDF4F \uD83C\uDF4F getNodeId: accountId from NODE_ID .env = " + accountId.toString());
        return accountId;
    }

    public static AccountId getOperatorId() {
        AccountId accountId = AccountId.fromString(Objects.requireNonNull(getEnv().get("OPERATOR_ID")));
        logger.info("\uD83C\uDF4F \uD83C\uDF4F getOperatorId: accountId from OPERATOR_ID .env = " + accountId.toString());
        return accountId;
    }

    public static Ed25519PrivateKey getOperatorKey() {
        Ed25519PrivateKey key = Ed25519PrivateKey.fromString(Objects.requireNonNull(getEnv().get("OPERATOR_KEY")));
        logger.info("\uD83C\uDF4F \uD83C\uDF4F getOperatorKey: accountId from nodeID .env = " + key.toString());
        return key;
    }

    public static Client createHederaClient() {
        // To connect to a network with more nodes, add additional entries to the network map
        String nodeAddress = Objects.requireNonNull(getEnv().get("NODE_ADDRESS"));
        logger.info("\uD83E\uDDE9 \uD83E\uDDE9 createHederaClient: nodeAddress from .env = " + nodeAddress);
        Client client = new Client(Map.of(getNodeId(), nodeAddress));

        // Defaults the operator account ID and key such that all generated transactions will be paid for
        // by this account and be signed by this key
        client.setOperator(getOperatorId(), getOperatorKey());

        return client;
    }

    public static byte[] parseHex(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];

        int i = 0;

        //noinspection NullableProblems
        for (Integer c : (Iterable<Integer>) hex.chars()::iterator) {
            if ((i % 2) == 0) {
                // high nibble
                data[i / 2] = (byte) (Character.digit(c, 16) << 4);
            } else {
                // low nibble
                data[i / 2] &= (byte) Character.digit(c, 16);
            }

            i++;
        }

        return data;
    }
}
