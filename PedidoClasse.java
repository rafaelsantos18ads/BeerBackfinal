package beerback.trabahodegraduacao.com.beerbackfinal;

class PedidoClasse {

    public String id_pedido;
    public String descricao;
    public String valor;
    public String cpf;


    public PedidoClasse(){

    }

    public String getCpf() {
        return cpf;
    }

    public PedidoClasse(String id_pedido, String descricao, String valor, String cpf) {
        this.id_pedido = id_pedido;
        this.descricao = descricao;
        this.valor = valor;
        this.cpf= cpf;

    }

    public String getId_pedido() {
        return id_pedido;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setId_pedido(String id_pedido) {
        this.id_pedido = id_pedido;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return ""+descricao+"\n"+valor;
    }
}

