package modelo;

public class Pedido {
    private int id;
    private String data;
    private double valor;
    private String descricao;
    private Entrega entrega;

    public Pedido(int id, String data, double valor, String descricao) {
        this.id = id;
        this.data = data;
        this.valor = valor;
        this.descricao = descricao;
    }

    public Entrega getEntrega() {
        return entrega;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }

    @Override
    public String toString() {
        int idEntrega = (entrega != null) ? entrega.getId() : -1;
        return "Pedido [id=" + id + ", data=" + data + ", valor=" + valor 
                + ", descricao=" + descricao + ", id_entrega=" + idEntrega + "]";
    }
}
