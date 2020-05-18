package SO.toDoList.model.entity;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Proxy(lazy=false)
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
