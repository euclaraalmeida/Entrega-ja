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





class ControleID {
  private static EmbeddedObjectContainer bancoSequencias;
  private static TreeMap<String, SequenciaID> memoriaSequencias = new TreeMap<>();
  private static boolean salvar;

  public static void ativar(ObjectContainer manager) {
    bancoSequencias = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "sequencias.db4o");

    lerSequenciasID();

    EventRegistry events = EventRegistryFactory.forObjectContainer(manager);

    events.creating().addListener((_, args) -> {
      try {
        Object objeto = args.object();

        Field campo = objeto.getClass().getDeclaredField("id");

        if (campo != null) {
          String nomeClasse = objeto.getClass().getName();
          SequenciaID sequencia = obterSequenciaID(nomeClasse);

          sequencia.incrementarID();
          campo.setAccessible(true);
          campo.setInt(objeto, sequencia.getUltimoId());
          memoriaSequencias.put(nomeClasse, sequencia);
          salvar = true;
        }


      } catch (Exception e) {
        System.out.println(e);
      }
    });

    events.created().addListener((_, _) -> {
      salvarSequenciasID();
    });

    events.closing().addListener((_, _) -> {
      if (bancoSequencias != null && !bancoSequencias.ext().isClosed()) {
        bancoSequencias.close();
      }
    });
  }

  public static void lerSequenciasID() {
    Query query = bancoSequencias.query();
    query.constrain(SequenciaID.class);

    List<SequenciaID> resultado = query.execute();

    for (SequenciaID seq : resultado) {
      memoriaSequencias.put(seq.getNomeClasse(), seq);
    }

    salvar = false;
  }

  public static SequenciaID obterSequenciaID(String nomeClasse) {
    return memoriaSequencias.containsKey(nomeClasse) ? memoriaSequencias.get(nomeClasse) : new SequenciaID(nomeClasse);
  }

  public static void salvarSequenciasID() {
    if (!salvar)
      return;

    for (SequenciaID seq : memoriaSequencias.values()) {
      if (seq.isModificado()) {
        bancoSequencias.store(seq);
        seq.setModificado(false);
      }
    }

    bancoSequencias.commit();
  }

}

class SequenciaID {
  private String nomeClasse;
  private Integer ultimoId = 0;
  transient private boolean modificado = false;

  public SequenciaID(String nomeClasse) {
    this.nomeClasse = nomeClasse;
  }

  public String getNomeClasse() {
    return nomeClasse;
  }

  public Integer getUltimoId() {
    return ultimoId;
  }

  public boolean isModificado() {
    return modificado;
  }

  public void setModificado(boolean modificado) {
    this.modificado = modificado;
  }

  public void incrementarID() {
    ultimoId++;
    setModificado(true);
  }
}