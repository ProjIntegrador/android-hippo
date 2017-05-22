package br.com.enforce.hippov1.rest;


public class ClienteLoginRest {

    private Long idCliente;
    private String emailCliente;
    private String senhaCliente;
    private String nomeUsuario;
    private String tipoPerfil;
    private boolean usuarioAtivo;

    public ClienteLoginRest(String emailCliente, String senhaCliente, String nomeUsuario, String tipoPerfil, boolean usuarioAtivo) {
        this.emailCliente = emailCliente;
        this.senhaCliente = senhaCliente;
        this.nomeUsuario = nomeUsuario;
        this.tipoPerfil = tipoPerfil;
        this.usuarioAtivo = usuarioAtivo;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public String getSenhaCliente() {
        return senhaCliente;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getTipoPerfil() {
        return tipoPerfil;
    }

    public boolean isUsuarioAtivo() {
        return usuarioAtivo;
    }
}
