package appconsole;

import com.db4o.ObjectContainer;
import modelo.Pedido;
import modelo.Entregador;
import modelo.Entrega;

public class Cadastrar {
	private ObjectContainer manager;

	public Cadastrar() {
		try { 
			manager = Util.conectarBanco();
			
			// --- Criar Entregadores ---
			Entregador e1 = new Entregador("João");
			Entregador e2 = new Entregador("Maria");
			Entregador e3 = new Entregador("Lucas");
			Entregador e4 = new Entregador("Matheus");
			Entregador e5 = new Entregador("Fausto");
			Entregador e6 = new Entregador("Mariana");
			Entregador e7 = new Entregador("Davi");
			Entregador e8 = new Entregador("Arthur");
			Entregador e9 = new Entregador("Clara");
			Entregador e10 = new Entregador("Laura");

			// --- Criar Entregas ---
			Entrega E1 = new Entrega("22/10/2025");
			Entrega E2 = new Entrega("23/10/2025");
			Entrega E3 = new Entrega("24/10/2025");
			Entrega E4 = new Entrega("25/10/2025");
			Entrega E5 = new Entrega("26/10/2025");
			Entrega E6 = new Entrega("27/10/2025");
			Entrega E7 = new Entrega("28/10/2025");
			Entrega E8 = new Entrega("29/10/2025");
			Entrega E9 = new Entrega("30/10/2025");
			Entrega E10 = new Entrega("31/10/2025");

			// ---  Criar Pedidos ---
			Pedido P1 = new Pedido("22/10/2025", 150.0, "Pedido de eletrônicos", "Porto Velho");
			Pedido P2 = new Pedido("23/10/2025", 89.9, "Pedido de roupas", "Ji-Paraná");
			Pedido P3 = new Pedido("24/10/2025", 230.5, "Pedido de livros", "Ariquemes");
			Pedido P4 = new Pedido("25/10/2025", 45.0, "Pedido de acessórios", "Cacoal");
			Pedido P5 = new Pedido("26/10/2025", 120.75, "Pedido de brinquedos", "Guajará-Mirim");
			Pedido P6 = new Pedido("27/10/2025", 310.4, "Pedido de eletrodomésticos", "Vilhena");
			Pedido P7 = new Pedido("28/10/2025", 67.8, "Pedido de cosméticos", "Jaru");
			Pedido P8 = new Pedido("29/10/2025", 540.0, "Pedido de móveis", "Rolim de Moura");
			Pedido P9 = new Pedido("30/10/2025", 22.5, "Pedido de alimentos", "Buritis");
			Pedido P10 = new Pedido("31/10/2025", 98.3, "Pedido de calçados", "Ouro Preto do Oeste");

		
			e1.adicionar(E1);   // Entregador 'João' fez a Entrega E1
			E1.setEntregador(e1); // A Entrega E1 foi feita por 'João'
			E1.adicionar(P1);    // A Entrega E1 contém o Pedido P1
			P1.setEntrega(E1);   // O Pedido P1 pertence à Entrega E1

			e2.adicionar(E2);   // Entregadora 'Maria' fez a Entrega E2
			E2.setEntregador(e2); // A Entrega E2 foi feita por 'Maria'
			E2.adicionar(P2);    // A Entrega E2 contém o Pedido P2
			E2.adicionar(P3);    // A Entrega E2 também contém o Pedido P3
			P2.setEntrega(E2);   // O Pedido P2 pertence à Entrega E2
			P3.setEntrega(E2);   // O Pedido P3 pertence à Entrega E2

			
			e3.adicionar(E3);
			E3.setEntregador(e3);
			E3.adicionar(P4);
			P4.setEntrega(E3);
			
			
		


		
			manager.store(P1);
			manager.store(P2);
			manager.store(P3);
			manager.store(P4);
			manager.store(P5);
			manager.store(P6);
			manager.store(P7);
			manager.store(P8);
			manager.store(P9);
			manager.store(P10);

			manager.store(e1);
			manager.store(e2);	
			manager.store(e3);	
			manager.store(e4);	
			manager.store(e5);	
			manager.store(e6);	
			manager.store(e7);	
			manager.store(e8);	
			manager.store(e9);	
			manager.store(e10);	

			manager.store(E1);
			manager.store(E2);
			manager.store(E3);
			manager.store(E4);
			manager.store(E5);
			manager.store(E6);
			manager.store(E7);
			manager.store(E8);
			manager.store(E9);
			manager.store(E10);
			
			manager.commit();
			System.out.println("Banco populado com dados iniciais!");

		} catch (Exception e) {
			System.out.println("Erro ao cadastrar: " + e.getMessage());
			manager.rollback();
		} finally {
			Util.desconectar(); 
		}
	}

	

	//=================================================
	public static void main(String[] args) {
		new Cadastrar();
	}
}
