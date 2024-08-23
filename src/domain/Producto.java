package domain;

import exceptions.SinGananciasException;

import java.time.LocalDate;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLongArray;

public abstract class Producto implements Cloneable{
    protected String id;
    public String nombre;
    public String descripcion;
    protected int cantStock;
    private float precioUnidad;
    private float ganancia;
    private boolean disponible;
    private double descuento;

    public Producto(String id, String nombre, String descripcion, int cantStock, float precioUnidad, float ganancia,double descuento) {
        if ((ganancia <= precioUnidad) && (cantStock<=0)) {
            throw new SinGananciasException(precioUnidad,ganancia);
        }else{
            this.id = id;
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.cantStock = cantStock;
            this.precioUnidad = precioUnidad;
            this.ganancia = ganancia;
            this.disponible = true;
            this.descuento = descuento;
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
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

    public boolean getDisponible(){
        return disponible;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setCantStock(int cantStock) {
        this.cantStock = cantStock;
    }

    public float getPrecioUnidad() {
        return precioUnidad;
    }

    public void setPrecioUnidad(float precioUnidad) {
        this.precioUnidad = precioUnidad;
    }

    public float getGanancia() {
        return ganancia;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return id.equals(producto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return " id= " + id + "\n" +
                " nombre= " + nombre + '\n' +
                " descripcion='" + descripcion + '\n'+
                " cantStock=" + cantStock + '\n' +
                " precioUnidad=" + precioUnidad + '\n' +
                " ganancia=" + ganancia + "%" + '\n' +
                " disponible=" + disponible;
    }

    public Object getPrecio() {
        return null;
    }
}
