package nimrodpasha.cinema.login;

import org.bson.Document;
import org.glassfish.jersey.internal.util.Base64;
import nimrodpasha.cinema.dao.Crud;
import nimrodpasha.cinema.dao.UserDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.StringTokenizer;

import static nimrodpasha.cinema.utils.Parameters.*;

/**
 * Created by Yuval on 16-Mar-17.
 * this class is for Authentication
 * and to determine if user is admin or not
 */
@Path("authenticate")
public class Authenticate {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String securedMethod(ContainerRequestContext requestContext) {
        //get the user name
        List<String> list = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
        if (list != null && list.size() > 0) {
            String s = list.get(0);

            s = s.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
            String decodedString = Base64.decodeAsString(s);
            StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
            String userName = tokenizer.nextToken();

            //decide if user is an admin
            Crud crud = new UserDao();
            Document userDocument = crud.read(userName);
            if ((boolean) userDocument.get(USER_IS_ADMIN)) {
                return ADMIN_IS_AUTHENTICATED;
            }
            return USER_IS_AUTHENTICATED;
        }

        return AUTHENTICATION_FAILED;
    }
}
