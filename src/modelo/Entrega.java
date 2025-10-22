package modelo;

import java.util.ArrayList;
import java.util.List;

public class Entrega {
    private int id;
    private String data;
    private String localizacao;
    private Entregador entregador;
    private List<Pedido> lista_pedidos = new ArrayList<>();

    public Entrega(int id, String data, String localizacao) {
        this.id = id;
        this.data = data;
        this.localizacao = localizacao;
    }

    public void adicionar(Pedido p) {
        lista_pedidos.add(p);
    }

    public void remover(Pedido p) {
        lista_pedidos.remove(p);
    }
    
    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public Entregador getEntregador() {
        return entregador;
    }

    public void setEntregador(Entregador entregador) {
        this.entregador = entregador;
    }

    public List<Pedido> getPedidos() {
        return lista_pedidos;
    }

    @Override
    public String toString() {
        String nomeEntregador = (entregador != null) ? entregador.getNome() : "N/A";
        return "Entrega [id=" + id + ", data=" + data + ", localizacao=" + localizacao 
                + ", entregador=" + nomeEntregador + ", N. de pedidos=" + lista_pedidos.size() + "]";
    }
}
