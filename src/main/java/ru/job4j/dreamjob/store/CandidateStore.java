package ru.job4j.dreamjob.store;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.model.City;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * Class CandidateStore - Хранилище кандидатов. Решение задач уровня Middle.
 * 3.2. Web Тема : 3.2.2. Html, Bootstrap, Thymeleaf.
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 01.10.2022
 * @version 1
 */
@Repository
@ThreadSafe
public class CandidateStore {
    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();
    private final AtomicInteger ids = new AtomicInteger(2);
    private CandidateStore() {
        candidates.put(0, new Candidate(0, "Candidate 1", "dsc1", new City(0, "City1")));
        candidates.put(1, new Candidate(1, "Candidate 2", "dsc2", new City(1, "City2")));
        candidates.put(2, new Candidate(2, "Candidate 3", "dsc3", new City(2, "City3")));
    }
    public Collection<Candidate> findAll() {
        return candidates.values();
    }
    public void add(Candidate candidate) {
        candidate.setId(ids.incrementAndGet());
        candidates.put(candidate.getId(), candidate);
    }

    public void update(Candidate candidate) {
        candidates.put(candidate.getId(), candidate);
    }
    public Candidate findById(int id) {
        return candidates.get(id);
    }
}
