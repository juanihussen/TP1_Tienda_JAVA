package exceptions;

public class NoSeEncontroElProductoException extends RuntimeException{
    private String nombreProductoNoEncontrado;

    public NoSeEncontroElProductoException(String nombreProductoNoEncontrado) {
        this.nombreProductoNoEncontrado = nombreProductoNoEncontrado;
    }

    public String getNombreProductoNoEncontrado() {
        return nombreProductoNoEncontrado;
    }

    @Override
    public String getMessage() {
        return "No se encontro el producto buscado en el stock con el nombre de " + nombreProductoNoEncontrado + ".";
    }
}
