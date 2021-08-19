package com.ezequieldaniel.pedrapapeloutesoura;

import java.util.HashMap;

public enum enumOpcao {
    pedra("pedra"),
    papel("papel"),
    tesoura("tesoura");

    private String descri;

    enumOpcao(String descricao) {
        this.descri = descricao;
    }

    public String getDescricao(){
        return descri;
    }

}
