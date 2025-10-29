package modelo;

import java.util.ArrayList;
import java.util.List;

import appconsole.Util;

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

    public List<Pedido> getListaPedidos() {
        return lista_pedidos;
    }
    
    


    
	public Pedido getEntregas(int id) throws Exception{			
		for (Pedido c : lista_pedidos) {
			if (c.getId() == id) {
				return c;
			}
		}

		throw new Exception("Convidado não encontrado");
	}

	public void adicionarEntrega(Pedido pedido) {
		this.lista_pedidos.add(pedido);
	}
	

    
   
	//toString
	@Override
	public String toString() {
		ArrayList<String> descricaoPedido = new ArrayList<String>();
		for(Pedido c : this.getListaPedidos()) {
			descricaoPedido.add(c.getDescricao());
		}
		
		return "Entrega [data=" + data + ", " + " Pedidos: " + descricaoPedido + "]";
	}


}
