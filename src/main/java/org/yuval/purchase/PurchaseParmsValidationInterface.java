package org.yuval.purchase;

import javax.ws.rs.BeanParam;
import javax.ws.rs.core.Response;

/**
 * Created by Yuval on 19-Apr-17.
 */
public interface PurchaseParmsValidationInterface {
    Response checkParameters(@BeanParam PurchaseFilterBean filterBean);
}
