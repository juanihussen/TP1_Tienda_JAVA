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
        Tienda JuaniStore = new Tienda("JuaniStore",100,1000);
        JuaniStore.menu();
    }
}