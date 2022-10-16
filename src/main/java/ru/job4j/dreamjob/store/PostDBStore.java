package ru.job4j.dreamjob.store;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostDBStore {
    private static final Logger LOG = LogManager.getLogger(PostDBStore.class.getName());

    private final BasicDataSource pool;

    public PostDBStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public List<Post> findAll() {
        List<Post> posts = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM post")
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    Post post = new Post(it.getInt("id"),
                                         it.getString("name"),
                                         it.getString("description"),
                                         new City(it.getInt("city_id"), null));
                    post.setCreated(it.getDate("created"));
                    post.setVisible(it.getBoolean("visible"));
                    posts.add(post);
                }
            }
        } catch (Exception e) {
            LOG.error("findAll", e);
        }
        return posts;
    }

    public Post add(Post post) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(
                     "INSERT INTO post(name, description, visible, city_id) VALUES (?, ?, ?, ?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, post.getName());
            ps.setString(2, post.getDescription());
            ps.setBoolean(3, post.isVisible());
            ps.setInt(4, post.getCity().getId());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    post.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            LOG.error("add", e);
        }
        return post;
    }

    public void update(Post post) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("UPDATE post SET name = ?, description = ?, "
                     + "visible = ?, city_id = ? WHERE id = ?")
        ) {
            ps.setString(1, post.getName());
            ps.setString(2, post.getDescription());
            ps.setBoolean(3, post.isVisible());
            ps.setInt(4, post.getCity().getId());
            ps.setInt(5, post.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            LOG.error("update", e);
        }
    }

    public Post findById(int id) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM post WHERE id = ?")
        ) {
            ps.setInt(1, id);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    Post post = new Post(it.getInt("id"),
                                         it.getString("name"),
                                         it.getString("description"),
                                         new City(it.getInt("city_id"), null)
                                         );
                    post.setVisible(it.getBoolean("visible"));
                    post.setCreated(it.getDate("created"));
                    return post;
                }
            }
        } catch (Exception e) {
            LOG.error("findById", e);
        }
        return null;
    }
    public void clearTable() {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("DELETE FROM post")
        ) {
            ps.execute();
        } catch (Exception e) {
            LOG.error("clearTable", e);
        }
    }
}