package Metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import DAO.EventosoperacionaisDAO;
import conexao.Conexao;

public class Verificacoes {
    
    public static boolean testaData(String data){
            ArrayList<CriarEventos> eventos = EventosoperacionaisDAO.listarEventos();
            for(int i=0;i<eventos.size();i++){
                if(eventos.get(i).getData().equals(data)){
                    return false;
                }
            }
            if(data.isBlank()){
                return false;
            }
            return true;
        }

    public static boolean testaNomeEvento(String nome){
        ArrayList<CriarEventos> eventos = EventosoperacionaisDAO.listarEventos();
        for(int i=0;i<eventos.size();i++){
            if(eventos.get(i).getNome_evento().equals(nome)){
                return false;
            }
        }
        if (!nome.matches("^[a-zA-Z]*$") || nome.isBlank()){
            return false;
        }
        return true;
    }
    public static boolean testaTelefone(String telefone){
        List<String> dddsValidos = Arrays.asList("11", "12", "13", "14", "15", "16", "17", "18", "19",
            "21", "22", "24", "27", "28", "31", "32", "33", "34", "35", "37", "38",
            "41", "42", "43", "44", "45", "46", "47", "48", "49",
            "51", "53", "54", "55", "61", "62", "63", "64", "65", "66", "67", "68", "69",
            "71", "73", "74", "75", "77", "79",
            "81", "82", "83", "84", "85", "86", "87", "88", "89",
            "91", "92", "93", "94", "95", "96", "97", "98", "99");
            String ddd;
            try{
                ddd = telefone.substring(1, 3);
            }catch(StringIndexOutOfBoundsException e){
                return false;
            }
            
            if (telefone.length() != 11 || telefone.charAt(2) != '9' || !dddsValidos.contains(ddd) || telefone.isBlank()){
                return false;
            }
            else{
                return true;
            }
    }

    public static boolean testaNome(String nome){
        for(int i=0;i<nome.length();i++){
            if(!Character.isAlphabetic(nome.charAt(i))){
                return true;
            }
        }
        if(nome.isBlank()){
            return true;
        }
        return false;
    }

    public static boolean testaEmail(String email){
        ArrayList<Cliente> clientes = EventosoperacionaisDAO.listarClientes();
        for(int i=0;i<clientes.size();i++){
            if(clientes.get(i).getEmail_cliente().equals(email)){
                return false;
            }
        }
        if (email.endsWith("@gmail.com")){
            return true;
        }
        
        return false;
    }

public static ArrayList<Cliente> listarClientes(){
        String sql = "SELECT * FROM CLIENTES";
        ArrayList<Cliente> retorno = new ArrayList<>();
        
        try {
            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            int i = 0;
            while (rs.next()){
                retorno.add(new Cliente());
                retorno.get(i).setNome_cliente(rs.getString("nome_cliente"));
                retorno.get(i).setEmail_cliente(rs.getString("email_cliente"));
                retorno.get(i).setNumero_tel_cliente(rs.getString("telefone_cliente"));
                retorno.get(i).setSenha_cliente(rs.getString("senha_cliente"));
                i++;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return retorno;
    }


}
