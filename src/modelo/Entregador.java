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
    @Override
    public String toString() {
        String info = "Entregador [id=" + id + ", nome=" + nome;
        
        if (lista_entregas.isEmpty()) {
            info += ", Entregas: Não tem";
        } else {
            info += ", Entregas: [";
            for (Entrega e : lista_entregas) {
                info += e.getId() + ", "; 
            }
            info = info.substring(0, info.length() - 2) + "]]"; 
        }
        
        return info;
        
    }}
 
