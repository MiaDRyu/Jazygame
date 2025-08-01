package model;

public class PartidaSesion {
    private static Partida partidaActual;

    public static void setPartidaActual(Partida partida) {
        partidaActual = partida;
    }

    public static Partida getPartidaActual() {
        return partidaActual;
    }
}
