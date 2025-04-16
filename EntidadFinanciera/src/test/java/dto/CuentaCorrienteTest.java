package dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CuentaCorrienteTest {

    private CuentaCorriente admin = new CuentaCorriente(1, 0);

    @Test
    void getGiroCubierto() {
        Assertions.assertEquals(0, admin.getGiroCubierto());
    }

    @Test
    void agregarSaldo() {
        boolean res = admin.agregarSaldo(1000);

        Assertions.assertTrue(res);
        Assertions.assertEquals(1000, admin.getSaldo());
    }

    @Test
    void quitarSaldoCorrectamente() {
        admin.agregarSaldo(1000);
        admin.quitarSaldo(1000);

        Assertions.assertEquals(0, admin.getSaldo());
    }

    @Test
    void quitarSaldoIncorrectamente() {
        admin.agregarSaldo(1000);
        admin.quitarSaldo(3000);

        Assertions.assertEquals(1000, admin.getSaldo());
    }

    @Test
    void quitarSaldoUsandoGiroCubierto() {
        admin.agregarSaldo(2000);
        admin.quitarSaldo(4000);

        Assertions.assertEquals(-2000, admin.getSaldo());
    }

    @Test
    void getOperaciones() {
        admin.agregarSaldo(2000);
        admin.quitarSaldo(4000);
        admin.agregarSaldo(4000);
        admin.agregarSaldo(4000);

        Assertions.assertEquals(4, admin.getOperaciones());
    }
}