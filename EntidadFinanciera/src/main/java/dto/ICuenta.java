package dto;

public interface ICuenta {
    boolean agregarSaldo(int id, double monto);
    boolean quitarSaldo(int id, double monto);
    double getSaldo();
    int getOperaciones();
}
