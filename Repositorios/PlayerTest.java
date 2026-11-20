public class PlayerTest {
    public static void testarVida() {
        Player player = new Player();

        player.setHpMax(100);
        player.setHp(150);

        verificar(player.getHp() == 100, "Limite máximo de vida");
    }
    public static void main(String[] args) {
        testarNome();
        testarDinheiro();
        testarVida();
        testarStamina();
        testarAtributos();
        testarDinheiroNegativo();  

        System.out.println("Todos os testes passaram!");
    }

    public static void testarNome() {
        Player player = new Player();

        player.setName("Arthur");

        verificar(player.getName().equals("Arthur"), "Nome do jogador");
    }

    public static void testarDinheiro() {
        Player player = new Player();

        player.setDinheiro(150.50);

        verificar(player.getDinheiro() == 150.50, "Dinheiro do jogador");
    }

    public static void verificar(boolean resultado, String teste) {
        if (!resultado) {
            throw new AssertionError("Falha: " + teste);
        }

        System.out.println("Aprovado: " + teste);
    }

    public static void testarStamina() {
        Player player = new Player();

        player.setStmMax(50);
        player.setStm(80);

        verificar(player.getStm() == 50, "Limite máximo de stamina");
    }

    public static void testarAtributos() {
        Player player = new Player();

        player.setAgility(-10);
        player.setStr(-5);

        verificar(player.getAgility() == 0, "Agilidade não pode ser negativa");
        verificar(player.getStr() == 0, "Força não pode ser negativa");
    }

    public static void testarDinheiroNegativo() {
        Player player = new Player();

        player.setDinheiro(-100);

        verificar(player.getDinheiro() == -100, "Dinheiro negativo é aceito");
    }
}