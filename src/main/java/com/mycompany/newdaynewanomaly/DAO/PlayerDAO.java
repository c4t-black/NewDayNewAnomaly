package com.mycompany.newdaynewanomaly.DAO;

import com.mycompany.newdaynewanomaly.Models.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {

    private static final String SQL_INSERT =
            "INSERT INTO game.player_status " +
            "(current_day, money, sanity, anomalies_guessed, humans_guessed, " +
            " cola, big_cola, chips, glasses, saved_at) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())";

    private static final String SQL_FIND_LATEST =
            "SELECT id, current_day, money, sanity, anomalies_guessed, humans_guessed, " +
            "cola, big_cola, chips, glasses " +
            "FROM game.player_status " +
            "ORDER BY saved_at DESC " +
            "LIMIT 1";

    private static final String SQL_FIND_ALL =
            "SELECT id, current_day, money, sanity, anomalies_guessed, humans_guessed, " +
            "cola, big_cola, chips, glasses, saved_at " +
            "FROM game.player_status " +
            "ORDER BY saved_at DESC " +
            "LIMIT 10";

    private static final String SQL_FIND_BY_ID =
            "SELECT id, current_day, money, sanity, anomalies_guessed, humans_guessed, " +
            "cola, big_cola, chips, glasses " +
            "FROM game.player_status " +
            "WHERE id = ?";

    private static final String SQL_DELETE_ALL =
            "DELETE FROM game.player_status";

    // ---------------------------------------------------------------

    public void save(Player player) {
        Connection conn = null;
        try {
            conn = ConnectionFactory.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(SQL_INSERT)) {
                ps.setInt    (1, player.getCurrentDay());
                ps.setInt    (2, player.getMoney());
                ps.setFloat  (3, player.getSanity());
                ps.setInt    (4, player.getAnomaliesGuessed());
                ps.setInt    (5, player.getHumansGuessed());
                ps.setInt    (6, player.getCola());
                ps.setInt    (7, player.getBigCola());
                ps.setInt    (8, player.getChips());
                ps.setBoolean(9, player.isGlasses());
                ps.executeUpdate();
                System.out.println("[PlayerDAO] Estado salvo — Dia " + player.getCurrentDay());
            }
        } catch (SQLException e) {
            System.err.println("[PlayerDAO] Erro ao salvar: " + e.getMessage());
            throw new RuntimeException("Falha ao salvar estado do jogador.", e);
        } finally {
            ConnectionFactory.closeConnection(conn);
        }
    }

    public Player findLatest() {
        Connection conn = null;
        try {
            conn = ConnectionFactory.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(SQL_FIND_LATEST);
                 ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
                return null;
            }
        } catch (SQLException e) {
            System.err.println("[PlayerDAO] Erro ao recuperar jogador: " + e.getMessage());
            throw new RuntimeException("Falha ao carregar estado do jogador.", e);
        } finally {
            ConnectionFactory.closeConnection(conn);
        }
    }

    /**
     * Retorna os 10 saves mais recentes com seus IDs e timestamps,
     * para exibição na tela de seleção de saves.
     */
    public List<SaveEntry> findAllSaves() {
        List<SaveEntry> saves = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionFactory.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(SQL_FIND_ALL);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    saves.add(new SaveEntry(
                            rs.getInt      ("id"),
                            rs.getInt      ("current_day"),
                            rs.getInt      ("money"),
                            rs.getFloat    ("sanity"),
                            rs.getTimestamp("saved_at").toLocalDateTime()
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("[PlayerDAO] Erro ao listar saves: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(conn);
        }
        return saves;
    }

    /** Carrega um Player a partir de um ID específico de save. */
    public Player findById(int id) {
        Connection conn = null;
        try {
            conn = ConnectionFactory.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_ID)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) return mapRow(rs);
                }
            }
            return null;
        } catch (SQLException e) {
            System.err.println("[PlayerDAO] Erro ao carregar save por ID: " + e.getMessage());
            throw new RuntimeException("Falha ao carregar save.", e);
        } finally {
            ConnectionFactory.closeConnection(conn);
        }
    }

    public void deleteAll() {
        Connection conn = null;
        try {
            conn = ConnectionFactory.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(SQL_DELETE_ALL)) {
                int rows = ps.executeUpdate();
                System.out.println("[PlayerDAO] " + rows + " save(s) removido(s).");
            }
        } catch (SQLException e) {
            System.err.println("[PlayerDAO] Erro ao apagar saves: " + e.getMessage());
            throw new RuntimeException("Falha ao apagar saves.", e);
        } finally {
            ConnectionFactory.closeConnection(conn);
        }
    }

    private Player mapRow(ResultSet rs) throws SQLException {
        return new Player(
                rs.getInt    ("money"),
                rs.getInt    ("current_day"),
                rs.getFloat  ("sanity"),
                rs.getInt    ("anomalies_guessed"),
                rs.getInt    ("humans_guessed"),
                rs.getInt    ("cola"),
                rs.getInt    ("big_cola"),
                rs.getInt    ("chips"),
                rs.getBoolean("glasses")
        );
    }
}
