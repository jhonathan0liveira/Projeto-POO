public class HabilidadeRepositoryTest {
    public static void main(String[] args) {
        Database database = new Database(":memory:");
        HabilidadeRepository repository = new HabilidadeRepository(database);

        testarCadastroEConsulta(repository);
        testarAtualizacao(repository);
        testarListagem(repository);
        testarExclusao(repository);
        testarExclusaoComInstanciasDiferentes(repository);

        database.close();

        System.out.println("Todos os testes passaram!");
    }

    public static void testarCadastroEConsulta(HabilidadeRepository repository) {
        Habilidade habilidade = new Habilidade(30, 10, "Corte");

        Habilidade cadastrada = repository.create(habilidade);

        verificar(cadastrada != null, "Cadastro da habilidade");
        verificar(cadastrada.getIdSkill() > 0, "Geração automática do ID");

        Habilidade encontrada = repository.loadFromId(cadastrada.getIdSkill());

        verificar(encontrada != null, "Consulta por ID");

        verificar(encontrada.getNomeSkill().equals("Corte"), "Nome da habilidade");
        verificar(encontrada.getDano() == 30, "Dano da habilidade");
        verificar(encontrada.getCusto() == 10, "Custo da habilidade");
    }

    public static void verificar(boolean resultado, String teste) {
        if (!resultado) {
            throw new AssertionError("Falha: " + teste);
        }

        System.out.println("Aprovado: " + teste);
    }

    public static void testarAtualizacao(HabilidadeRepository repository) {
        Habilidade habilidade = new Habilidade(30, 10, "Corte");

        repository.create(habilidade);

        habilidade.setDano(50);
        habilidade.setCusto(15);
        habilidade.setNomeSkill("Corte Poderoso");

        repository.update(habilidade);

        Habilidade atualizada = repository.loadFromId(habilidade.getIdSkill());

        verificar(atualizada.getDano() == 50, "Atualização do dano");
        verificar(atualizada.getCusto() == 15, "Atualização do custo");
        verificar(atualizada.getNomeSkill().equals("Corte Poderoso"), "Atualização do nome");
    }

    public static void testarListagem(HabilidadeRepository repository) {
        java.util.List<Habilidade> habilidades = repository.loadAll();

        verificar(habilidades != null, "Lista não nula");
        verificar(habilidades.size() == 2, "Quantidade de habilidades cadastradas");
        verificar(repository.getLoadedHabilidades().size() == 2, "Atualização da lista interna");
        verificar(repository.getLoadedHabilidade() != null, "Definição da habilidade atual");
    }

    public static void testarExclusao(HabilidadeRepository repository) {
        Habilidade habilidade = new Habilidade(20, 5, "Investida");

        repository.create(habilidade);

        int id = habilidade.getIdSkill();

        repository.delete(habilidade);

        verificar(repository.getLoadedHabilidades().size() == 2, "Remoção da habilidade da lista interna");

        verificar(repository.getLoadedHabilidade() == null, "Limpeza da habilidade atual");

        Habilidade encontrada = repository.loadFromId(id);

        verificar(encontrada == null, "Exclusão da habilidade do banco");
    }

    public static void testarExclusaoComInstanciasDiferentes(HabilidadeRepository repository) {
        Habilidade habilidade = new Habilidade(40, 10, "Flecha");

        repository.create(habilidade);

        int id = habilidade.getIdSkill();

        Habilidade carregada = repository.loadFromId(id);

        verificar(carregada != null, "Consulta para teste de exclusão");
        verificar(carregada != habilidade, "Instâncias diferentes do mesmo registro");

        repository.delete(habilidade);

        verificar(repository.getLoadedHabilidades().stream().noneMatch(h -> h.getIdSkill() == id), "Remoção do registro do cache por ID");

        verificar(repository.getLoadedHabilidade() == null, "Limpeza da habilidade atual após exclusão");
    }
}