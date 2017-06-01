package br.com.enforce.hippov1.entities;

public enum TipoPagto {
    CartaoDeCredito(1, "Cartão de Crédito"),
    BOLETO(2, "Boleto");

    private int idTipoPagto;
    private String TipoPagto;

    private TipoPagto(int idTipoPagto, String TipoPagto) {
        this.idTipoPagto = idTipoPagto;
        this.TipoPagto = TipoPagto;
    }

    @Override
    public String toString() {
        return TipoPagto;
    }

    public int getIdTipoPagto() {
        return idTipoPagto;
    }

    public String getTipoPagto() {
        return TipoPagto;
    }
}