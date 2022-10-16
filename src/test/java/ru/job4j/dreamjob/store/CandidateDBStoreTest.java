package ru.job4j.dreamjob.store;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.model.City;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CandidateDBStoreTest {
    private static CandidateDBStore store;
    private static Candidate candidate1;
    private static Candidate candidate2;
    private static Candidate candidate3;

    @BeforeClass
    public static void initConnection() {
        store = new CandidateDBStore(new Main().loadPool());
    }

    @Before
    public void newCandidate() {
        candidate1 = new Candidate(0, "Candidate 1", "Senior", new City(0, null));
        candidate2 = new Candidate(1, "Candidate 2", "Middle", new City(1, null));
        candidate3 = new Candidate(2, "Candidate 3", "Junior", new City(2, null));
    }

    @After
    public void clear() {
        store.clearTable();
    }

    @Test
    public void whenCreateCandidate() {
        store.add(candidate1);
        Candidate candidateInDb = store.findById(candidate1.getId());
        assertThat(candidateInDb.getName(), is(candidate1.getName()));
    }
    @Test
    public void whenUpdateCandidate() {
        store.add(candidate2);
        candidate3.setId(candidate2.getId());
        store.update(candidate3);
        Candidate candidateInDb = store.findById(candidate2.getId());
        assertThat(candidateInDb, is(candidate3));
    }
    @Test
    public void whenFindAll() {
        List<Candidate> lists = List.of(candidate1, candidate2, candidate3);
        for (Candidate list : lists) {
            store.add(list);
        }
        assertThat(lists, is(store.findAll()));
    }
}

