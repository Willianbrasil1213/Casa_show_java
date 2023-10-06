package Metodos;

public class CriarEventos {

    private int id;
    private String nome_evento,ingressoOuConvite,tipo_evento;
 
    private String data;
    private String hora;
    private String extras;
    private String classificacao;
    private String acesso;
    private static final int capacidade_maxima = 250;
    private String atracao;
    private String preco_total;
    private String convidados;
    private String preco_ingresso;
    private int quantidade_disponivel;

    public CriarEventos(String nome_evento, String data, String extras, String classificacao, String acesso,
            String atracao, String convidados) {
        this.nome_evento = nome_evento;
        this.data = data;
        this.extras = extras;
        this.classificacao = classificacao;
        this.acesso = acesso;
        this.atracao = atracao;
        this.convidados = convidados;
    }

    public CriarEventos(String nome_evento, String data, String extras, String classificacao, String acesso,
            String atracao, String precoIngresso, int quantidade_disponivel) {
        this.nome_evento = nome_evento;
        this.data = data;
        this.extras = extras;
        this.classificacao = classificacao;
        this.acesso = acesso;
        this.atracao = atracao;
        this.preco_ingresso = precoIngresso;
        this.quantidade_disponivel = quantidade_disponivel;
    }

    public CriarEventos() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome_evento() {
        return nome_evento;
    }

    public String getPreco_total() {
        return preco_total;
    }

    public void setPreco_total(String preco_total) {
        this.preco_total = preco_total;
    }

    public void setNome_evento(String nome_evento) {
        this.nome_evento = nome_evento;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getAcesso() {
        return acesso;
    }

    public void setAcesso(String acesso) {
        this.acesso = acesso;
    }

    public static int getCapacidadeMaxima() {
        return capacidade_maxima;
    }

    public String getAtracao() {
        return atracao;
    }

    public void setAtracao(String atracao) {
        this.atracao = atracao;
    }

    public String getConvidados() {
        return convidados;
    }

    public void setConvidados(String numConvidados) {
        this.convidados = numConvidados;
    }

    public String getPreco_ingresso() {
        return preco_ingresso;
    }

    public void setPreco_ingresso(String precoIngresso) {
        this.preco_ingresso = precoIngresso;
    }

    public int getQuantidade_disponivel() {
        return quantidade_disponivel;
    }

    public void setQuantidade_disponivel(int quantidade_disponivel) {
        this.quantidade_disponivel = quantidade_disponivel;
    }
    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

       public String getIngressoOuConvite() {
        return ingressoOuConvite;
    }

    public void setIngressoOuConvite(String ingressoOuConvite) {
        this.ingressoOuConvite = ingressoOuConvite;
    }

    public String getTipo_evento() {
        return tipo_evento;
    }

    public void setTipo_evento(String tipo_evento) {
        this.tipo_evento = tipo_evento;
    }

    @Override
    public String toString() {
        return "Criareventos [nome_evento=" + nome_evento + ", data=" + data +", hora=" + hora + ", extras=" + extras + ", classificacao="
                + classificacao + ", acesso=" + acesso + ", atracao=" + atracao + ", preco_total=" + preco_total
                + ", convidados=" + convidados + ", preco_ingresso=" + preco_ingresso + ", quantidade_disponivel="
                + quantidade_disponivel + "]";
    }
    
}
