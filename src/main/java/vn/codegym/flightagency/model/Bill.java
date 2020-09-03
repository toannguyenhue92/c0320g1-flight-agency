package vn.codegym.flightagency.model;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Bill {

    private Long id;

    private LocalDateTime dateCreated;

//    private Deal deal;

    private UUID billCode;

    public Bill() {
        this.billCode = UUID.randomUUID();
    }
}

