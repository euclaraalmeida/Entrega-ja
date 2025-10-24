package modelo;

import java.util.ArrayList;
import java.util.List;

public class Entregador {
    private int id;
    private String nome;
    private List<Entrega> lista_entregas = new ArrayList<>();

    public Entregador(String nome) {
        this.nome = nome;
    }

 

	public void adicionar(Entrega e) {
        lista_entregas.add(e);
    }
    
    public void SetId(int idNovo) {
        this.id = idNovo;
   }
    public void remover(Entrega e) {
        lista_entregas.remove(e);
    }

    public String getNome() {
        return nome;
    }

    public List<Entrega> getEntregas() {
        return lista_entregas;
    }
 // Dentro da classe modelo.Entregador
    @Override
    public String toString() {
        String info = "Entregador [id=" + id + ", nome=" + nome;
        
        // Mostra o relacionamento
        if (lista_entregas.isEmpty()) {
            info += ", Entregas: 0]";
        } else {
            info += ", Qtd Entregas: " + lista_entregas.size() + "]";
        }
        
        return info;
        // Saída: Entregador [id=1, nome=João, Qtd Entregas: 1]
    }
}
 