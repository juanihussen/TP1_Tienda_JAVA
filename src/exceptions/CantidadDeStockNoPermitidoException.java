package exceptions;

public class CantidadDeStockNoPermitido extends RuntimeException{
    private int cantStockExcedido;

    public CantidadDeStockNoPermitido(int cantStockExcedido) {
        this.cantStockExcedido = cantStockExcedido;
    }

    public int getCantStockExcedido() {
        return cantStockExcedido;
    }

    @Override
    public String getMessage() {
        return "Se intento hacer una compra de " + cantStockExcedido +  ". No se pueden pedir mas de doce productos.";
    }
}
