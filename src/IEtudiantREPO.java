import java.sql.SQLException;

public interface IEtudiantREPO {

    abstract void add(Etudiant E) throws SQLException;

    abstract boolean Exists(int matricule) throws SQLException;

    abstract boolean Exists(String email) throws SQLException;

}

