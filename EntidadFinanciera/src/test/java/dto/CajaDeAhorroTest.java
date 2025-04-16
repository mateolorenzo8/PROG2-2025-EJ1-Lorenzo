package dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CajaDeAhorroTest {

    private CajaDeAhorro admin = new CajaDeAhorro(1);

    @Test
    void agregarSaldo() {

        admin.agregarSaldo(1000);
        admin.agregarSaldo(1000);

        Assertions.assertEquals(2000, admin.getSaldo());
    }

    @Test
    void quitarSaldoCorrecto() {
        admin.agregarSaldo(2000);
        admin.quitarSaldo(1000);

        Assertions.assertEquals(1000, admin.getSaldo());
    }

    @Test
    void quitarSaldoIncorrecto() {
        admin.agregarSaldo(2000);
        admin.quitarSaldo(3000);

        Assertions.assertEquals(2000, admin.getSaldo());
    }

    @Test
    void getOperaciones() {
        admin.agregarSaldo(2000);
        admin.agregarSaldo(3000);
        admin.agregarSaldo(3000);
        admin.agregarSaldo(3000);

        Assertions.assertEquals(4, admin.getOperaciones());
    }
}