import java.util.Random;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.field.DatabaseField;

@DatabaseTable(tableName = "player")
public class Player extends Entidade {

    @DatabaseField
    private String name;

    @DatabaseField
    private double dinheiro;

    public Player() {
        super();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDinheiro() {
        return this.dinheiro;
    }

    public void setDinheiro(double dinheiro) {
        this.dinheiro = dinheiro;
        }
    
    public int atacar(int id, Monstro monstro) {
    
        Database database = new Database("rpg.db");
        HabilidadeRepository habilidadeRepo =
            new HabilidadeRepository(database);
    
        try {
            Habilidade habilidade = habilidadeRepo.loadFromId(id);
    
            if (habilidade == null) {
                System.out.println("Habilidade não encontrada!");
                return 0;
            }
    
            int custo = habilidade.getCusto();
            int dano = habilidade.getDano();
            String tipo = habilidade.getTipo();
    
            if (this.getStm() < custo) {
                System.out.println("STM insuficiente!");
                return 0;
            }
    
            setStm(this.getStm() - custo);
    
            Random random = new Random();
            int dado = random.nextInt(20) + 1;
    
            if (dado <= monstro.getAgility()) {
                System.out.println("O monstro desviou do ataque!");
                return 0;
            }
    
            System.out.println("Ataque acertou!");
    
            if (monstro.getFraqueza().equals(tipo)) {
                monstro.setHp(monstro.getHp() - (2 * dano));
    
                System.out.println("Efetivo! Dano: " + (2 * dano));
    
                return 2 * dano;
            }
    
            monstro.setHp(monstro.getHp() - dano);
    
            return dano;
    
        } finally {
            database.close();
        }
}
}
