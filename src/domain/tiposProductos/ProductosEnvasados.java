package domain.tiposProductos;

import domain.Producto;
import domain.interfaces.IaplicarImpuestos;
import domain.interfaces.IporcentajeDescuento;
import domain.interfaces.IporcentajeGanancia;

import java.time.LocalDate;

public class ProductosEnvasados extends Producto implements IporcentajeGanancia , IaplicarImpuestos, IporcentajeDescuento {
    private String tipoEnvase;
    private boolean importado;
    private LocalDate fechaVencimiento;
    private double calorias;

    public ProductosEnvasados(String id, String nombre, String descripcion, int cantStock, float precioUnidad, float ganancia, String tipoEnvase, boolean importado,LocalDate fechaVencimiento,double calorias, double descuento) {
        super(id, nombre, descripcion, cantStock, precioUnidad, ganancia,descuento);
        if (!(id.substring(0, 2).equals("AB")) || (id.length() != 5)) {
            throw new IllegalArgumentException("La id de su producto envasado debe empezar con AB y ser seguido por tres numeros enteros");
        }
        this.tipoEnvase = tipoEnvase;
        this.importado = importado;
        this.fechaVencimiento = fechaVencimiento;
        this.calorias = calorias;
        comprobarPorcentajes();
        comprobarDescuento();
        aplicarImpuestos();
    }

    public boolean isImportado() {
        return importado;
    }

    public void setImportado(boolean importado) {
        this.importado = importado;
    }

    @Override
    public void comprobarPorcentajes() {
        if(getGanancia() > 20){
            throw new IllegalArgumentException("El porcentaje de ganancia no puede ser mayor a 20%");
        }
    }

    @Override
    public void comprobarDescuento() {
        if(getDescuento() > 15){
            throw new IllegalArgumentException("El porcentaje de descuento de los productos envasados no pueden superar el 15%");
        }
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
                " tipoEnvase= " + tipoEnvase + '\n' +
                " importado= " + importado + "\n --------------------------------------------------";
    }
}
