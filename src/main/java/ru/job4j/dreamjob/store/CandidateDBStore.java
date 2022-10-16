package ru.job4j.dreamjob.store;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.model.City;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CandidateDBStore {
    private static final Logger LOG = LogManager.getLogger(CandidateDBStore.class.getName());
    private BasicDataSource pool;

    public CandidateDBStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public List<Candidate> findAll() {
        List<Candidate> candidates = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT * FROM candidate")
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    Candidate candidate = new Candidate(it.getInt("id"),
                                                        it.getString("name"),
                                                        it.getString("description"),
                                                        new City(it.getInt("city_id"), null)
                                                       );
                    candidate.setPhoto(it.getBytes("photo"));
                    candidate.setCreated(it.getDate("created"));
                    candidates.add(candidate);
                }
            }
        } catch (Exception e) {
            LOG.error("findAll", e);
        }
        return candidates;
    }

    public Candidate add(Candidate candidate) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(
                     "INSERT INTO candidate(name, description, photo, city_id) "
                             + "VALUES (?, ?, ?, ?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, candidate.getName());
            ps.setString(2, candidate.getDescription());
            ps.setBytes(3, candidate.getPhoto());
            ps.setInt(4, candidate.getCity().getId());
            ps.execute();
            try (ResultSet it = ps.getGeneratedKeys()) {
                if (it.next()) {
                    candidate.setId(it.getInt(1));
                }
            }
        } catch (Exception e) {
            LOG.error("add", e);
        }
        return candidate;
    }

    public void update(Candidate candidate) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(
                     "UPDATE candidate SET name = ?, description = ?, photo = ?,"
                             + " city_id = ? WHERE id = ?")
        ) {
            ps.setString(1, candidate.getName());
            ps.setString(2, candidate.getDescription());
            ps.setBytes(3, candidate.getPhoto());
            ps.setInt(4, candidate.getCity().getId());
            ps.setInt(5, candidate.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            LOG.error("update", e);
        }
    }

    public Candidate findById(int id) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT * FROM candidate WHERE id = ?")
        ) {
            ps.setInt(1, id);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    Candidate candidate = new Candidate(it.getInt("id"),
                            it.getString("name"),
                            it.getString("description"),
                            new City(it.getInt("city_id"), null)
                    );
                    candidate.setPhoto(it.getBytes("photo"));
                    candidate.setCreated(it.getDate("created"));
                    return candidate;
                }
            }
        } catch (Exception e) {
            LOG.error("findById", e);
        }
        return null;
    }

    public void clearTable() {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("DELETE FROM candidate")
        ) {
            ps.execute();
        } catch (Exception e) {
            LOG.error("clearTable", e);
        }
    }
}