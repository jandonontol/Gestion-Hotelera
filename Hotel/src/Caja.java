import java.util.ArrayList;

public class Caja {
    private ArrayList<Double> ingresos;
    private ArrayList<Double> egresos;

    public Caja() {
        ingresos = new ArrayList<Double>();
        egresos = new ArrayList<Double>();
    }

    public void registrarIngreso(double monto) {
        ingresos.add(monto);
    }

    public void registrarEgreso(double monto) {
        egresos.add(monto);
    }

    public double calcularIngresos() {
        double total = 0;
        for (double ingreso : ingresos) {
            total += ingreso;
        }
        return total;
    }

    public double calcularEgresos() {
        double total = 0;
        for (double egreso : egresos) {
            total += egreso;
        }
        return total;
    }

    public double calcularSaldo() {
        return calcularIngresos() - calcularEgresos();
    }

    public void mostrarResumenCaja() {
        System.out.println("Total ingresos: S/ " + calcularIngresos());
        System.out.println("Total egresos: S/ " + calcularEgresos());
        System.out.println("Saldo actual: S/ " + calcularSaldo());
        System.out.println("----------------------------");
    }
}
