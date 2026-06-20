public class Reserva {
    private Cliente cliente;
    private Habitacion habitacion;
    private String fechaIngreso;
    private String fechaSalida;
    private String estado;

    public Reserva(Cliente cliente, Habitacion habitacion, String fechaIngreso, String fechaSalida) {
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
        this.estado = "Activa";
        habitacion.cambiarEstado("Reservada");
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public String getEstado() {
        return estado;
    }

    public void cancelarReserva() {
        this.estado = "Cancelada";
        habitacion.cambiarEstado("Disponible");
    }

    public String toArchivo() {
        return cliente.getDni() + ";" + habitacion.getNumero() + ";" + fechaIngreso + ";" + fechaSalida + ";" + estado;
    }

    public void mostrarReserva() {
        System.out.println("Cliente: " + cliente.getNombres() + " " + cliente.getApellidos());
        System.out.println("Habitacion: " + habitacion.getNumero());
        System.out.println("Ingreso: " + fechaIngreso);
        System.out.println("Salida: " + fechaSalida);
        System.out.println("Estado: " + estado);
        System.out.println("----------------------------");
    }
}
