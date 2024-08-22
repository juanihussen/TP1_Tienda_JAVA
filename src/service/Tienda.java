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
        int opcion;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println(
                    "*************************\n" +
                            "*   Menú de Opciones    *\n" +
                            "*************************\n" +
                            "* 1. Testear Compras *\n" +
                            "* 2. Testear Ventas  *\n" +
                            "* 3. Mostrar Stock   *\n" +
                            "* 4.     Salir       *\n" +
                            "*************************\n" +
                            "  Seleccione una opción:  "

            );
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    compraProductosMenu();
                    break;
                case 2:
                    ventaProductoMenu();
                    break;
                case 3:
                    mostrarProductos(stock);
                    break;
                case 4:
                    System.out.println("Salir.");
                    break;
                default:
                    System.out.println("Opcion no valida.");
                    break;
            }
        } while (opcion != 3);
    }

    public void compraProductos(Producto producto) {
        verificacionStockMaximo(producto.getCantStock());
        float precioTotalCompra = producto.getPrecioUnidad() * producto.getCantStock();
        verificacionSaldoInsuficienteException(precioTotalCompra);
        compraUnProducto(producto);
    }

    public void compraProductosMenu() {
        int opcion;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println(
                    "*************************\n" +
                            "*   Menú de Compra    *\n" +
                            "*************************\n" +
                            "* 1. Compra de productos sin espacio de stock *\n" +
                            "* 2. Compra de productos sin saldo en la caja suficiente  *\n" +
                            "* 3. Compra de producto completa      *\n" +
                            "* 4.       Salir          *\n" +
                            "*************************\n" +
                            "  Seleccione una opción:  "
            );

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    Producto misterMusculo = new ProductosLimpieza("AB123", "Mr.Musculo", "Deja que Mr haga lo suyo", 200, 1, 5,AplicacionLimpieza.BANIO);
                    compraProductos(misterMusculo);
                    break;
                case 2:
                    Producto coca = new Bebidas("AC123", "coca", "Diabetes!", 10, 2000F, 2F, 12.5F, true,LocalDate.of(2024,12,2),100);
                    compraProductos(coca);
                    break;
                case 3:
                    Producto galletitas = new ProductosEnvasados("AB123", "oreos", "Galletitas de chocolate y crema", 20, 5.5F, 10,"Plastico",  true,LocalDate.of(2024,9,3),200);
                    compraProductos(galletitas);
                    break;
                default:
                    System.out.println("Opcion no valida.");
                    break;
            }
        } while (opcion != 4);
    }

    public void verificacionStockMaximo(int cantStock) {
        if (!(stockMaximo == 0) || !(stockMaximo < cantStock)) {
            throw new StockMaximoException(stockMaximo);
        }
    }

    public void verificacionSaldoInsuficienteException(float precioTotal){
        if (saldoCaja < precioTotal){
            throw new SaldoInsuficienteException(saldoCaja, precioTotal);
        }
    }

    public void compraUnProducto(Producto producto) {
        producto.setDisponible(true);
        stock.add(producto);
        saldoCaja -= (producto.getPrecioUnidad() * producto.getCantStock());
        stockMaximo -= producto.getCantStock();
    }


    //modularizar con los metodos ya creados
    public void venta(ArrayList<Producto> productosAVender) {
        ArrayList<Producto> vendidos = new ArrayList<>();
        StringBuilder mensaje = new StringBuilder();

        verificacionMasDeTresProductos(productosAVender.size());

        for (Producto producto : productosAVender) {

            verificacionCantidadDeStockNoPermitido(producto.getCantStock());

            Producto productoEnStock = verificacionExistenciaProducto(producto);

            verificacionSeEncontraron(productoEnStock,producto);

            verificacionDisponibilidadProducto(productoEnStock);

            verificacionCasoUnidadesSolicitadas(producto, productoEnStock, vendidos, mensaje);

        }
        mostrarImpresionDeVenta(vendidos);
        if (mensaje.length() > 0) {
            System.out.println(mensaje.toString());
        }
    }

    public void ventaProductoMenu() {
        Producto producto1 = new ProductosLimpieza("AZ123","Mr.Musculo","Deja que el mr haga lo suyo",1,2F,2F, AplicacionLimpieza.COCINA);
        Producto producto2 = new Bebidas("AC123","Coca-Cola","Que rico!",1,1F,2F,23.4F,true, LocalDate.of(2025,8,1),300);
        Producto producto3 = new ProductosEnvasados("AB123","Mermelada","Sabor Duraznito!",1,1F,2F,"Botella",true,LocalDate.of(2025,2,5),200);
        ArrayList<Producto> ordenDeCompra = new ArrayList<>();

        int opcion;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println(
                    "*************************\n" +
                            "*   Menú de Venta    *\n" +
                            "*************************\n" +
                            "* 1. Venta con unidades mayor a las disponibles *\n" +
                            "* 2. Venta de mas de 3 productos  *\n" +
                            "* 3. Venta de producto no disponible para la venta  *\n" +
                            "* 4. Venta de producto y unidades disponibles a la venta     *\n" +
                            "* 5.       Salir          *\n" +
                            "*************************\n" +
                            "  Seleccione una opción:  "
            );
            opcion = scanner.nextInt();
            //crear ejemplos con los diferentes casos PARA TESTEAR JUANI DE MAÑANA.
            switch (opcion) {
                case 1:
                    producto1.setCantStock(13);
                    ordenDeCompra.add(producto1);
                    venta(ordenDeCompra);
                    break;
                case 2:
                    ordenDeCompra.add(producto1);
                    ordenDeCompra.add(producto2);
                    ordenDeCompra.add(producto3);
                    ordenDeCompra.add(producto1);
                    venta(ordenDeCompra);
                    break;
                case 3:
                    ordenDeCompra.add(producto1);
                    ordenDeCompra.add(producto1);
                    venta(ordenDeCompra);
                    break;
                case 4:
                    ordenDeCompra.add(producto1);
                    ordenDeCompra.add(producto3);
                    venta(ordenDeCompra);
                    break;
                default:
                    System.out.println("Opcion no valida.");
                    break;
            }
        } while (opcion != 5);
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

    public Producto verificacionExistenciaProducto(Producto producto) {
        Producto productoEnStock = null;

        for (Producto p : stock) {
            if (p.getNombre().equals(producto.getNombre())) {
                productoEnStock = p;
                break;
            }
        }
        return productoEnStock;
    }

    public void verificacionSeEncontraron(Producto productoEnStock,Producto producto) {
        if (productoEnStock == null) {
            throw new NoSeEncontroElProductoException(producto.getNombre());
        }
    }

    public void verificacionCasoUnidadesSolicitadas(Producto producto, Producto productoEnStock, ArrayList<Producto> vendidos, StringBuilder mensaje) {
            verificacionDisponibilidadProducto(productoEnStock);
            if (producto.getCantStock() <= productoEnStock.getCantStock()) {
                vendidos.add(producto);
                saldoCaja += (productoEnStock.getGanancia() * producto.getCantStock());
                productoEnStock.setCantStock(productoEnStock.getCantStock() - producto.getCantStock());
                darDeBajaProductoNoDisponible(productoEnStock);
            } else {
                producto.setCantStock(productoEnStock.getCantStock());
                vendidos.add(producto);
                saldoCaja += (productoEnStock.getCantStock() * productoEnStock.getGanancia());
                mensaje.append("Hay productos con stock disponible menor al solicitado.\n");
                darDeBajaProductoNoDisponible(productoEnStock);
            }
    }

    public void darDeBajaProductoNoDisponible(Producto productoEnStock) {
        if (productoEnStock.getCantStock() <= 0) {
            productoEnStock.setCantStock(0);
            productoEnStock.setDisponible(false);
        }
    }

    public void mostrarProductos(ArrayList<Producto> stock) {
        for (Producto producto : stock) {
            System.out.println(producto);
        }
    }

    public void mostrarImpresionDeVenta(ArrayList<Producto> productosVendidos) {
        double total = 0.0;
        for (Producto producto : productosVendidos) {
            double subtotal = producto.getCantStock() * producto.getPrecioUnidad();
            System.out.println(producto.getId() + " " + producto.descripcion + " " + producto.getCantStock() + " x " + producto.getPrecioUnidad());
            total += subtotal;
        }
        System.out.println("TOTAL VENTA: " + total);
    }
}




