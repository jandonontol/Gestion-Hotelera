public class MovimientoCaja {
    private String tipo;
    private double monto;
    private String descripcion;

    public MovimientoCaja(String tipo, double monto, String descripcion) {
        this.tipo = tipo;
        this.monto = monto;
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public double getMonto() {
        return monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String toArchivo() {
        return tipo + ";" + monto + ";" + descripcion;
    }

    public void mostrarMovimiento() {
        System.out.println("Tipo: " + tipo);
        System.out.println("Monto: S/ " + monto);
        System.out.println("Descripcion: " + descripcion);
        System.out.println("----------------------------");
    }
}
