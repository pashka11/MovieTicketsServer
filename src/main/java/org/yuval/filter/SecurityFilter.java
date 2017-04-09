package org.yuval.filter;

import org.bson.Document;
import org.glassfish.jersey.internal.util.Base64;
import org.yuval.dao.UserDao;
import org.yuval.utils.Encryption;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import static org.yuval.utils.Parameters.*;


/**
 * this class handles authentication
 * in case of successful authentication the HTTP request will go through
 * in case of unsuccessful authentication an proper response is sent
 */
@Provider
public class SecurityFilter implements ContainerRequestFilter{


	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		if(requestContext.getUriInfo().getPath().contains(SECURED_URL_LOGIN)||requestContext.getUriInfo().getPath().contains(SECURED_URL_PURCHASE)){
		List<String>list=requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
		if(list!=null&&list.size()>0){
			String s = list.get(0);
			
			s=s.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
			String decodedString= Base64.decodeAsString(s);
			StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
			String userName = tokenizer.nextToken();
			String password = tokenizer.nextToken();

			//check if user exist
			UserDao userDao = new UserDao();
			Document userDocument = userDao.read(userName);
			//user not found
			if (userDocument==null){
				Response unauthorizedStatus= Response
						.status(Response.Status.UNAUTHORIZED)
						.entity(USER_DOES_NOT_EXIST)
						.build();

				requestContext.abortWith(unauthorizedStatus);
				return;
			}
			//password does not match
			if (!password.equals(Encryption.decrypt(userDocument.get(USER_PASSWORD).toString()))){
				Response unauthorizedStatus= Response
						.status(Response.Status.UNAUTHORIZED)
						.entity(PASSWORD_INCORRECT)
						.build();

				requestContext.abortWith(unauthorizedStatus);
			}
			//authentication is successful
			return;
		}
		//trying to login without authentication
		Response unauthorizedStatus= Response
				.status(Response.Status.UNAUTHORIZED)
				.entity(AUTHENTICATION_FAILED)
				.build();
		
		requestContext.abortWith(unauthorizedStatus);
		}
	}
}
