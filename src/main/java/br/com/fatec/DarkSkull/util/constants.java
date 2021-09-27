package br.com.fatec.DarkSkull.util;

public enum constants {
    PADRAO(0),PAGAMENTO(1), ENVIO(2), PAGAMENTO_E_ENVIO(3), PRINCIPAL(4);

    private final int code;

    constants(int tipopassado) {
        code = tipopassado;
    }

    public int getCode(){
        return code;
    }
}
