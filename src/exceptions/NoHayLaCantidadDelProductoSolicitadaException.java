package exceptions;

public class NoHayLaCantidadDelProductoSolicitadaException extends RuntimeException{
    private int cantSolicitada;

    public NoHayLaCantidadDelProductoSolicitadaException(int cantSolicitada) {
        this.cantSolicitada = cantSolicitada;
    }

    public int getCantSolicitada() {
        return cantSolicitada;
    }

    @Override
    public String getMessage() {
        return "Hay productos con stock disponible menor al solicitado, la cual fue " + cantSolicitada;
    }
}
