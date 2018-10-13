package br.com.poli.classes;

public enum difficulty {
    Easy("facil"),
    Normal("normal"),
    Hard("dificil");

    private String description;

    private difficulty(String s){
        this.description = s ;

    }

    public String getDescription() {
        return description;
    }
}
