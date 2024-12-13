/*package mk.ukim.finki.eventapp.repository.impl;

import mk.ukim.finki.eventapp.model.Rate;
import org.springframework.stereotype.Repository;


import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryRateRepository {
    private final Map<Long, Rate> rateMap = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    public List<Rate> findAll() {
        return new ArrayList<>(rateMap.values());
    }

    public Optional<Rate> findById(Long id) {
        return Optional.ofNullable(rateMap.get(id));
    }

    public Rate save(Rate rate) {
        if (rate.getId() == null) {
            rate.setId(idGenerator.incrementAndGet());
        }
        rateMap.put(rate.getId(), rate);
        return rate;
    }

    public void deleteById(Long id) {
        rateMap.remove(id);
    }

    public List<Rate> findByEventId(Long eventId) {
        List<Rate> rates = new ArrayList<>();
        for (Rate rate : rateMap.values()) {
            if (rate.getEvent() != null && rate.getEvent().getId().equals(eventId)) {
                rates.add(rate);
            }
        }
        return rates;
    }

    public List<Rate> findByUserId(Long userId) {
        List<Rate> rates = new ArrayList<>();
        for (Rate rate : rateMap.values()) {
            if (rate.getUser() != null && rate.getUser().getId().equals(userId)) {
                rates.add(rate);
            }
        }
        return rates;
    }

    public void deleteAll() {
        rateMap.clear();
    }
}
*/