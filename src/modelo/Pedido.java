package modelo;

public class Pedido {
    private int id;
    private String data;
    private double valor;
    private String descricao;
    private Entrega entrega;
    private String localizacao;


    public Pedido(String data, double valor, String descricao, String localizacao) {
        this.data = data;
        this.valor = valor;
        this.descricao = descricao;
        this.localizacao = localizacao;	}

	public Entrega getEntrega() {
        return entrega;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }
    
    public int getId() {
        return id;
    }
    
    public void SetId(int idNovo) {
        this.id = idNovo;
   }

    @Override
    public String toString() {
        String info = "Pedido [id=" + id + ", data=" + data + ", valor=" + valor + 
                      ", descrição=" + descricao + ", localização=" + localizacao;
        
        if (entrega != null) {
            info += ", ID da Entrega: " + entrega.getId(); 
        } else {
            info += ", Entrega: Não tem";
        }
        
        info += "]";
        return info;
    }
}
