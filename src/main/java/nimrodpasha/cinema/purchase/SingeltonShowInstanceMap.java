package nimrodpasha.cinema.purchase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yuval on 17-Mar-17.
 * only one instance of this class can exist
 * this class handles requests for show instances
 */
public class SingeltonShowInstanceMap {

    private static volatile SingeltonShowInstanceMap instance;
    private static Map<String, MultitonShowInstance> singeltonMap = new HashMap<String, MultitonShowInstance>();

    //only one instance can be created
    public static SingeltonShowInstanceMap getInstance() {
        if (instance == null) {
            synchronized (SingeltonShowInstanceMap.class) {
                if (instance == null) {
                    instance = new SingeltonShowInstanceMap();
                }
            }
        }
        return instance;
    }

    /**
     * the keys of the map are the id's of the show instance
     * if the value assign to the key is null we create a new one and if its not null return an instance
     * @param showInstanceId the id of the show instance to return
     * @return instance of MultitonShowInstance
     */
    public MultitonShowInstance getMultitonShowInstance(String showInstanceId) {
        if (singeltonMap.get(showInstanceId) == null) {

            synchronized (SingeltonShowInstanceMap.class) {
                singeltonMap.computeIfAbsent(showInstanceId, k -> new MultitonShowInstance(showInstanceId));
            }
        }
        return (MultitonShowInstance) singeltonMap.get(showInstanceId);
    }
}
