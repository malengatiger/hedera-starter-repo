package examples.advanced;

import com.google.protobuf.ByteString;
import com.hedera.hashgraph.sdk.Client;
import com.hedera.hashgraph.sdk.HederaException;

import com.hedera.hashgraph.sdk.file.FileContentsQuery;
import com.hedera.hashgraph.sdk.file.FileId;
import examples.ExampleHelper;

import java.io.FileOutputStream;
import java.io.IOException;

/** Get the network address book for inspecting the node public keys, among other things */
public final class GetAddressBook {
    private GetAddressBook() { }

    public static void main(String[] args) throws HederaException, IOException {
        final Client client = ExampleHelper.createHederaClient();
        final FileContentsQuery fileQuery = new FileContentsQuery(client)
            .setFileId(new FileId(0, 0, 102));

        final long cost = fileQuery.requestCost();
        System.out.println("file contents cost: " + cost);

        fileQuery.setPaymentDefault(100_000);

        final ByteString contents = fileQuery.execute().getFileContents().getContents();

        try (final FileOutputStream fos = new FileOutputStream("address-book.proto.bin")) {
            contents.newInput().transferTo(fos);
        }
    }
}
