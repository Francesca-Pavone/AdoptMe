package com.ispwproject.adoptme.engineering.dao.queries;

import java.sql.*;

public class CRUDQueries {

    // la utilizzo nel caso in cui viene modificato solo lo stato (richiesta rifiutata / accettata)
    public static void updateReqState(Statement stmt, int reqId, int reqStatus) throws SQLException  {
        String updateStatement = String.format("UPDATE Requests set status=%d WHERE requestId = %d", reqStatus, reqId);
        stmt.executeUpdate(updateStatement);
    }

    public static void deleteReq(Statement stmt, int reqId) throws SQLException  {
        String updateStatement = String.format("DELETE FROM Requests WHERE requestId = %d", reqId);
        stmt.executeUpdate(updateStatement);
    }

}