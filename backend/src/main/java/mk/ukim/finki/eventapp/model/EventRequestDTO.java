package mk.ukim.finki.eventapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import mk.ukim.finki.eventapp.model.enumerations.Status;
import mk.ukim.finki.eventapp.model.enumerations.Type;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventRequestDTO {
    private String name;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;
    private Status status;
    private String type;
    private Long organizerId;
}

