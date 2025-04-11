package dto;

public abstract class Cuenta {
    protected double saldo;
    protected int transacciones;
    protected int id;

    public Cuenta() {
        this.saldo = 0;
        this.transacciones = 0;
    }

    public int getId(){
        return id;
    }
}
