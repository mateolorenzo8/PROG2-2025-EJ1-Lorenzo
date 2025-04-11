package dto;

public class CajaDeAhorroBuilder implements Builder {
    private int id;

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public CajaDeAhorro getCajaDeAhorro() {
        return new CajaDeAhorro(id);
    }
}
