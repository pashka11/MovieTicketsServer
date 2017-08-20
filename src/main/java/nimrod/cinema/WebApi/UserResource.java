package nimrod.cinema.WebApi;

import nimrod.cinema.Managers.UserManager;
import nimrod.cinema.objects.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource
{
    @POST
    public Response ValidateUser(User user)
    {
        UserManager userManager = new UserManager();

        User validateuser = userManager.HandleValidateUser(user);

        return validateuser != null ?
                Response.ok(validateuser).build():
                Response.serverError().build();
    }
}
