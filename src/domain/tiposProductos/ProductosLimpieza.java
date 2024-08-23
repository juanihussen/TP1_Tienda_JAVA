package domain.tiposProductos;
import domain.Producto;
import domain.interfaces.IporcentajeDescuento;
import domain.interfaces.IporcentajeGanancia;

public class ProductosLimpieza extends Producto implements IporcentajeGanancia, IporcentajeDescuento {
    private AplicacionLimpieza tipoAplicacion;

    public ProductosLimpieza(String id, String nombre, String descripcion, int cantStock, float precioUnidad, float ganancia, AplicacionLimpieza tipoAplicacion,double descuento) {
        super(id, nombre, descripcion, cantStock, precioUnidad, ganancia,descuento);

        if (!(id.substring(0, 2).equals("AZ")) || (id.length() != 5)) {
            throw new IllegalArgumentException("La id de su producto envasado debe empezar con AZ y ser seguido por tres numeros enteros");
        }
        this.tipoAplicacion = tipoAplicacion;
        comprobarPorcentajes();
        comprobarDescuento();
    }

    public AplicacionLimpieza getTipoAplicacion() {
        return tipoAplicacion;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "tipoAplicacion= " + tipoAplicacion + "\n" + "--------------------------------------------------";
    }

    @Override
    public void comprobarPorcentajes() {
        if((getGanancia() < 10 || getGanancia() > 25) && (getTipoAplicacion() == AplicacionLimpieza.ROPA || getTipoAplicacion() == AplicacionLimpieza.BANIO) ){
            throw new IllegalArgumentException("Los productos de limpieza no podrá ser menor del 10% ni mayor al 25%, excepto los de tipo COCINA y MULTIUSO que no tendrán mínimo.");
        }
    }

    @Override
    public void comprobarDescuento() {
        if(getDescuento() > 20){
            throw new IllegalArgumentException("El porcentaje de descuento de los productos de limpieza no pueden superar el 20%");
        }
    }
}

