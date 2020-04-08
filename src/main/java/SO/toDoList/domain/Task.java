package SO.toDoList.domain;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotEmpty
    private String naam;

    private LocalDateTime dateAndTimeOfBeheading;

    @NotEmpty
    private String beschrijving;

    public LocalDateTime getDateAndTimeOfBeheading() {
        return dateAndTimeOfBeheading;
    }

    public void setDateAndTimeOfBeheading(LocalDateTime dateAndTimeOfBeheading) {
        this.dateAndTimeOfBeheading = dateAndTimeOfBeheading;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setBeschrijving(String beschrijving){
        this.beschrijving = beschrijving;
    }

    public String getNaam() {
        return naam;
    }

    public String getBeschrijving(){
        return this.beschrijving;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
