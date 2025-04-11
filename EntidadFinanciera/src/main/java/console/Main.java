package console;

import dto.CajaDeAhorro;
import dto.CajaDeAhorroBuilder;
import dto.CuentaCorriente;
import dto.CuentaCorrienteBuilder;
import service.LogicaCuenta;
import java.util.Random;

public class Main {

    public static void main(String[] args){
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            CajaDeAhorroBuilder cajaDeAhorroBuilder = new CajaDeAhorroBuilder();
            cajaDeAhorroBuilder.setId(i);
            CajaDeAhorro cajaDeAhorro = cajaDeAhorroBuilder.getCajaDeAhorro();
            boolean res1 = LogicaCuenta.obtenerInstancia().agregarCuenta(cajaDeAhorro);

            i++;

            CuentaCorrienteBuilder cuentaCorrienteBuilder = new CuentaCorrienteBuilder();
            cuentaCorrienteBuilder.setId(i);
            CuentaCorriente cuentaCorriente = cuentaCorrienteBuilder.getCuentaCorriente();
            boolean res2 = LogicaCuenta.obtenerInstancia().agregarCuenta(cuentaCorriente);
        }

        for (int i = 0; i < 5000; i++) {
            var res = LogicaCuenta.obtenerInstancia().agregarSaldo(random.nextInt(20), 200);
            var res1 = LogicaCuenta.obtenerInstancia().quitarSaldo(random.nextInt(20), 100);
        }

        int total = 0;

        for (int i = 0; i < 20; i++) {
            System.out.println("Saldo cuenta " + (i + 1 ) + " " + LogicaCuenta.obtenerInstancia().consultarSaldo(i));
            System.out.println("Movimientos cuenta " + (i + 1 ) + " " + LogicaCuenta.obtenerInstancia().getTransacciones(i));
            total += LogicaCuenta.obtenerInstancia().getTransacciones(i);
        }

        System.out.println("\n" + total);

    }
}