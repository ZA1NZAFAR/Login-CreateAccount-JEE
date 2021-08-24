package JavaClasses;


import JavaClasses.User;
import Servlets.Login;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author z.zafar
 */
public class ConnectionDB {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/jeu?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static final String DB_USERNAME = "jeu";
    private static final String DB_PASSWORD = "java123";

    public boolean checkExist(String id, String pwd) throws SQLException {
        String query = "SELECT * FROM comptes";
        loadDriverClass();
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
                PreparedStatement ps = conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                if (rs.getString("Login").equals(id) && rs.getString("Motdepasse").equals(pwd) && rs.getLong("Age") >= 18) {
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
        return false;
    }

    public boolean createAccount(User user) throws SQLException {
        if (checkExist(user.getLogin(), user.getMdp())) {
            throw new SQLException("Utilisateur existe deja.");
        }
        String query = " INSERT INTO comptes (Nom, Prenom, Age, Login, Motdepasse)"
                + " VALUES (?, ?, ?, ?, ?)";
        loadDriverClass();
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
                PreparedStatement ps = conn.prepareStatement(query);) {
            ps.setString(1, user.getNom());
            ps.setString(2, user.getPrenom());
            ps.setInt(3, user.getAge());
            ps.setString(4, user.getLogin());
            ps.setString(5, user.getMdp());

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    private void loadDriverClass() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
