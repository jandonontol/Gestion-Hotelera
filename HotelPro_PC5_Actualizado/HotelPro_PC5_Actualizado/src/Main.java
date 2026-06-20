import java.util.Scanner;

public class Main {
    private static final String RUTA_CLIENTES = "data/clientes.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Hotel hotel = new Hotel("HotelPro");
        int opcion = -1;

        cargarDatosIniciales(hotel);

        do {
            try {
                mostrarMenu();
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1:
                        registrarCliente(sc, hotel);
                        break;

                    case 2:
                        hotel.listarClientes();
                        break;

                    case 3:
                        hotel.listarHabitaciones();
                        break;

                    case 4:
                        buscarHabitacionPorNumero(sc, hotel);
                        break;

                    case 5:
                        buscarHabitacionPorTipo(sc, hotel);
                        break;

                    case 6:
                        registrarReserva(sc, hotel);
                        break;

                    case 7:
                        hotel.listarReservas();
                        break;

                    case 8:
                        realizarCheckIn(sc, hotel);
                        break;

                    case 9:
                        realizarCheckOut(sc, hotel);
                        break;

                    case 10:
                        registrarEgreso(sc, hotel);
                        break;

                    case 11:
                        hotel.getCaja().mostrarResumenCaja();
                        break;

                    case 12:
                        hotel.mostrarHabitacionesPorEstado("Disponible");
                        break;

                    case 13:
                        hotel.mostrarHabitacionesPorEstado("Ocupada");
                        break;

                    case 14:
                        modificarCliente(sc, hotel);
                        break;

                    case 15:
                        eliminarCliente(sc, hotel);
                        break;

                    case 16:
                        hotel.getCaja().listarMovimientos();
                        break;

                    case 17:
                        ArchivoUtil.guardarClientes(hotel.getClientes(), RUTA_CLIENTES);
                        break;

                    case 18:
                        hotel.getClientes().clear();
                        hotel.getClientes().addAll(ArchivoUtil.leerClientes(RUTA_CLIENTES));
                        break;

                    case 19:
                        cancelarReserva(sc, hotel);
                        break;

                    case 20:
                        cambiarEstadoHabitacion(sc, hotel);
                        break;

                    case 0:
                        System.out.println("Saliendo del sistema HotelPro...");
                        break;

                    default:
                        System.out.println("Opcion no valida.");
                        break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: debe ingresar un numero valido.");
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }

            System.out.println();

        } while (opcion != 0);

        sc.close();
    }

    private static void cargarDatosIniciales(Hotel hotel) {
        hotel.registrarHabitacion(new Habitacion(101, "Simple", 50.0, "Disponible"));
        hotel.registrarHabitacion(new Habitacion(102, "Doble", 80.0, "Disponible"));
        hotel.registrarHabitacion(new Habitacion(103, "Matrimonial", 100.0, "Disponible"));
        hotel.registrarHabitacion(new Habitacion(104, "Triple", 120.0, "Disponible"));
        hotel.registrarHabitacion(new Habitacion(105, "Matrimonial", 100.0, "Disponible"));
    }

    private static void mostrarMenu() {
        System.out.println("===== SISTEMA HOTELPRO =====");
        System.out.println("1. Registrar cliente");
        System.out.println("2. Listar clientes");
        System.out.println("3. Listar habitaciones");
        System.out.println("4. Buscar habitacion por numero");
        System.out.println("5. Buscar habitacion por tipo");
        System.out.println("6. Registrar reserva");
        System.out.println("7. Listar reservas");
        System.out.println("8. Realizar check-in");
        System.out.println("9. Realizar check-out");
        System.out.println("10. Registrar egreso");
        System.out.println("11. Ver caja");
        System.out.println("12. Mostrar habitaciones disponibles");
        System.out.println("13. Mostrar habitaciones ocupadas");
        System.out.println("14. Modificar cliente");
        System.out.println("15. Eliminar cliente");
        System.out.println("16. Consultar movimientos de caja");
        System.out.println("17. Guardar clientes en archivo");
        System.out.println("18. Leer clientes desde archivo");
        System.out.println("19. Cancelar reserva");
        System.out.println("20. Cambiar estado de habitacion");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opcion: ");
    }

    private static void registrarCliente(Scanner sc, Hotel hotel) {
        System.out.print("DNI: ");
        String dni = sc.nextLine();

        System.out.print("Nombres: ");
        String nombres = sc.nextLine();

        System.out.print("Apellidos: ");
        String apellidos = sc.nextLine();

        System.out.print("Telefono: ");
        String telefono = sc.nextLine();

        hotel.registrarCliente(new Cliente(dni, nombres, apellidos, telefono));
    }

    private static void buscarHabitacionPorNumero(Scanner sc, Hotel hotel) {
        System.out.print("Ingrese numero de habitacion: ");
        int numero = Integer.parseInt(sc.nextLine());

        Habitacion h = hotel.buscarHabitacion(numero);

        if (h != null) {
            h.mostrarInformacion();
        } else {
            System.out.println("Habitacion no encontrada.");
        }
    }

    private static void buscarHabitacionPorTipo(Scanner sc, Hotel hotel) {
        System.out.print("Ingrese tipo de habitacion: ");
        String tipo = sc.nextLine();
        hotel.buscarHabitacion(tipo);
    }

    private static void registrarReserva(Scanner sc, Hotel hotel) {
        System.out.print("DNI del cliente: ");
        String dniReserva = sc.nextLine();

        Cliente clienteReserva = hotel.buscarCliente(dniReserva);

        if (clienteReserva == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        System.out.print("Numero de habitacion: ");
        int numHab = Integer.parseInt(sc.nextLine());

        Habitacion habitacionReserva = hotel.buscarHabitacion(numHab);

        if (habitacionReserva == null) {
            System.out.println("Habitacion no encontrada.");
            return;
        }

        if (!habitacionReserva.getEstado().equalsIgnoreCase("Disponible")) {
            System.out.println("La habitacion no esta disponible.");
            return;
        }

        System.out.print("Fecha de ingreso: ");
        String ingreso = sc.nextLine();

        System.out.print("Fecha de salida: ");
        String salida = sc.nextLine();

        Reserva reserva = new Reserva(clienteReserva, habitacionReserva, ingreso, salida);
        hotel.registrarReserva(reserva);
    }

    private static void realizarCheckIn(Scanner sc, Hotel hotel) {
        System.out.print("Numero de habitacion: ");
        int habCheckIn = Integer.parseInt(sc.nextLine());
        hotel.realizarCheckIn(habCheckIn);
    }

    private static void realizarCheckOut(Scanner sc, Hotel hotel) {
        System.out.print("Numero de habitacion: ");
        int habCheckOut = Integer.parseInt(sc.nextLine());

        System.out.print("Cantidad de dias: ");
        int dias = Integer.parseInt(sc.nextLine());

        hotel.realizarCheckOut(habCheckOut, dias);
    }

    private static void registrarEgreso(Scanner sc, Hotel hotel) {
        System.out.print("Monto del egreso: ");
        double egreso = Double.parseDouble(sc.nextLine());

        System.out.print("Descripcion: ");
        String descripcion = sc.nextLine();

        hotel.getCaja().registrarEgreso(egreso, descripcion);
        System.out.println("Egreso registrado correctamente.");
    }

    private static void modificarCliente(Scanner sc, Hotel hotel) {
        System.out.print("DNI del cliente a modificar: ");
        String dni = sc.nextLine();

        System.out.print("Nuevos nombres: ");
        String nombres = sc.nextLine();

        System.out.print("Nuevos apellidos: ");
        String apellidos = sc.nextLine();

        System.out.print("Nuevo telefono: ");
        String telefono = sc.nextLine();

        hotel.modificarCliente(dni, nombres, apellidos, telefono);
    }

    private static void eliminarCliente(Scanner sc, Hotel hotel) {
        System.out.print("DNI del cliente a eliminar: ");
        String dni = sc.nextLine();
        hotel.eliminarCliente(dni);
    }

    private static void cancelarReserva(Scanner sc, Hotel hotel) {
        System.out.print("Numero de habitacion de la reserva: ");
        int numero = Integer.parseInt(sc.nextLine());
        hotel.cancelarReserva(numero);
    }

    private static void cambiarEstadoHabitacion(Scanner sc, Hotel hotel) {
        System.out.print("Numero de habitacion: ");
        int numero = Integer.parseInt(sc.nextLine());

        System.out.print("Nuevo estado: ");
        String estado = sc.nextLine();

        hotel.cambiarEstadoHabitacion(numero, estado);
    }
}
