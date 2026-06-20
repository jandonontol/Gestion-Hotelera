public class Habitacion {
    private int numero;
    private String tipo;
    private double precio;
    private String estado;

    public Habitacion(int numero, String tipo, double precio, String estado) {
        this.numero = numero;
        this.tipo = tipo;
        this.precio = precio;
        this.estado = estado;
    }

    public int getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void cambiarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public String toArchivo() {
        return numero + ";" + tipo + ";" + precio + ";" + estado;
    }

    public void mostrarInformacion() {
        System.out.println("Habitacion N°: " + numero);
        System.out.println("Tipo: " + tipo);
        System.out.println("Precio: S/ " + precio);
        System.out.println("Estado: " + estado);
        System.out.println("----------------------------");
    }
}
