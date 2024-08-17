import domain.Producto;
import domain.tiposProductos.Bebidas;
import domain.tiposProductos.ProductosEnvasados;
import service.Tienda;

public class Main {
    public static void main(String[] args) {
        Tienda JuaniStore = new Tienda("JuaniStore",100,1000);
        //JuaniStore.menu();
        Producto misterMusculo = new ProductosEnvasados("AB123","Mr.Musculo","Deja que Mr haga lo suyo",20,3.4F,5,"Botella",true);
        Producto coca = new Bebidas("AC123","coca","Diabetes!",10,12.5F,2F,false,1.5F,true);
        JuaniStore.compraProductos(misterMusculo);
        JuaniStore.compraProductos(coca);
        JuaniStore.mostrarProductos();
    }
}