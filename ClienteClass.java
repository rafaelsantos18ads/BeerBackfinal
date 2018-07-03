package beerback.trabahodegraduacao.com.beerbackfinal;

class ClienteClass {

    String nomecadastro;
    String sobrenomecadastro;
    String cpfcadastro;
    String telefonecadastro;
    String usuariocadastro;
    String senhacadastro;

   public  ClienteClass(String id, String nome, String cpf, String sobrenome, String telefone){

   }

    public ClienteClass(String id, String nomecadastro, String sobrenomecadastro, String cpfcadastro, String telefonecadastro, String usuariocadastro, String senhacadastro) {
        this.nomecadastro = nomecadastro;
        this.sobrenomecadastro = sobrenomecadastro;
        this.cpfcadastro = cpfcadastro;
        this.telefonecadastro = telefonecadastro;
        this.usuariocadastro = usuariocadastro;
        this.senhacadastro = senhacadastro;
    }

    public String getNomecadastro() {
        return nomecadastro;
    }

    public String getSobrenomecadastro() {
        return sobrenomecadastro;
    }

    public String getCpfcadastro() {
        return cpfcadastro;
    }

    public String getTelefonecadastro() {
        return telefonecadastro;
    }

    public String getUsuariocadastro() {
        return usuariocadastro;
    }

    public String getSenhacadastro() {
        return senhacadastro;
    }
}