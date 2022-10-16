package ru.job4j.dreamjob.store;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PostDBStoreTest {
    private static PostDBStore store;
    private static Post post1;
    private static Post post2;
    private static Post post3;

    @BeforeClass
    public static void initConnection() {
        store = new PostDBStore(new Main().loadPool());
    }

    @Before
    public void newPost() {
        post1 = new Post(0, "Java", "Senior", new City(0, null));
        post2 = new Post(1, "C++", "Middle", new City(1, null));
        post3 = new Post(2, "Java", "Junior", new City(2, null));
    }

    @After
    public void clear() {
        store.clearTable();
    }

    @Test
    public void whenCreatePost() {
        store.add(post1);
        Post postInDb = store.findById(post1.getId());
        assertThat(postInDb.getName(), is(post1.getName()));
    }
    @Test
    public void whenUpdatePost() {
        store.add(post2);
        post3.setId(post2.getId());
        store.update(post3);
        Post postInDb = store.findById(post2.getId());
        assertThat(postInDb, is(post3));
    }
    @Test
    public void whenFindAll() {
        List<Post> lists = List.of(post1, post2, post3);
        for (Post list : lists) {
            store.add(list);
        }
        assertThat(lists, is(store.findAll()));
    }
}
