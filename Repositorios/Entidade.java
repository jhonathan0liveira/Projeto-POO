
import com.j256.ormlite.field.DatabaseField;

public abstract class Entidade {

    @DatabaseField(generatedId = true)
    protected int id;

    @DatabaseField
    protected int lvl;

    @DatabaseField
    protected int hp = 10;

    @DatabaseField
    protected int hpMax = 10 ;

    @DatabaseField
    protected int agility = 10;

    @DatabaseField
    protected int str;

    @DatabaseField
    protected int stm = 10;

    @DatabaseField
    protected int stmMax = 10;

    public Entidade() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = Math.max(0, Math.min(hp, hpMax));
    }

    public int getHpMax() {
        return hpMax;
    }

    public void setHpMax(int hpMax) {
        this.hpMax = Math.max(0, hpMax);

        if (this.hp > this.hpMax) {
            this.hp = this.hpMax;
        }
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = Math.max(0, agility);
    }

    public int getStr() {
        return str;
    }

    public void setStr(int str) {
        this.str = Math.max(0, str);
    }

    public int getStm() {
        return stm;
    }

    public void setStm(int stm) {
        this.stm = Math.max(0, Math.min(stm, stmMax));
    }

    public int getStmMax() {
        return stmMax;
    }

    public void setStmMax(int stmMax) {
        this.stmMax = Math.max(0, stmMax);

        if (this.stm > this.stmMax) {
            this.stm = this.stmMax;
        }
    }
}
