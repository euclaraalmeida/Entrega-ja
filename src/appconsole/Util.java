package appconsole; 

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;

import modelo.Entrega;
import modelo.Entregador;
import modelo.Pedido;

public class Util {
    
    // O manager continua sendo a única instância da conexão
    private static ObjectContainer manager = null; // Começa como null

    public static ObjectContainer conectarBanco() {
        // ---- MELHORIA AQUI ----
        // Só abre uma nova conexão se o manager for null OU se estiver fechado
        if (manager == null || manager.ext().isClosed()) { 
            EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
            
            // 2. Desliga mensagens de log
            config.common().messageLevel(0); 
            
            //cascades
            config.common().objectClass(Entregador.class).cascadeOnUpdate(true);
            config.common().objectClass(Entregador.class).cascadeOnActivate(true);
            config.common().objectClass(Entregador.class).cascadeOnDelete(false); 
            
            config.common().objectClass(Entrega.class).cascadeOnUpdate(true);
            config.common().objectClass(Entrega.class).cascadeOnActivate(true);
            config.common().objectClass(Entrega.class).cascadeOnDelete(false); 
            
            config.common().objectClass(Pedido.class).cascadeOnUpdate(true);
            config.common().objectClass(Pedido.class).cascadeOnActivate(true);
            config.common().objectClass(Pedido.class).cascadeOnDelete(false); 

            // Abre o arquivo e guarda em manager
            manager = Db4oEmbedded.openFile(config, "banco_entrega.db4o");
        }
        
        // Retorna o manager (novo ou o que já existia)
        return manager;
    }

    
    public static void desconectar() {
        // ---- MELHORIA AQUI ----
        // Só tenta fechar se o manager não for null E não estiver fechado
        if (manager != null && !manager.ext().isClosed()) {
            manager.close();
            manager = null; // Essencial para o conectarBanco() funcionar da prox vez
        }
    }
}