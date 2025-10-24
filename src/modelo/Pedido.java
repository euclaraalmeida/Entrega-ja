package modelo;

public class Pedido {
    private int id;
    private String data;
    private double valor;
    private String descricao;
    private Entrega entrega;
    private String localizacao;

    public Pedido(int id, String data, double valor, String descricao, String localizacao) {
        this.id = id;
        this.data = data;
        this.valor = valor;
        this.descricao = descricao;
        this.localizacao = localizacao;
    }

    public Entrega getEntrega() {
        return entrega;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }

 // Dentro da classe modelo.Pedido
    @Override
    public String toString() {
        String info = "Pedido [id=" + id + ", data=" + data + ", valor=" + valor + 
                      ", descrição=" + descricao + ", localização=" + localizacao;
        
        // Relacionamento com Entrega
        if (entrega != null) {
            info += ", ID da Entrega: " + entrega.getId(); // Pega só o ID da entrega
        } else {
            info += ", Entrega: (não definida)";
        }
        
        info += "]";
        return info;
        // Saída: Pedido [id=1, data=22/10/2025, valor=150.0, ..., ID da Entrega: 1]
    }
}
