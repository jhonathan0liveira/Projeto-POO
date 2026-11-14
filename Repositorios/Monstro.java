
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.field.DatabaseField;

@DatabaseTable(tableName = "monstro")
public class Monstro extends Entidade {

    @DatabaseField
    protected String fraqueza = "Impacto";

    @DatabaseField
    protected int ataque1;

    @DatabaseField
    protected int ataque2;

    @DatabaseField
    protected int ataque3;

    @DatabaseField
    protected int ataque4;

    public Monstro() {
        super();
    }

    public Monstro(String fraqueza) {
        super();
        this.fraqueza = fraqueza;
    }

    public String getFraqueza() {
        return this.fraqueza;
    }

    public void setFraqueza(String fraqueza) {
        this.fraqueza = fraqueza;
    }

    public int getAtaque1() {
        return this.ataque1;
    }

    public void setAtaque1(int ataque1) {
        this.ataque1 = ataque1;
    }

    public int getAtaque2() {
        return this.ataque2;
    }

    public void setAtaque2(int ataque2) {
        this.ataque2 = ataque2;
    }

    public int getAtaque3() {
        return this.ataque3;
    }

    public void setAtaque3(int ataque3) {
        this.ataque3 = ataque3;
    }

    public int getAtaque4() {
        return this.ataque4;
    }

    public void setAtaque4(int ataque4) {
        this.ataque4 = ataque4;
    }
}
