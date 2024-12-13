/*package mk.ukim.finki.eventapp.service.impl;

import mk.ukim.finki.eventapp.model.Rate;
import mk.ukim.finki.eventapp.repository.jpa.RateRepository;
import mk.ukim.finki.eventapp.service.RateService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RateServiceImpl implements RateService {

    private final RateRepository rateRepository;

    // Конструктор за вбризгување на RateRepository
    public RateServiceImpl(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    @Override
    public List<Rate> getAllRates() {
        // Добивање на сите рејтинзи од базата
        return rateRepository.findAll();
    }

    @Override
    public Rate addRate(Rate rate) {
        // Чување на нов рејтинг
        return rateRepository.save(rate);
    }

    @Override
    public Rate updateRate(Rate rate) {
        // Проверка дали рејтингот постои и ажурирање
        return rateRepository.findById(rate.getId())
                .map(existingRate -> {
                    existingRate.setValue(rate.getValue()); // Пример за ажурирање на вредност
                    existingRate.setEventId(rate.getEventId());
                    existingRate.setUserId(rate.getUserId());
                    return rateRepository.save(existingRate);
                })
                .orElseThrow(() -> new IllegalArgumentException("Rate not found with id: " + rate.getId()));
    }

    @Override
    public void deleteRate(Long id) {
        // Проверка и бришење
        if (rateRepository.existsById(id)) {
            rateRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Rate not found with id: " + id);
        }
    }

    @Override
    public List<Rate> findRatesByEventId(Long eventId) {
        // Наоѓање на рејтинзи според Event ID
        return rateRepository.findByEventId(eventId);
    }

    @Override
    public List<Rate> findRatesByUserId(Long userId) {
        // Наоѓање на рејтинзи според User ID
        return rateRepository.findByUserId(userId);
    }
}
*/