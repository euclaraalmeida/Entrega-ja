import modelo.Entregador;

/**
			 * Classe de Filtro customizada que implementa a lógica
			 * para avaliar cada objeto Entregador.
			 */
			class FiltroEntregadorComMaisDe5Entregas implements Evaluation {
			    
			    public void evaluate(Candidate candidate) {
			        // Pega o objeto Entregador que está sendo avaliado
			        Entregador e = (Entregador) candidate.getObject();

			        // A lógica do filtro
			        boolean incluir = (e.getLista_entregas() != null && 
			                             e.getListaDeEntrega().size() > 5);
			        
			        // Diz ao db4o se deve incluir (true) ou não (false) este objeto
			        candidate.include(incluir);
			    }
			}
		