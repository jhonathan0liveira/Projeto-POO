
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
    
    
    public int atacar(int id) {
    
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
    
            if (this.getStm() < custo) {
                System.out.println("STM insuficiente!");
                return 0;
            }
    
            setStm(this.getStm() - custo);
    
            return dano;
    
        } finally {
            database.close();
        }
    }
}
