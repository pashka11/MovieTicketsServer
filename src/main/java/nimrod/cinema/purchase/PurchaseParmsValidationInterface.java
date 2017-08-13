package nimrod.cinema.purchase;

import javax.ws.rs.BeanParam;
import javax.ws.rs.core.Response;


public interface PurchaseParmsValidationInterface {
    Response checkParameters(@BeanParam PurchaseFilterBean filterBean);
}
