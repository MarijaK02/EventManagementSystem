package mk.ukim.finki.eventapp.repository.jpa;

import mk.ukim.finki.eventapp.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {
    List<Rate> findByEventId(Long eventId); // Пребарување рејтинзи по настан
    List<Rate> findByUserId(Long userId);  // Пребарување рејтинзи по корисник
}