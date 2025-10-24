package appconsole;
import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;

import modelo.Entregador;



// Classe com o nome "Filtro"
class Filtro implements Evaluation {

    public void evaluate(Candidate candidate) {
        // 1. Pega o objeto candidato e converte para Entregador
        Entregador e = (Entregador) candidate.getObject();

        // 2. Verifica a condição: a lista de entregas tem mais de 5 itens?
        // (Estou assumindo que o método get se chama getListaDeEntrega())
        boolean maisDeCinco = e.getEntregas().size() > 5;

        // 3. Inclui o objeto no resultado se a condição for verdadeira
        candidate.include(maisDeCinco);
    }
}