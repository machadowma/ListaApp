package com.github.machadowma.listaapp;

public class Animal {
    String nome, raca;
    Integer drawableId;

    public Animal(String nome, String raca) {
        this.nome = nome;
        this.raca = raca;
        switch (raca){
            case "gato":
                this.drawableId = R.drawable.gato;
                break;
            case "cachorro":
                this.drawableId = R.drawable.cachorro;
                break;
            case "coelho":
                this.drawableId = R.drawable.coelho;
                break;
            case "peixe":
                this.drawableId = R.drawable.peixe;
                break;
            default:
                this.drawableId = android.R.drawable.ic_menu_report_image;
                break;
        }

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public Integer getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(Integer drawableId) {
        this.drawableId = drawableId;
    }
}
