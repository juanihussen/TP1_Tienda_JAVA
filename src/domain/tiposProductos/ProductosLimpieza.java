package domain.tiposProductos;
import domain.Producto;
import domain.interfaces.IporcentajeGanancia;

public class ProductosLimpieza extends Producto implements IporcentajeGanancia {
    private AplicacionLimpieza tipoAplicacion;

    public ProductosLimpieza(String id, String nombre, String descripcion, int cantStock, float precioUnidad, float ganancia, AplicacionLimpieza tipoAplicacion) {
        super(id, nombre, descripcion, cantStock, precioUnidad, ganancia);
        // Verificar los primeros dos caracteres
        if (!(id.substring(0, 2).equals("AZ")) || (id.length() != 5)) {
            throw new IllegalArgumentException("La id de su producto envasado debe empezar con AZ y ser seguido por tres numeros enteros");
        }
        this.tipoAplicacion = tipoAplicacion;
        comprobarPorcentajes();

    }

    public AplicacionLimpieza getTipoAplicacion() {
        return tipoAplicacion;
    }

    @Override
    public void comprobarPorcentajes() {
        if(((ganancia < (precioUnidad * 1.1)) || (ganancia > (precioUnidad * 1.25))) && ((tipoAplicacion == AplicacionLimpieza.BANIO) ||(tipoAplicacion == AplicacionLimpieza.ROPA))) {
            corregirPorcentajes();
        }
    }

    @Override
    public void corregirPorcentajes() {
        setGanancia((float) (precioUnidad * 1.25));
    }


    @Override
    public String toString() {
        return super.toString() + "\n" +
                "tipoAplicacion= " + tipoAplicacion + "\n" + "--------------------------------------------------";
    }


}

