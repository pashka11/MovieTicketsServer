package nimrodpasha.cinema.filter;

import nimrodpasha.cinema.dao.Crud;
import nimrodpasha.cinema.dao.UserDao;
import nimrodpasha.cinema.utils.Parameters;
import org.bson.Document;
import org.glassfish.jersey.internal.util.Base64;
import nimrodpasha.cinema.utils.Encryption;
import nimrodpasha.cinema.utils.EncryptionInterface;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;


/**
 * this class handles authentication
 * in case of successful authentication the HTTP request will go through
 * in case of unsuccessful authentication an proper response is sent
 */
@Provider
public class SecurityFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if (requestContext.getUriInfo().getPath().contains(Parameters.SECURED_URL_LOGIN) || requestContext.getUriInfo().getPath().contains(Parameters.SECURED_URL_PURCHASE)) {
            List<String> list = requestContext.getHeaders().get(Parameters.AUTHORIZATION_HEADER_KEY);
            if (list != null && list.size() > 0) {
                String s = list.get(0);

                s = s.replaceFirst(Parameters.AUTHORIZATION_HEADER_PREFIX, "");
                String decodedString = Base64.decodeAsString(s);
                StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
                String userName = tokenizer.nextToken();
                String password = tokenizer.nextToken();

                //check if user exist
                Crud crud = new UserDao();
                Document userDocument = crud.read(userName);
                //user not found
                if (userDocument == null) {
                    Response unauthorizedStatus = Response
                            .status(Response.Status.UNAUTHORIZED)
                            .entity(Parameters.USER_DOES_NOT_EXIST)
                            .build();

                    requestContext.abortWith(unauthorizedStatus);
                    return;
                }
                //password does not match
                EncryptionInterface encryptionInterface = new Encryption();
                if (!password.equals(encryptionInterface.decrypt(userDocument.get(Parameters.USER_PASSWORD).toString()))) {
                    Response unauthorizedStatus = Response
                            .status(Response.Status.UNAUTHORIZED)
                            .entity(Parameters.PASSWORD_INCORRECT)
                            .build();

                    requestContext.abortWith(unauthorizedStatus);
                }
                //authentication is successful
                return;
            }
            //trying to login without authentication
            Response unauthorizedStatus = Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity(Parameters.AUTHENTICATION_FAILED)
                    .build();

            requestContext.abortWith(unauthorizedStatus);
        }
    }
}
