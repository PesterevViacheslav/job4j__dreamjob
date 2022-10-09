package ru.job4j.dreamjob.store;
import ru.job4j.dreamjob.model.Candidate;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Date;
/**
 * Class CandidateStore - Хранилище кандидатов. Решение задач уровня Middle.
 * 3.2. Web Тема : 3.2.2. Html, Bootstrap, Thymeleaf.
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 01.10.2022
 * @version 1
 */
public class CandidateStore {
    private static final CandidateStore INST = new CandidateStore();
    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();
    private CandidateStore() {
        candidates.put(0, new Candidate(0, "Candidate 1", "dsc1"));
        candidates.put(1, new Candidate(1, "Candidate 2", "dsc2"));
        candidates.put(2, new Candidate(2, "Candidate 3", "dsc3"));
    }
    public static CandidateStore instOf() {
        return INST;
    }
    public Collection<Candidate> findAll() {
        return candidates.values();
    }

    public void update(Candidate candidate) {
        candidates.put(candidate.getId(), candidate);
    }
    public Object findById(int id) {
        return candidates.get(id);
    }
}
