import domain.Producto;
import domain.tiposProductos.AplicacionLimpieza;
import domain.tiposProductos.Bebidas;
import domain.tiposProductos.ProductosEnvasados;
import domain.tiposProductos.ProductosLimpieza;
import exceptions.*;
import service.Tienda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Tienda JuaniStore = new Tienda("JuaniStore",10,20);
        Producto producto1 = new ProductosLimpieza("AZ123","Mr.Musculo","Deja que el mr haga lo suyo",1,2F,2F, AplicacionLimpieza.COCINA);
        Producto producto2 = new Bebidas("AC123","Coca-Cola","Que rico!",1,1F,2F,23.4F,true,LocalDate.of(2025,8,1),200);
        Producto producto3 = new ProductosEnvasados("AB123","Mermelada","Sabor Duraznito!",1,1F,2F,"Botella",true,LocalDate.of(2025,2,5),100);
        JuaniStore.getStock().add(producto1);
        JuaniStore.getStock().add(producto2);
        JuaniStore.getStock().add(producto3);
        //JuaniStore.menu();
        JuaniStore.ventaProductoMenu();
    }
}