package exceptions;

public class SaldoInsuficienteException extends RuntimeException{
    private float saldoCajaActual;
    private float precioProducto;

    public SaldoInsuficienteException(float saldoCajaActual, float precioProducto) {
        this.saldoCajaActual = saldoCajaActual;
        this.precioProducto = precioProducto;
    }

    public float getSaldoCajaActual() {
        return saldoCajaActual;
    }

    public float getPrecioProducto() {
        return precioProducto;
    }

    @Override
    public String getMessage() {
        return "El saldo actual de la tienda es de " + saldoCajaActual + " y es insuficiente para efectuar la compra de $" + precioProducto;
    }
}
