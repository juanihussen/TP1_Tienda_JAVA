package domain.tiposProductos;
import domain.Producto;

public class ProductosLimpieza extends Producto {
    private AplicacionLimpieza tipoAplicacion;

    public ProductosLimpieza(String id, String nombre, String descripcion, int cantStock, float precioUnidad, float ganancia, boolean disponible, AplicacionLimpieza tipoAplicacion) {
        super(id, nombre, descripcion, cantStock, precioUnidad, ganancia);
        // Verificar los primeros dos caracteres
        if (!(id.substring(0, 2).equals("AZ")) || (id.length() != 5)) {
            throw new IllegalArgumentException("La id de su producto envasado debe empezar con AZ y ser seguido por tres numeros enteros");
        }
        this.tipoAplicacion = tipoAplicacion;
    }

    public AplicacionLimpieza getTipoAplicacion() {
        return tipoAplicacion;
    }

    @Override
    public String toString() {
        return super.toString() +  "ProductosLimpieza{" +
                "tipoAplicacion=" + tipoAplicacion +
                '}';
    }
}

