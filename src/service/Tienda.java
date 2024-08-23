package service;
import domain.Producto;
import domain.tiposProductos.AplicacionLimpieza;
import domain.tiposProductos.Bebidas;
import domain.tiposProductos.ProductosEnvasados;
import domain.tiposProductos.ProductosLimpieza;
import exceptions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Tienda {

    private String nombre;
    private int stockMaximo;
    private float saldoCaja;
    private ArrayList<Producto> stock;

    public Tienda(String nombre, int stockMaximo, float saldoCaja) {
        this.nombre = nombre;
        this.stockMaximo = stockMaximo;
        this.saldoCaja = saldoCaja;
        this.stock = new ArrayList<>();
    }

    public ArrayList<Producto> getStock() {
        return stock;
    }

    public void menu() {
        Producto producto1 = new ProductosLimpieza("AZ123","Mr.Musculo","Deja que el mr haga lo suyo",10,2F,10, AplicacionLimpieza.BANIO,10);
        Producto producto2 = new Bebidas("AC123","Coca-Cola","Que rico!",10,2F,10,23.4F,true, LocalDate.of(2025,8,1),300,8);
        Producto producto3 = new ProductosEnvasados("AB123","Mermelada","Sabor Duraznito!",10,2F,10,"Botella",true,LocalDate.of(2025,2,5),200,10);
        Producto galletitas = new ProductosEnvasados("AB123", "oreos", "Galletitas de chocolate y crema", 1, 2F, 10,"Plastico",  true,LocalDate.of(2024,9,3),200,8);
        ArrayList<Producto> ordenDeCompra = new ArrayList<>();

        int opcion;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println(
                    "*************************\n" +
                            "*   Menú de Opciones    *\n" +
                            "*************************\n" +
                            "* 1. Compra de productos sin espacio de stock.  *\n" +
                            "* 2. Compra de productos sin saldo en la caja suficiente.  *\n" +
                            "* 3. Compra de producto satifactoria.  *\n" +
                            "* 4. Venta con unidades mayor a las disponibles.  *\n" +
                            "* 5. Venta de mas de 3 productos.   *\n" +
                            "* 6. Venta de producto no disponible para la venta.  *\n" +
                            "* 7. Venta de productos satifactoria.  *\n" +
                            "* 8. Obtener lista de productos con descuento menor a 15.  *\n" +
                            "* 9. Mostrar Stock    *\n" +
                            "* 10.     Salir       *\n" +
                            "*************************\n" +
                            "  Seleccione una opción:  "
            );
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    Producto productoError = new ProductosLimpieza("AZ123","Mr.Musculo","Deja que el mr haga lo suyo",1000,2F,10, AplicacionLimpieza.BANIO,10);
                    compraProductos(productoError);
                    break;
                case 2:
                    Producto productoMuyCaro = new ProductosLimpieza("AZ123","Mr.Musculo","Deja que el mr haga lo suyo",10,2000,10, AplicacionLimpieza.BANIO,10);
                    compraProductos(productoMuyCaro);
                    break;
                case 3:
                    compraProductos(producto1);
                    compraProductos(producto2);
                    compraProductos(producto3);
                    compraProductos(galletitas);
                    System.out.println("La compra se realizo con exito !!!");
                    break;
                case 4:
                    Producto productoCantMas12 = new ProductosLimpieza("AZ123","Mr.Musculo","Deja que el mr haga lo suyo",13,2F,10, AplicacionLimpieza.BANIO,10);
                    ordenDeCompra.add(productoCantMas12);
                    venta(ordenDeCompra);
                    ordenDeCompra.clear();
                    break;
                case 5:
                    ordenDeCompra.add(producto1);
                    ordenDeCompra.add(producto2);
                    ordenDeCompra.add(producto3);
                    ordenDeCompra.add(galletitas);
                    venta(ordenDeCompra);
                    ordenDeCompra.clear();
                    break;
                case 6:
                    Producto galletitasExcedidas = new ProductosEnvasados("AB123", "oreos", "Galletitas de chocolate y crema", 5, 2F, 10,"Plastico",  true,LocalDate.of(2024,9,3),200,8);
                    galletitas.setDisponible(false);
                    ordenDeCompra.add(galletitasExcedidas);
                    venta(ordenDeCompra);
                    ordenDeCompra.clear();
                    break;
                case 7:
                    compraProductos(producto1);
                    compraProductos(producto3);
                    ordenDeCompra.add(producto1);
                    ordenDeCompra.add(producto3);
                    venta(ordenDeCompra);
                    break;
                case 8:
                    System.out.println(obtenerProductosConMenorDescuento(15));
                    break;
                case 9:
                    mostrarProductos(stock);
                    break;
                case 10:
                    System.out.println("Salir.");
                    break;
                default:
                    System.out.println("Opcion no valida.");
                    break;
            }
        } while (opcion != 10);
    }

    public void compraProductos(Producto producto) {
        verificacionStockMaximo(producto.getCantStock());
        float precioTotalCompra = producto.getPrecioUnidad() * producto.getCantStock();
        verificacionSaldoInsuficienteException(precioTotalCompra);
        compraUnProducto(producto);
    }

    public void verificacionStockMaximo(int cantStock) {
        if ((stockMaximo == 0) || (stockMaximo < cantStock)) {
            throw new StockMaximoException(stockMaximo);
        }
    }

    public void verificacionSaldoInsuficienteException(float precioTotal){
        if (saldoCaja < precioTotal){
            throw new SaldoInsuficienteException(saldoCaja, precioTotal);
        }
    }

    public void compraUnProducto(Producto producto) {
        boolean flag = false;
        for(Producto p : stock){
            if(p.getNombre().equals(producto.getNombre())){
                p.setCantStock(p.getCantStock() + producto.getCantStock());
                flag = true;
            }
        }
        if(!flag){
            producto.setDisponible(true);
            saldoCaja -= (producto.getPrecioUnidad() * producto.getCantStock());
            stockMaximo -= producto.getCantStock();
            producto.setPrecioUnidad(((producto.getPrecioUnidad() * producto.getGanancia()) / 100) + producto.getPrecioUnidad());
            stock.add(producto);
        }
    }

    public void venta(ArrayList<Producto> productosAVender) {
        ArrayList<Producto> vendidos = new ArrayList<>();
        StringBuilder mensaje = new StringBuilder();

        verificacionMasDeTresProductos(productosAVender.size());

        for (Producto producto : productosAVender) {

            verificacionCantidadDeStockNoPermitido(producto.getCantStock());

            for (Producto p : stock) {
                if (p.getNombre().equals(producto.getNombre())) {
                    verificacionDisponibilidadProducto(p);
                    verificacionCasoUnidadesSolicitadas(producto, p, vendidos, mensaje);
                    break;
                }
            }
        }
        mostrarImpresionDeVenta(vendidos);
        if (mensaje.length() > 0) {
            System.out.println(mensaje.toString());
        }
    }

    public void verificacionMasDeTresProductos(int cantProductosAVender) {
        if(cantProductosAVender > 3){
            throw new MasDeTresProductosException(cantProductosAVender);
        }
    }

    public void verificacionCantidadDeStockNoPermitido(int cantidadPedida) {
        if(cantidadPedida > 12){
            throw new CantidadDeStockNoPermitidoException(cantidadPedida);
        }
    }

    public void verificacionDisponibilidadProducto(Producto producto) {
        if (!producto.getDisponible() || producto.getCantStock() == 0){
            throw new ProductoNoDisponibleException(producto.getId(),producto.getNombre());
        }
    }

    public void verificacionCasoUnidadesSolicitadas(Producto producto, Producto p, ArrayList<Producto> vendidos, StringBuilder mensaje) {
            verificacionDisponibilidadProducto(p);
            if (producto.getCantStock() <= p.getCantStock()) {
                vendidos.add(producto);
                saldoCaja += (p.getPrecioUnidad() * producto.getCantStock());
                //p.setCantStock(p.getCantStock() - producto.getCantStock());
                darDeBajaProductoNoDisponible(p);
            } else {
                producto.setCantStock(p.getCantStock());
                vendidos.add(producto);
                saldoCaja += (p.getCantStock() * p.getPrecioUnidad());
                mensaje.append("Hay productos con stock disponible menor al solicitado.\n");
                darDeBajaProductoNoDisponible(p);
            }
    }

    public void darDeBajaProductoNoDisponible(Producto productoEnStock) {
        if (productoEnStock.getCantStock() <= 0) {
            this.stockMaximo += productoEnStock.getCantStock();
            stock.remove(productoEnStock);
            productoEnStock.setCantStock(0);
            productoEnStock.setDisponible(false);
        }
    }

    public void mostrarProductos(ArrayList<Producto> stock) {
        System.out.println("**************************************************************\n Saldo de caja : " + this.saldoCaja + "   ||   " + " Espacio disponible : " + "(" + stockMaximo + ")  *\n" + "**************************************************************");
        if(stock.size() == 0){
            System.out.println("El stock esta vacio, ve y repone mercaderia");
        }else{
            for (Producto producto : stock) {
                System.out.println(producto);
            }
        }
    }

    public void mostrarImpresionDeVenta(ArrayList<Producto> productosVendidos) {
        double total = 0.0;
        for (Producto producto : productosVendidos) {
            double subtotal = (producto.getCantStock() * producto.getPrecioUnidad());
            System.out.println(producto.getId() + " " + producto.descripcion + " " + producto.getCantStock() + " x " + producto.getPrecioUnidad());
            total += subtotal;
        }
        System.out.println("TOTAL VENTA: " + total);
    }

    public String obtenerProductosConMenorDescuento(double porcentajeDescuento) {
        return stock.stream()
                .filter(p -> p instanceof ProductosEnvasados || p instanceof Bebidas)
                .filter(p -> p.getDescuento() < porcentajeDescuento)
                .map(Producto::getNombre)
                .map(String::toUpperCase)
                .collect(Collectors.joining(","));
    }
}




