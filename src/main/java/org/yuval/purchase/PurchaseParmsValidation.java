package org.yuval.purchase;

import org.yuval.exceptions.ShowInstanceNotExistException;
import org.yuval.exceptions.ShowNotExistException;
import org.yuval.exceptions.TheaterOutOfBoundsException;
import org.yuval.exceptions.UserNotExistException;

import javax.ws.rs.BeanParam;
import javax.ws.rs.core.Response;

/**
 * Created by Yuval on 20-Mar-17.
 * if any parameter is invalid throw proper exception
 */
public class PurchaseParmsValidation implements CheckParameters{
    //if method returns null then all parameters are valid
    @Override
    public Response checkParameters(@BeanParam PurchaseFilterBean filterBean) {

        try {
            new ValidateInput().validateInput(filterBean);
        } catch (UserNotExistException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (ShowInstanceNotExistException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (ShowNotExistException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (TheaterOutOfBoundsException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return null;
    }

    @Override
    public Response checkParametersUnmarkedSeat(@BeanParam PurchaseFilterBean filterBean) {

        try {
            new ValidateInput().validateInputUnmarkedSeat(filterBean);
        } catch (UserNotExistException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (ShowInstanceNotExistException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (ShowNotExistException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

        return null;
    }
}