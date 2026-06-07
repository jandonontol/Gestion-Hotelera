import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Hotel hotel = new Hotel("HotelPro");
        int opcion = 0;

        hotel.registrarHabitacion(new Habitacion(101, "Simple", 50.0, "Disponible"));
        hotel.registrarHabitacion(new Habitacion(102, "Doble", 80.0, "Disponible"));
        hotel.registrarHabitacion(new Habitacion(103, "Matrimonial", 100.0, "Disponible"));

        do {
            try {
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
                System.out.println("0. Salir");
                System.out.print("Seleccione una opcion: ");
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.print("DNI: ");
                        String dni = sc.nextLine();

                        if (hotel.buscarCliente(dni) != null) {
                            System.out.println("El cliente ya existe.");
                            break;
                        }

                        System.out.print("Nombres: ");
                        String nombres = sc.nextLine();

                        System.out.print("Apellidos: ");
                        String apellidos = sc.nextLine();

                        System.out.print("Telefono: ");
                        String telefono = sc.nextLine();

                        Cliente cliente = new Cliente(dni, nombres, apellidos, telefono);
                        hotel.registrarCliente(cliente);
                        System.out.println("Cliente registrado correctamente.");
                        break;

                    case 2:
                        hotel.listarClientes();
                        break;

                    case 3:
                        hotel.listarHabitaciones();
                        break;

                    case 4:
                        System.out.print("Ingrese numero de habitacion: ");
                        int numero = Integer.parseInt(sc.nextLine());
                        Habitacion h = hotel.buscarHabitacion(numero);

                        if (h != null) {
                            h.mostrarInformacion();
                        } else {
                            System.out.println("Habitacion no encontrada.");
                        }
                        break;

                    case 5:
                        System.out.print("Ingrese tipo de habitacion: ");
                        String tipo = sc.nextLine();
                        hotel.buscarHabitacion(tipo);
                        break;

                    case 6:
                        System.out.print("DNI del cliente: ");
                        String dniReserva = sc.nextLine();
                        Cliente clienteReserva = hotel.buscarCliente(dniReserva);

                        if (clienteReserva == null) {
                            System.out.println("Cliente no encontrado.");
                            break;
                        }

                        System.out.print("Numero de habitacion: ");
                        int numHab = Integer.parseInt(sc.nextLine());
                        Habitacion habitacionReserva = hotel.buscarHabitacion(numHab);

                        if (habitacionReserva == null) {
                            System.out.println("Habitacion no encontrada.");
                            break;
                        }

                        if (!habitacionReserva.getEstado().equalsIgnoreCase("Disponible")) {
                            System.out.println("La habitacion no esta disponible.");
                            break;
                        }

                        System.out.print("Fecha de ingreso: ");
                        String ingreso = sc.nextLine();

                        System.out.print("Fecha de salida: ");
                        String salida = sc.nextLine();

                        Reserva reserva = new Reserva(clienteReserva, habitacionReserva, ingreso, salida);
                        hotel.registrarReserva(reserva);
                        System.out.println("Reserva registrada correctamente.");
                        break;

                    case 7:
                        hotel.listarReservas();
                        break;

                    case 8:
                        System.out.print("Numero de habitacion: ");
                        int habCheckIn = Integer.parseInt(sc.nextLine());
                        hotel.realizarCheckIn(habCheckIn);
                        break;

                    case 9:
                        System.out.print("Numero de habitacion: ");
                        int habCheckOut = Integer.parseInt(sc.nextLine());

                        System.out.print("Cantidad de dias: ");
                        int dias = Integer.parseInt(sc.nextLine());

                        hotel.realizarCheckOut(habCheckOut, dias);
                        break;

                    case 10:
                        System.out.print("Monto del egreso: ");
                        double egreso = Double.parseDouble(sc.nextLine());
                        hotel.getCaja().registrarEgreso(egreso);
                        System.out.println("Egreso registrado correctamente.");
                        break;

                    case 11:
                        hotel.getCaja().mostrarResumenCaja();
                        break;

                    case 0:
                        System.out.println("Saliendo del sistema...");
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
}
