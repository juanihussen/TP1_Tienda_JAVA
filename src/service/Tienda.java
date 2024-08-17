package service;
import domain.Producto;
import domain.tiposProductos.ProductosEnvasados;
import exceptions.SaldoInsuficienteException;
import exceptions.StockMaximoException;

import java.util.ArrayList;
import java.util.Scanner;

public class Tienda {

    private String nombre;
    private int stockMaximo;
    private float saldoCaja;
    private ArrayList<Producto> stock;

    public Tienda(String nombre, int stockMaximo,float saldoCaja) {
        this.nombre = nombre;
        this.stockMaximo = stockMaximo;
        this.saldoCaja = saldoCaja;
        this.stock = new ArrayList<>();
    }

    public void menu () {
        int opcion;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println(
                            "*************************\n" +
                            "*   Menú de Opciones    *\n" +
                            "*************************\n" +
                            "* 1. Compra de Mercadería *\n" +
                            "* 2. Venta de Mercadería  *\n" +
                            "* 3.       Salir          *\n" +
                            "*************************\n" +
                            "  Seleccione una opción:  "
            );
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("Compra de productos para mercaderia");
                    break;
                case 2:
                    System.out.println("Venta de productos");
                    break;
                case 3:
                    System.out.println("Salir.");
                    break;
                default:
                    System.out.println("Opcion no valida.");
                    break;
            }
        } while (opcion != 3);
    }

    public void compraProductos (Producto producto){
        if (stockMaximo == 0) {
            throw new StockMaximoException(stockMaximo);
        }
        float precioTotalCompra = producto.getPrecioUnidad() * producto.getCantStock();

        if (saldoCaja >= precioTotalCompra) {
            producto.setDisponible(true);
            stock.add(producto);
            saldoCaja -= (producto.getPrecioUnidad() * producto.getCantStock());
            stockMaximo -= producto.getCantStock();
        } else {
            throw new SaldoInsuficienteException(saldoCaja,precioTotalCompra);
        }
    }

    public void mostrarProductos() {
        for (Producto producto : stock) {
            System.out.println(producto);
        }
    }

}
