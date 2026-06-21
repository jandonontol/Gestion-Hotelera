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

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void registrarHabitacion(Habitacion habitacion) {
        habitaciones.add(habitacion);
    }

    public boolean registrarCliente(Cliente cliente) {
        if (buscarCliente(cliente.getDni()) != null) {
            System.out.println("El cliente ya existe.");
            return false;
        }

        clientes.add(cliente);
        System.out.println("Cliente registrado correctamente.");
        return true;
    }

    public void modificarCliente(String dni, String nombres, String apellidos, String telefono) {
        Cliente cliente = buscarCliente(dni);

        if (cliente != null) {
            cliente.actualizarDatos(nombres, apellidos, telefono);
            System.out.println("Cliente modificado correctamente.");
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    public void eliminarCliente(String dni) {
        Cliente cliente = buscarCliente(dni);

        if (cliente != null) {
            clientes.remove(cliente);
            System.out.println("Cliente eliminado correctamente.");
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    public void registrarReserva(Reserva reserva) {
        reservas.add(reserva);
        System.out.println("Reserva registrada correctamente.");
    }

    public void cancelarReserva(int numeroHabitacion) {
        for (Reserva reserva : reservas) {
            if (reserva.getHabitacion().getNumero() == numeroHabitacion && reserva.getEstado().equalsIgnoreCase("Activa")) {
                reserva.cancelarReserva();
                System.out.println("Reserva cancelada correctamente.");
                return;
            }
        }

        System.out.println("No se encontro una reserva activa para esa habitacion.");
    }

    public Caja getCaja() {
        return caja;
    }

    public void listarHabitaciones() {
        System.out.println("LISTA GENERAL DE HABITACIONES");
        for (Habitacion h : habitaciones) {
            h.mostrarInformacion();
        }
    }

    public void listarClientes() {
        System.out.println("LISTA GENERAL DE CLIENTES");
        if (clientes.isEmpty()) {
            System.out.println("No existen clientes registrados.");
        }
        for (Cliente c : clientes) {
            c.mostrarInformacion();
        }
    }

    public void listarReservas() {
        System.out.println("LISTA GENERAL DE RESERVAS");
        if (reservas.isEmpty()) {
            System.out.println("No existen reservas registradas.");
        }
        for (Reserva r : reservas) {
            r.mostrarReserva();
        }
    }

    public void mostrarHabitacionesPorEstado(String estado) {
        System.out.println("HABITACIONES EN ESTADO: " + estado);
        for (Habitacion h : habitaciones) {
            if (h.getEstado().equalsIgnoreCase(estado)) {
                h.mostrarInformacion();
            }
        }
    }

    public void cambiarEstadoHabitacion(int numero, String nuevoEstado) {
        Habitacion habitacion = buscarHabitacion(numero);

        if (habitacion != null) {
            habitacion.cambiarEstado(nuevoEstado);
            System.out.println("Estado actualizado correctamente.");
        } else {
            System.out.println("Habitacion no encontrada.");
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

    public void buscarClientePorNombre(String nombre) {
        System.out.println("CLIENTES CON NOMBRE: " + nombre);
        for (Cliente c : clientes) {
            if (c.getNombres().toLowerCase().contains(nombre.toLowerCase())) {
                c.mostrarInformacion();
            }
        }
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
            caja.registrarIngreso(total, "Hospedaje habitacion " + numeroHabitacion);
            h.cambiarEstado("Disponible");
            System.out.println("Check-out realizado correctamente.");
            System.out.println("Total a pagar: S/ " + total);
        } else {
            System.out.println("No se puede realizar check-out. La habitacion debe estar ocupada.");
        }
    }
}
