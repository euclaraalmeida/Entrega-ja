package modelo;

import java.util.ArrayList;
import java.util.List;

public class Entrega {
    private int id;
    private String data;
    private Entregador entregador;
    private List<Pedido> lista_pedidos = new ArrayList<>();

   
    public Entrega(String data) {
        this.data = data;	}

	

	public void adicionar(Pedido p) {
        lista_pedidos.add(p);
    }

    public void remover(Pedido p) {
        lista_pedidos.remove(p);
    }
    
    public int getId() {
        return id;
    }
    
    public void SetId(int idNovo) {
         this.id = idNovo;
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

 // Dentro da classe modelo.Entrega
    @Override
    public String toString() {
        String info = "Entrega [id=" + id + ", data=" + data;
        
        // Relacionamento com Entregador
        if (entregador != null) {
            info += ", Entregador: " + entregador.getNome(); // Pega só o nome
        } else {
            info += ", Entregador: (não definido)";
        }
        
        // Relacionamento com Pedidos
        info += ", Qtd Pedidos: " + lista_pedidos.size() + "]";
        
        return info;
    }
}
