
import com.j256.ormlite.field.DatabaseField;

public class Habilidade {

    @DatabaseField
    protected int dano;
    
    @DatabaseField
    protected String tipo;

    @DatabaseField
    protected String nomeSkill;

    @DatabaseField
    protected int custo;

    @DatabaseField(generatedId = true)
    protected int idSkill;
    
    public Habilidade() {
    }
    
    public String getTipo(){
        return this.tipo;
    }
    public void setTipo(String tipo){ 
        this.tipo = tipo;
    }

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

    public String getNomeSkill() {
        return nomeSkill;
    }

    public void setNomeSkill(String nomeSkill) {
        this.nomeSkill = nomeSkill;
    }

    public int getCusto() {
        return custo;
    }

    public void setCusto(int custo) {
        this.custo = custo;
    }

    public int getIdSkill() {
        return idSkill;
    }

    public void setIdSkill(int idSkill) {
        this.idSkill = idSkill;
    }
    
    Habilidade(int dano, int custo, String nomeSkill){
        this.dano = dano;
        this.custo = custo;
        this.nomeSkill = nomeSkill;
    }
    
}
