package domain;

import exceptions.SinGananciasException;

import java.util.Objects;

public abstract class Producto {
    protected String id;
    public String nombre;
    public String descripcion;
    protected int cantStock;
    protected float precioUnidad;
    protected float ganancia;
    public boolean disponible;

    public Producto(String id, String nombre, String descripcion, int cantStock, float precioUnidad, float ganancia) {
        if ((ganancia <= precioUnidad) && (cantStock<=0)) {
            throw new SinGananciasException(precioUnidad,ganancia);
        }else{
            this.id = id;
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.cantStock = cantStock;
            this.precioUnidad = precioUnidad;
            this.ganancia = ganancia;
            this.disponible = false;
        }
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantStock() {
        return cantStock;
    }

    public float getPrecioUnidad() {
        return precioUnidad;
    }

    public float getGanancia() {
        return ganancia;
    }

    public void setGanancia(float ganancia) {
        this.ganancia = ganancia;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto producto)) return false;
        return cantStock == producto.cantStock && Float.compare(precioUnidad, producto.precioUnidad) == 0 && Float.compare(ganancia, producto.ganancia) == 0 && disponible == producto.disponible && Objects.equals(id, producto.id) && Objects.equals(nombre, producto.nombre) && Objects.equals(descripcion, producto.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, descripcion, cantStock, precioUnidad, ganancia, disponible);
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", cantStock=" + cantStock +
                ", precioUnidad=" + precioUnidad +
                ", ganancia=" + ganancia +
                ", disponible=" + disponible +
                '}';
    }
}
