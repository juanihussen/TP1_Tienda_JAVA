package domain.tiposProductos;

import domain.Producto;

public class ProductosEnvasados extends Producto {
    private String tipoEnvase;
    private boolean importado;

    public ProductosEnvasados(String id, String nombre, String descripcion, int cantStock, float precioUnidad, float ganancia, String tipoEnvase, boolean importado) {
        super(id, nombre, descripcion, cantStock, precioUnidad, ganancia);
        if (!(id.substring(0, 2).equals("AB")) || (id.length() != 5)) {
            throw new IllegalArgumentException("La id de su producto envasado debe empezar con AB y ser seguido por tres numeros enteros");
        }
        this.tipoEnvase = tipoEnvase;
        this.importado = importado;
    }

    public String getTipoEnvase() {
        return tipoEnvase;
    }

    public boolean isImportado() {
        return importado;
    }

    @Override
    public String toString() {
        return super.toString() + "ProductosEnvasados{" +
                "tipoEnvase='" + tipoEnvase + '\'' +
                ", importado=" + importado +
                '}';
    }
}
