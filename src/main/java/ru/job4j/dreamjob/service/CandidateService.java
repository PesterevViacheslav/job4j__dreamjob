package ru.job4j.dreamjob.service;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.store.CandidateStore;
import java.util.Collection;
/**
 * Class CandidateService - Сервис работы с кандидатами. Решение задач уровня Middle.
 * 3.2. Web  Тема : 3.2.4. Архитектура Web приложений
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 01.10.2022
 * @version 1
 */
@Service
@ThreadSafe
public class CandidateService {
    private final CandidateStore store;
    private final CityService cityService;
    public CandidateService(CandidateStore store, CityService cityService) {
        this.store = store;
        this.cityService = cityService;
    }
    public Collection<Candidate> findAll() {
        Collection<Candidate> candidates = store.findAll();
        candidates.forEach(
                candidate -> candidate.setCity(
                        cityService.findById(candidate.getCity().getId())
                )
        );
        return candidates;
    }
    public void update(Candidate candidate) {
        store.update(candidate);
    }
    public Candidate findById(int id) {
        return store.findById(id);
    }
    public void add(Candidate candidate) {
        store.add(candidate);
    }
}
