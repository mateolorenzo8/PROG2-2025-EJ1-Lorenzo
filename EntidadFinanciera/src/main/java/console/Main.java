package console;

import dto.CajaDeAhorro;
import dto.CajaDeAhorroBuilder;
import dto.CuentaCorriente;
import dto.CuentaCorrienteBuilder;
import service.LogicaCuenta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args){
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            CajaDeAhorroBuilder cajaDeAhorroBuilder = new CajaDeAhorroBuilder();
            cajaDeAhorroBuilder.setId(i);
            CajaDeAhorro cajaDeAhorro = cajaDeAhorroBuilder.getCajaDeAhorro();
            LogicaCuenta.obtenerInstancia().agregarCuenta(cajaDeAhorro);

            i++;

            CuentaCorrienteBuilder cuentaCorrienteBuilder = new CuentaCorrienteBuilder();
            cuentaCorrienteBuilder.setId(i);
            CuentaCorriente cuentaCorriente = cuentaCorrienteBuilder.getCuentaCorriente();
            LogicaCuenta.obtenerInstancia().agregarCuenta(cuentaCorriente);
        }

        List<CompletableFuture<Void>> operaciones = new ArrayList<>();

        for (int i = 0; i < 5000; i++) {
            int cuenta1 = random.nextInt(20);
            int cuenta2 = random.nextInt(20);
            int cantidad1 = random.nextInt(200);
            int cantidad2 = random.nextInt(100);

            operaciones.add(CompletableFuture.runAsync(() -> {
                LogicaCuenta.obtenerInstancia().agregarSaldo(cuenta1, cantidad1);
                LogicaCuenta.obtenerInstancia().quitarSaldo(cuenta2, cantidad2);
            }));
        }

        CompletableFuture.allOf(operaciones.toArray(new CompletableFuture[0])).join();

        int total = 0;

        for (int i = 0; i < 20; i++) {
            System.out.println("Saldo cuenta " + (i + 1 ) + " " + LogicaCuenta.obtenerInstancia().consultarSaldo(i));
            System.out.println("Movimientos cuenta " + (i + 1 ) + " " + LogicaCuenta.obtenerInstancia().getTransacciones(i));
            total += LogicaCuenta.obtenerInstancia().getTransacciones(i);
        }

        System.out.println("\n" + "Transacciones correctas: " + total);
    }
}