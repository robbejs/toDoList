package SO.toDoList.dto;

import SO.toDoList.domain.SubTask;
import SO.toDoList.domain.Task;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
