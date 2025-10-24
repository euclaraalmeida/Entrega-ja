package appconsole;

import java.util.List;
import com.db4o.ObjectContainer;
import com.db4o.query.Query;
import modelo.Entrega;
import modelo.Entregador;


public class Apagar {
    private ObjectContainer manager;

    
    public Apagar() {
        manager = Util.conectarBanco();
        apagar(); 
        Util.desconectar();
    }

    public void apagar() {
        
        Query q = manager.query();
        q.constrain(Entregador.class);
        q.descend("nome").constrain("João");
        List<Entregador> resultados = q.execute();
        
        if (resultados.size() > 0) {
            Entregador entregadorParaApagar = resultados.get(0);
            System.out.println("Entregador encontrado: " + entregadorParaApagar.getNome());
            
            
            List<Entrega> listaDeEntregas = entregadorParaApagar.getEntregas();
            
            if (listaDeEntregas.isEmpty()) {
                System.out.println("Entregador não tem entregas, pode ser apagado diretamente.");
            } else {
                System.out.println("Tratando " + listaDeEntregas.size() + " entrega(s) órfã(s)...");
                for(Entrega e : listaDeEntregas) {
                    
                    e.setEntregador(null); 
                    
                    manager.store(e); 
                    System.out.println("...Entrega " + e.getId() + " agora está sem entregador (órfã).");
                }
            }
            
            
            manager.delete(entregadorParaApagar);
            
            
            manager.commit();
            System.out.println("Entregador 'João' foi apagado.");
        }
        else {
            System.out.println("Entregador 'João' nao encontrado.");
        }
    }

    
    public static void main(String[] args) {
        new Apagar();
    }
}