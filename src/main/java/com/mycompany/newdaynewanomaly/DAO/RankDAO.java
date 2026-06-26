package com.mycompany.newdaynewanomaly.DAO;

import com.mycompany.newdaynewanomaly.Models.RankEntry;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RankDAO {

    private static final String SQL_TOP10 =
            "SELECT " +
                    "ROW_NUMBER() OVER (ORDER BY current_day DESC, money DESC) AS position, " +
                    "current_day, " +
                    "money, " +
                    "sanity, " +
                    "anomalies_guessed, " +
                    "humans_guessed " +
                    "FROM game.player_status " +
                    "ORDER BY current_day DESC, money DESC " +
                    "LIMIT 10";

    public List<RankEntry> findTop10() {
        List<RankEntry> ranking = new ArrayList<>();
        Connection conn = null;

        try {
            conn = ConnectionFactory.getConnection();

            try (PreparedStatement ps = conn.prepareStatement(SQL_TOP10);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    ranking.add(new RankEntry(
                            rs.getInt  ("position"),
                            rs.getInt  ("current_day"),
                            rs.getInt  ("money"),
                            rs.getFloat("sanity"),
                            rs.getInt  ("anomalies_guessed"),
                            rs.getInt  ("humans_guessed")
                    ));
                }
            }

        } catch (SQLException e) {
            System.err.println("[RankDAO] Erro ao buscar ranking: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(conn);
        }

        return ranking;
    }
}