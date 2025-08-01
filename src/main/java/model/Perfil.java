package model;

public class Perfil {
    private int id;
    private String nombre;
    private int puntuacion;
    private int highscore;

    public Perfil(){
        this(0,"",0,0);
    }

    public Perfil(int id, String nombre, int puntuacion, int highscore) {
        this.id = id;
        this.nombre = nombre;
        this.puntuacion = puntuacion;
        this.highscore = highscore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
