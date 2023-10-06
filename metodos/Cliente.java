package Metodos;

public class Cliente {

    private String nome_cliente;
    private String email_cliente;
    private String numero_tel_cliente;
    private String senha_cliente;

    
    public Cliente(String nome_cliente, String email_cliente, String numero_tel_cliente, String senha_cliente) {
        this.nome_cliente = nome_cliente;
        this.email_cliente = email_cliente;
        this.numero_tel_cliente = numero_tel_cliente;
        this.senha_cliente =  senha_cliente;
    }
    
    public Cliente() {
    }

    public String getNome_cliente() {
        return nome_cliente;
    }
    public String getEmail_cliente() {
        return email_cliente;
    }
    public String getNumero_tel_cliente() {
        return numero_tel_cliente;
    }
    public String getSenha_cliente() {
        return senha_cliente;
    }
    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }
    public void setEmail_cliente(String email_cliente) {
        this.email_cliente = email_cliente;
    }
    public void setNumero_tel_cliente(String numero_tel_cliente) {
        this.numero_tel_cliente = numero_tel_cliente;
    }
    public void setSenha_cliente(String sENHA_CLIENTE2) {
        this.senha_cliente = sENHA_CLIENTE2;
    }
    @Override
    public String toString() {
        return "Cliente:" + nome_cliente + ", Email=" + email_cliente
                + ", Numero de telefone=" + numero_tel_cliente;
    }
    
    

    
}

