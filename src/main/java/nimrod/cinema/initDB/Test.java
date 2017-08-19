package nimrod.cinema.initDB;

import nimrod.cinema.dao.SingleMongoClient;
import nimrod.cinema.objects.Image;
import nimrod.cinema.utils.Constants;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.io.IOException;
import java.io.InputStream;

import static com.sun.org.apache.bcel.internal.util.SecuritySupport.getResourceAsStream;

/**
 * Created by Nimrod on 19-Mar-17.
 */
public class Test {
    public static void main(String[] args) {

        final Morphia morphia = new Morphia();

        // tell Morphia where to find your classes
        // can be called multiple times with different packages or classes
        morphia.mapPackage("nimrod.cinema.objects");

        // create the Datastore connecting to the default port on the local host
        final Datastore datastore =
                morphia.createDatastore(SingleMongoClient.getInstance().getClient(),
                                        Constants.DB.TICKET_DATABASE);
        datastore.ensureIndexes();

        InputStream stream = getResourceAsStream("/Images/acdc.jpg");

        int bufferSize = 0;
        try
        {
            bufferSize = stream.available();

            byte[] buffer = new byte[bufferSize];
            int bytesRead = stream.read(buffer);

            datastore.save(new Image("acdc.jpg", buffer));
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
