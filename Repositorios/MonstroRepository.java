import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class MonstroRepository {

    private static Database database;
    private static Dao<Monstro, Integer> dao;

    private List<Monstro> loadedMonstros;
    private Monstro loadedMonstro;

    public MonstroRepository(Database database) {
        MonstroRepository.setDatabase(database);

        this.loadedMonstros = new ArrayList<>();
        this.loadedMonstro = null;
    }

    public static void setDatabase(Database database) {
        MonstroRepository.database = database;

        try {
            dao = DaoManager.createDao(
                database.getConnection(),
                Monstro.class
            );

            TableUtils.createTableIfNotExists(
                database.getConnection(),
                Monstro.class
            );

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Monstro create(Monstro monstro) {
        try {
            int nrows = dao.create(monstro);

            if (nrows == 0) {
                throw new SQLException("Error: object not saved");
            }

            this.loadedMonstro = monstro;
            this.loadedMonstros.add(monstro);

        } catch (SQLException e) {
            System.out.println(e);
        }

        return monstro;
    }

    public void update(Monstro monstro) {
        try {
            dao.update(monstro);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void delete(Monstro monstro) {
        try {
            dao.delete(monstro);

            this.loadedMonstros.remove(monstro);

            if (this.loadedMonstro == monstro) {
                this.loadedMonstro = null;
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Monstro loadFromId(int id) {
        try {
            this.loadedMonstro = dao.queryForId(id);

            if (this.loadedMonstro != null &&
                !this.loadedMonstros.contains(this.loadedMonstro)) {

                this.loadedMonstros.add(this.loadedMonstro);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return this.loadedMonstro;
    }

    public List<Monstro> loadAll() {
        try {
            this.loadedMonstros = dao.queryForAll();

            if (!this.loadedMonstros.isEmpty()) {
                this.loadedMonstro = this.loadedMonstros.get(0);
            } else {
                this.loadedMonstro = null;
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return this.loadedMonstros;
    }

    public List<Monstro> getLoadedMonstros() {
        return loadedMonstros;
    }

    public Monstro getLoadedMonstro() {
        return loadedMonstro;
    }
}
