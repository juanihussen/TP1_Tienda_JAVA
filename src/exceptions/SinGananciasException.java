package exceptions;

public class SinGananciasException extends RuntimeException{
    private float precioUnitario;
    private float gananciaProducto;

    public SinGananciasException(float precioUnitario, float gananciaProducto) {
        this.precioUnitario = precioUnitario;
        this.gananciaProducto = gananciaProducto;
    }

    public float getPrecioUnitario() {
        return precioUnitario;
    }

    public float getGananciaProducto() {
        return gananciaProducto;
    }

    @Override
    public String getMessage() {
        return "La ganancia del producto " + "$" + gananciaProducto + " es menor al precio unitario del mismo " + precioUnitario;
    }
}
