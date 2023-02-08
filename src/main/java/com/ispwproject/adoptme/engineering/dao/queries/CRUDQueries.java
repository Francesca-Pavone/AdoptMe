package com.ispwproject.adoptme.engineering.dao.queries;

import java.sql.*;

public class CRUDQueries {

    private CRUDQueries() {
        //costruttore privato
    }

    // la utilizzo nel caso in cui viene modificato solo lo stato (richiesta rifiutata / accettata)
    public static void updateReqStatus(Statement stmt, int reqId, int reqStatus) throws SQLException  {
        String updateStatement = String.format("UPDATE Requests set status=%d WHERE requestId = %d", reqStatus, reqId);
        stmt.executeUpdate(updateStatement);
    }

    public static void deleteReq(Statement stmt, int reqId) throws SQLException  {
        String updateStatement = String.format("DELETE FROM Requests WHERE requestId = %d", reqId);
        stmt.executeUpdate(updateStatement);
    }

    public static void insertFavorite(Statement stmt, int userId, int petId, int shelterId) throws SQLException {
        String updateStatement = String.format("INSERT INTO Favorites set userId=%d, petId=%d, shelterId=%d", userId, petId, shelterId);
        stmt.executeUpdate(updateStatement);
    }

    public static void removeFavorite(Statement stmt, int userId, int petId, int shelterId) throws SQLException {
        String updateStatement = String.format("DELETE FROM Favorites WHERE (userId=%d AND petId=%d AND shelterId=%d)", userId, petId, shelterId);
        stmt.executeUpdate(updateStatement);
    }
}