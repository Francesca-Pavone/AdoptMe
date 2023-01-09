package com.ispwproject.adoptme.utils.dao;

import com.ispwproject.adoptme.model.Shelter;
import com.ispwproject.adoptme.utils.dao.queries.SimpleQueries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShelterDAOJDBC {

    private static String USER = "user1";
    private static String PASS = "user1";
    private static String DB_URL = "jdbc:mysql://127.0.0.1:3306/AdoptMe";
    private static String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";

    private ShelterDAOJDBC() {}

    public static List<Shelter> retrieveShelterByCity(String city) throws Exception {
        Statement stmt = null;
        Connection conn = null;
        List<Shelter> sheltersList = new ArrayList<>();
        try {
            Class.forName(DRIVER_CLASS_NAME);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            ResultSet resultSet = SimpleQueries.searchSheltersByCity(stmt, city);

            if (!resultSet.first()){
                Exception e = new Exception("No cities found that begin with that input: "+city);
                throw e;
            }

            resultSet.first();
            do{
                String shelterName = resultSet.getString("name");
                String shelterImage = resultSet.getString("profileImg");

                Shelter shelter = new Shelter(shelterName, shelterImage);

                sheltersList.add(shelter);

            }while(resultSet.next());

            resultSet.close();

        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return sheltersList;
    }

    /*public static List<String> retrieveCityByInputCity(String city) throws Exception {

        Statement stmt = null;
        Connection conn = null;
        List<String> cityList = new ArrayList<String>();
        try {
            Class.forName(DRIVER_CLASS_NAME);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            ResultSet resultSet = SimpleQueries.selectCity(stmt, city);

            if (!resultSet.first()){
                Exception e = new Exception("No cities found that begin with that input: "+city);
                throw e;
            }

            resultSet.first();
            do{
                String foundCity = resultSet.getString("city");

                cityList.add(foundCity);

            }while(resultSet.next());

            resultSet.close();

        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return cityList;
    }*/
}
