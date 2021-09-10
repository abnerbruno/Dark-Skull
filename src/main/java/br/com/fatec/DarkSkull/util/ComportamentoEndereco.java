package br.com.fatec.DarkSkull.util;

public enum ComportamentoEndereco {
    PADRAO(0),PAGAMENTO(1), ENVIO(2), PAGAMENTO_E_ENVIO(3);

    private final int code;

    ComportamentoEndereco(int tipopassado) {
        code = tipopassado;
    }

    public int getCode(){
        return code;
    }
}
