package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String url = "jdbc:mysql://root:2Bnh775vzxAgL9iogyqL@containers-us-west-143.railway.app:7386/railway";
    private static final String user = "root";
    private static final String password = "2Bnh775vzxAgL9iogyqL";

    private static Connection com;

    public static Connection getConexao(){
         try {
            if (com == null || com.isClosed()) {
                com = DriverManager.getConnection(url, user, password);
            }
            return com;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Driver JDBC do MySQL não encontrado.");
            return;
        }
        
        Connection conexao = getConexao();
        if (conexao != null) {
            System.out.println("Conexão bem-sucedida!");
            getConexao();
        } else {
            System.err.println("Falha na conexão.");
        }
    }
}
