package domain.tiposProductos;

import domain.Producto;
import domain.interfaces.IaplicarImpuestos;
import domain.interfaces.IporcentajeGanancia;

import java.time.LocalDate;

public class ProductosEnvasados extends Producto implements IporcentajeGanancia , IaplicarImpuestos {
    private String tipoEnvase;
    private boolean importado;
    private LocalDate fechaVencimiento;
    private double calorias;

    public ProductosEnvasados(String id, String nombre, String descripcion, int cantStock, float precioUnidad, float ganancia, String tipoEnvase, boolean importado,LocalDate fechaVencimiento,double calorias) {
        super(id, nombre, descripcion, cantStock, precioUnidad, ganancia);
        if (!(id.substring(0, 2).equals("AB")) || (id.length() != 5)) {
            throw new IllegalArgumentException("La id de su producto envasado debe empezar con AB y ser seguido por tres numeros enteros");
        }
        this.tipoEnvase = tipoEnvase;
        this.importado = importado;
        this.fechaVencimiento = fechaVencimiento;
        this.calorias = calorias;
        comprobarPorcentajes();
        aplicarImpuestos();
    }

    public String getTipoEnvase() {
        return tipoEnvase;
    }

    public boolean isImportado() {
        return importado;
    }


    @Override
    public void comprobarPorcentajes() {
        if(ganancia > (precioUnidad * 1.20)){
            corregirPorcentajes();
        }
    }

    @Override
    public void corregirPorcentajes() {
        setGanancia((float) (precioUnidad * 1.20));
    }

    @Override
    public void aplicarImpuestos() {
        if(importado){
            setGanancia((float) (ganancia * 1.12));
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                " tipoEnvase= " + tipoEnvase + '\n' +
                " importado= " + importado +
                '}' + "\n" + "--------------------------------------------------";
    }



}
