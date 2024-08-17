package domain.tiposProductos;

import domain.Producto;

import java.util.Objects;

public class Bebidas extends Producto {
    public float graduacionAlcoholica;
    private boolean importado;

    public Bebidas(String id, String nombre, String descripcion, int cantStock, float precioUnidad, float ganancia, boolean disponible, float graduacionAlcoholica, boolean importado) {
        super(id, nombre, descripcion, cantStock, precioUnidad, ganancia);
        if (!(id.substring(0, 2).equals("AC")) || (id.length() != 5)) {
            throw new IllegalArgumentException("La id de su producto envasado debe empezar con AB y ser seguido por tres numeros enteros");
        }
        this.graduacionAlcoholica = graduacionAlcoholica;
        this.importado = importado;
    }

    public float getGraduacionAlcoholica() {
        return graduacionAlcoholica;
    }

    public boolean isImportado() {
        return importado;
    }

    @Override
    public String toString() {
        return super.toString() + "Bebidas{" +
                "graduacionAlcoholica=" + graduacionAlcoholica +
                ", importado=" + importado +
                '}';
    }
}
