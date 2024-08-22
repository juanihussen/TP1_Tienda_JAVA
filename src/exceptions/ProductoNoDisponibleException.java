package exceptions;

public class ProductoNoDisponibleException extends RuntimeException{
    private String id;
    private String nombre;

    public ProductoNoDisponibleException(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String getMessage() {
        return "El producto " + id + " " + nombre + " no se encuentra disponible.";
    }
}
