package appconsole;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

import modelo.Entregador;
import modelo.Entrega;
import modelo.Pedido;

public class Consultar {
	private ObjectContainer manager;

	public Consultar() {
		try {
			manager = Util.conectarBanco();
			consultar(); // Chama o método com as consultas
		} catch (Exception e) {
			System.out.println("Erro durante a consulta: " + e.getMessage());
		} finally {
			Util.desconectar();
		}
		System.out.println("\n\nConsulta finalizada.");
	}

	public void consultar() {
		System.out.println("------ Iniciando Consultas ------");

		// -----------------------------------------------------------------
		// 1. quais as entregas na data 23/10/2025
		// -----------------------------------------------------------------
		System.out.println("\n--- 1. Quais as entregas na data 23/10/2025 ---");
		Query q1 = manager.query();
		q1.constrain(Entrega.class);
		q1.descend("data").constrain("23/10/2025");
		List<Entrega> resultados1 = q1.execute();

		for (Entrega e : resultados1) {
			System.out.println(e); 

		}

		// -----------------------------------------------------------------
		// 2. quais os pedidos entregues pelo entregador de nome "Maria"
		// -----------------------------------------------------------------
		System.out.println("\n--- 2. Quais os pedidos entregues pelo entregador 'Maria' ---");
		Query q2 = manager.query();
		q2.constrain(Pedido.class);
		q2.descend("entrega")
		  .descend("entregador")
		  .descend("nome")
		  .constrain("Maria");
		
		List<Pedido> resultados2 = q2.execute();

			System.out.println("Pedidos encontrados:");
			for (Pedido p : resultados2) {
				// Print principal
				System.out.println(p); 
				
			
			}
	

		// -----------------------------------------------------------------
		// 3. quais os entregadores que tem mais N entregas (ex: N=2)
		// -----------------------------------------------------------------
		System.out.println("\n--- 3. Quais os entregadores que tem mais de 5 entregas ---");
		
		final int N = 5; // Você pode mudar esse valor

		Query q3 = manager.query();
		q3.constrain(Entregador.class);
		q3.constrain(new Evaluation() {
			public void evaluate(Candidate candidate) {
				Entregador entregador = (Entregador) candidate.getObject();
				if (entregador.getListaDeEntrega() != null && entregador.getListaDeEntrega().size() > N) {
					candidate.include(true);
				} else {
					candidate.include(false);
				}
			}
		});

		List<Entregador> resultados3 = q3.execute();

		if (resultados3.isEmpty()) {
			System.out.println("Nenhum entregador com mais de " + N + " entregas encontrado.");
		} else {
			System.out.println("Entregadores encontrados:");
			for (Entregador e : resultados3) {
				// Print principal
				System.out.println(e);
				
			
	}
	
	
	public static void main(String[] args) {
		new Consultar();
	}
}