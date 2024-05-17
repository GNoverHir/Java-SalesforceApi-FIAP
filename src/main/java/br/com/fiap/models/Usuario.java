package br.com.fiap.models;

public class Usuario {
    private String nomeCompleto;
    private String email;
    private int telefone;
    private int tamanhoEmpresa;
    private String pais;
    private String idioma;
    private String senha;


    public Usuario() {
    }

    public Usuario(String idioma, String pais, int tamanhoEmpresa, int telefone, String senha, String email, String nomeCompleto) {
        this.idioma = idioma;
        this.pais = pais;
        this.tamanhoEmpresa = tamanhoEmpresa;
        this.telefone = telefone;
        this.senha = senha;
        this.email = email;
        this.nomeCompleto = nomeCompleto;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nomeCompleto='" + nomeCompleto + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", telefone=" + telefone +
                ", tamanhoEmpresa=" + tamanhoEmpresa +
                ", pais='" + pais + '\'' +
                ", idioma='" + idioma + '\'' +
                '}';
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public int getTamanhoEmpresa() {
        return tamanhoEmpresa;
    }

    public void setTamanhoEmpresa(int tamanhoEmpresa) {
        this.tamanhoEmpresa = tamanhoEmpresa;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
}
