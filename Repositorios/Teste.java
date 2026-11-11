import java.util.Scanner;
public class Teste {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Database database = new Database("rpg.db");
        HabilidadeRepository habilidadeRepo =
            new HabilidadeRepository(database);
        
            
        System.out.println("Digite o id da skill: ");
        int id = scanner.nextInt();
            
        Habilidade habilidade = habilidadeRepo.loadFromId(id);

        if (habilidade != null) {
            int dano = habilidade.getDano();
            String skill = habilidade.getNomeSkill();
            System.out.println("Skill: "+skill+" Dano: " + dano);
        } else {
            System.out.println("Habilidade não encontrada!");
        }

        database.close();
    }
}
