package SO.toDoList.model.dto;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

public class TaskDTO {
    @NotEmpty
    private String naam;

    @NotEmpty
    private String beschrijving;

    private LocalDateTime dateAndTimeOfBeheading;
    private int id;

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

    public LocalDateTime getDateAndTimeOfBeheading() {
        return dateAndTimeOfBeheading;
    }

    public void setDateAndTimeOfBeheading(LocalDateTime dateAndTimeOfBeheading) {
        this.dateAndTimeOfBeheading = dateAndTimeOfBeheading;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
