package modelo;

import java.util.ArrayList;
import java.util.List;

import appconsole.Util;

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

    public List<Entrega> getListaEntregas() {
        return lista_entregas;
    }
    
    
    
	public Entrega getEntregador(int id) throws Exception{			
		for (Entrega e : lista_entregas) {
			if (e.getId() == id) {
				return e;
			}
		}

		throw new Exception("Evento não encontrado");
	}
	
	//métodos gerais
	public void adicionarEntrega(Entrega entrega) {
		this.lista_entregas.add(entrega);
	}
	
	public void removerEntrega(Entrega entrega) {
		this.lista_entregas.remove(entrega);
	}
	


@Override
public String toString() {

	
	return "id: " + this.id + ","+ " nome: " + this.nome + "," + " Entregas: " + lista_entregas ;
}

}
