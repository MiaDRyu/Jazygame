package model;

public class Partida {
    private int id;
    private Perfil perfil;
    private int etapa;
    private int puntaje;

    public Partida(int id,Perfil perfil, int etapa, int puntaje) {
        this.id = id;
        this.perfil = perfil;
        this.etapa = etapa;
        this.puntaje = puntaje;
    }

    public Partida (){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
}
