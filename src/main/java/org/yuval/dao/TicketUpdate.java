package org.yuval.dao;

/**
 * interface to handle ticket update
 */
public interface TicketUpdate {
    void setTicket(int row, int column, String showInstanceID, String user, int showId);
}

