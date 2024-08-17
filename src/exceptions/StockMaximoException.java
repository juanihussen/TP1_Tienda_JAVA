package exceptions;

public class StockMaximoException extends RuntimeException {
    private int stockMaximo;

    public StockMaximoException(int stockMaximo) {
        this.stockMaximo = stockMaximo;
    }

    public int getStockMaximo() {
        return stockMaximo;
    }

    @Override
    public String getMessage() {
        return "El espacio disponible actual en el stock es de " + stockMaximo + " .Vende productos de la tienda para poder comprar mas.";
    }
}
