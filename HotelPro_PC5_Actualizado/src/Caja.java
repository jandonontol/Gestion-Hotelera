import java.util.ArrayList;

public class Caja {
    private ArrayList<MovimientoCaja> movimientos;

    public Caja() {
        movimientos = new ArrayList<MovimientoCaja>();
    }

    public void registrarIngreso(double monto, String descripcion) {
        movimientos.add(new MovimientoCaja("Ingreso", monto, descripcion));
    }

    public void registrarEgreso(double monto, String descripcion) {
        movimientos.add(new MovimientoCaja("Egreso", monto, descripcion));
    }

    public double calcularIngresos() {
        double total = 0;
        for (MovimientoCaja movimiento : movimientos) {
            if (movimiento.getTipo().equalsIgnoreCase("Ingreso")) {
                total += movimiento.getMonto();
            }
        }
        return total;
    }

    public double calcularEgresos() {
        double total = 0;
        for (MovimientoCaja movimiento : movimientos) {
            if (movimiento.getTipo().equalsIgnoreCase("Egreso")) {
                total += movimiento.getMonto();
            }
        }
        return total;
    }

    public double calcularSaldo() {
        return calcularIngresos() - calcularEgresos();
    }

    public void listarMovimientos() {
        System.out.println("MOVIMIENTOS DE CAJA");
        if (movimientos.isEmpty()) {
            System.out.println("No existen movimientos registrados.");
        }
        for (MovimientoCaja movimiento : movimientos) {
            movimiento.mostrarMovimiento();
        }
    }

    public void mostrarResumenCaja() {
        System.out.println("Total ingresos: S/ " + calcularIngresos());
        System.out.println("Total egresos: S/ " + calcularEgresos());
        System.out.println("Saldo actual: S/ " + calcularSaldo());
        System.out.println("----------------------------");
    }
}
