package domain.tiposProductos;

public enum AplicacionLimpieza {
    COCINA("Cocina"),
    BANIO("Banio"),
    ROPA("Ropa"),
    MULTIUSO("Multiuso");

    private final String descripcion;

    AplicacionLimpieza(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}

