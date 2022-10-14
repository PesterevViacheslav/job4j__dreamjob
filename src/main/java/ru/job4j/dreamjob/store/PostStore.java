package ru.job4j.dreamjob.store;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Post;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class PostStore - Хранилище вакансий. Решение задач уровня Middle.
 * 3.2. Web Тема : 3.2.2. Html, Bootstrap, Thymeleaf.
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 01.10.2022
 * @version 1
 */
@Repository
@ThreadSafe
public class PostStore {
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();
    private final AtomicInteger ids = new AtomicInteger(4);
    private PostStore() {
        posts.put(0, new Post(0, "Junior Java Job", "dsc1"));
        posts.put(1, new Post(1, "Middle Java Job", "dsc2"));
        posts.put(2, new Post(2, "Senior Java Job", "dsc3"));
    }
    public Collection<Post> findAll() {
        return posts.values();
    }
    public void add(Post post) {
        post.setId(ids.incrementAndGet());
        posts.put(post.getId(), post);
    }
    public Post findById(int id) {
        return posts.get(id);
    }
    public void update(Post post) {
        posts.put(post.getId(), post);
    }
}
