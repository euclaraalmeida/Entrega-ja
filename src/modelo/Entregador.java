package modelo;

import java.util.ArrayList;
import java.util.List;

public class Entregador {
    private int id;
    private String nome;
    private List<Entrega> lista_entregas = new ArrayList<>();

    public Entregador(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public void adicionar(Entrega e) {
        lista_entregas.add(e);
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
        String texto = "Entregador [id=" + id + ", nome=" + nome + ", N. de entregas=" + lista_entregas.size() + "]";
        texto += "\n Entregas: ";
        if (lista_entregas.isEmpty()) {
            texto += "Nenhuma entrega associada.";
        } else {
            for (Entrega e : lista_entregas) {
                texto += e.getId() + ", ";
            }
        }
        return texto;
    }
}
