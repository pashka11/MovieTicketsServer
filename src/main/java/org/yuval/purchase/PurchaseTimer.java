package org.yuval.purchase;

import static org.yuval.utils.Parameters.PURCHASE_TIMER_TIME_TO_SLEEP;

/**
 * Created by Yuval on 20-Mar-17.
 * this class represent the purchase timer hence handles the lock of a seat for X amount of time
 */
public class PurchaseTimer extends Thread {

    private int row, column, showId;
    private String instanceId;

    /**
     * constructor
     * @param row
     * @param column
     * @param showId
     * @param instanceId
     */
    public PurchaseTimer(int row, int column, int showId, String instanceId) {
        this.row = row;
        this.column = column;
        this.showId = showId;
        this.instanceId = instanceId;
    }

    /**
     * thread sleeps for the time defined in PURCHASE_TIMER_TIME_TO_SLEEP
     * after that the seat gets released (if not approved)
     */
    @Override
    public void run() {
        try {
            Thread.sleep(PURCHASE_TIMER_TIME_TO_SLEEP);
        } catch (Exception e) {
            System.out.println("Error from " + PurchaseTimer.class.getName());
        } finally {
            SingeltonShowInstanceMap singeltonShowInstanceMap= SingeltonShowInstanceMap.getInstance();
            MultitonShowInstance multitonShowInstance = singeltonShowInstanceMap.getMultitonShowInstance(instanceId);
            multitonShowInstance.releaseSeatAfterTimeout(row, column, showId, instanceId);
        }

    }
}
