package exceptions;

public class MasDeTresProductosException extends RuntimeException{
    private int cantProductos;

    public MasDeTresProductosException(int cantProductos) {
        this.cantProductos = cantProductos;
    }

    public int getCantProductos() {
        return cantProductos;
    }

    @Override
    public String getMessage() {
        return "Se intento hacer una compra de " + cantProductos + " productos. Solo tienes permitido 3 por venta.";
    }

}