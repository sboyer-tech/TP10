/*
* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jspmvc_exo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Diego
 */
public class DAO {

    private final DataSource myDataSource;

    /**
     *
     * @param dataSource la source de données à utiliser
     */
    public DAO(DataSource dataSource) {
        this.myDataSource = dataSource;
    }

    List<RemiseEntity> AllRemise() throws SQLException {
        List<RemiseEntity> result = new ArrayList<>();

        String sql = "SELECT * FROM DISCOUNT_CODE";
        try (Connection connection = myDataSource.getConnection();
                Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                result.add(new RemiseEntity(rs.getString("DISCOUNT_CODE"), rs.getFloat("RATE")));
            }
        }

        return result;
    }

    void AddRemise(String cle, float taux) throws SQLException {
        // Une requête SQL paramétrée
        String sql = "INSERT INTO APP.DISCOUNT_CODE VALUES (?, ?)";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Définir la valeur du paramètre
            stmt.setString(1, cle);
            stmt.setFloat(2, taux);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw ex;
        }

    }

    void DeleteRemise(String cle) throws SQLException {
        String sql = "DELETE FROM APP.DISCOUNT_CODE WHERE DISCOUNT_CODE=?";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Définir la valeur du paramètre
            stmt.setString(1, cle);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    void UpdateRemise(char cle, float taux) throws SQLException {
        String sql = "UPDATE APP.DISCOUNT_CODE SET RATE = ? WHERE DISCOUNT_CODE = ?";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setFloat(1, taux);
            stmt.setString(2, String.valueOf(cle));
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        }
    }

}
