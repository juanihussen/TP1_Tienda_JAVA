package domain.tiposProductos;

import domain.Producto;
import domain.interfaces.IaplicarImpuestos;
import domain.interfaces.IporcentajeDescuento;
import domain.interfaces.IporcentajeGanancia;

import java.time.LocalDate;

public class Bebidas extends Producto implements IporcentajeGanancia, IaplicarImpuestos, IporcentajeDescuento {
    public float graduacionAlcoholica;
    private boolean importado;
    private LocalDate fechaVencimiento;
    private double calorias;

    public Bebidas(String id, String nombre, String descripcion, int cantStock, float precioUnidad, float ganancia, float graduacionAlcoholica, boolean importado,LocalDate fechaVencimiento,double calorias,double descuento) {
        super(id, nombre, descripcion, cantStock, precioUnidad, ganancia,descuento);
        if (!(id.substring(0, 2).equals("AC")) || (id.length() != 5)) {
            throw new IllegalArgumentException("La id de su producto envasado debe empezar con AB y ser seguido por tres numeros enteros");
        }
        this.graduacionAlcoholica = graduacionAlcoholica;
        this.importado = importado;
        this.fechaVencimiento = fechaVencimiento;
        this.calorias = calcularCaloriasBebidas();
        comprobarPorcentajes();
        comprobarDescuento();
        aplicarImpuestos();
    }

    public float getGraduacionAlcoholica() {
        return graduacionAlcoholica;
    }

    public boolean isImportado() {
        return importado;
    }

    public void setCalorias(double calorias) {
        this.calorias = calorias;
    }

    public double calcularCaloriasBebidas() {
        if( 2.1 <= graduacionAlcoholica && (graduacionAlcoholica  >= 4.5) ) {
            setCalorias(calorias * 1.25);
        }
        if( graduacionAlcoholica > 4.5 ) {
            setCalorias(calorias * 1.5);
        }
        return this.calorias;
    }

    @Override
    public void aplicarImpuestos() {
        if(importado){
            setPrecioUnidad((float) (getPrecioUnidad() * 1.12));
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                " graduacionAlcoholica= " + graduacionAlcoholica + "\n" +
                " importado= " + importado + "\n --------------------------------------------------";
    }

    @Override
    public void comprobarPorcentajes() {
        if(getGanancia() > 20){
            throw new IllegalArgumentException("El porcentaje de ganancia no puede ser mayor a 20%");
        }
    }

    @Override
    public void comprobarDescuento() {
        if(getDescuento() > 10){
            throw new IllegalArgumentException("El porcentaje de descuento de las bebidas no pueden superar el 10%");
        }
    }
}
