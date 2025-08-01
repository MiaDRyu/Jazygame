package model;

public class PerfilSesion {
    private static Perfil perfilActual;

    public static void setPerfilActual(Perfil perfil) {
        perfilActual = perfil;
    }

    public static Perfil getPerfilActual() {
        return perfilActual;
    }
}
