package model;

public class Expresion {
    private int id;
    private String expresion;
    private String tipoExpresion;
    private String tipoSolucion;
    private String solucion;

    public Expresion(int id, String expresion, String tipoExpresion, String tipoSolucion, String solucion) {
        this.id = id;
        this.expresion = expresion;
        this.tipoExpresion = tipoExpresion;
        this.tipoSolucion = tipoSolucion;
        this.solucion = solucion;
    }

    public Expresion(){
        this(0,"","","","");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExpresion() {
        return expresion;
    }

    public void setExpresion(String expresion) {
        this.expresion = expresion;
    }

    public String getTipoExpresion() {
        return tipoExpresion;
    }

    public void setTipoExpresion(String tipoExpresion) {
        this.tipoExpresion = tipoExpresion;
    }

    public String getTipoSolucion() {
        return tipoSolucion;
    }

    public void setTipoSolucion(String tipoSolucion) {
        this.tipoSolucion = tipoSolucion;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }
}
