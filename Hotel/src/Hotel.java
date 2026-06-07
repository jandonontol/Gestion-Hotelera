import java.util.ArrayList;

public class Hotel {
    private String nombre;
    private ArrayList<Habitacion> habitaciones;
    private ArrayList<Cliente> clientes;
    private ArrayList<Reserva> reservas;
    private Caja caja;

    public Hotel(String nombre) {
        this.nombre = nombre;
        habitaciones = new ArrayList<Habitacion>();
        clientes = new ArrayList<Cliente>();
        reservas = new ArrayList<Reserva>();
        caja = new Caja();
    }

    public void registrarHabitacion(Habitacion habitacion) {
        habitaciones.add(habitacion);
    }

    public void registrarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void registrarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public Caja getCaja() {
        return caja;
    }

    public void listarHabitaciones() {
        System.out.println("LISTA DE HABITACIONES");
        for (Habitacion h : habitaciones) {
            h.mostrarInformacion();
        }
    }

    public void listarClientes() {
        System.out.println("LISTA DE CLIENTES");
        for (Cliente c : clientes) {
            c.mostrarInformacion();
        }
    }

    public void listarReservas() {
        System.out.println("LISTA DE RESERVAS");
        for (Reserva r : reservas) {
            r.mostrarReserva();
        }
    }

    // Sobrecarga de metodos: busqueda por numero.
    public Habitacion buscarHabitacion(int numero) {
        for (Habitacion h : habitaciones) {
            if (h.getNumero() == numero) {
                return h;
            }
        }
        return null;
    }

    // Sobrecarga de metodos: busqueda por tipo.
    public void buscarHabitacion(String tipo) {
        System.out.println("Habitaciones de tipo: " + tipo);
        for (Habitacion h : habitaciones) {
            if (h.getTipo().equalsIgnoreCase(tipo)) {
                h.mostrarInformacion();
            }
        }
    }

    public Cliente buscarCliente(String dni) {
        for (Cliente c : clientes) {
            if (c.getDni().equals(dni)) {
                return c;
            }
        }
        return null;
    }

    public void realizarCheckIn(int numeroHabitacion) {
        Habitacion h = buscarHabitacion(numeroHabitacion);
        if (h != null && h.getEstado().equalsIgnoreCase("Reservada")) {
            h.cambiarEstado("Ocupada");
            System.out.println("Check-in realizado correctamente.");
        } else {
            System.out.println("No se puede realizar check-in. La habitacion debe estar reservada.");
        }
    }

    public void realizarCheckOut(int numeroHabitacion, int dias) {
        Habitacion h = buscarHabitacion(numeroHabitacion);
        if (h != null && h.getEstado().equalsIgnoreCase("Ocupada")) {
            double total = h.getPrecio() * dias;
            caja.registrarIngreso(total);
            h.cambiarEstado("Disponible");
            System.out.println("Check-out realizado correctamente.");
            System.out.println("Total a pagar: S/ " + total);
        } else {
            System.out.println("No se puede realizar check-out. La habitacion debe estar ocupada.");
        }
    }
}
