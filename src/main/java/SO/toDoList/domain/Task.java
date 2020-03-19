package SO.toDoList.domain;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Task {
    @NotEmpty
    private String naam;
    private LocalDateTime datum;
    @NotEmpty
    private String beschrijving;
    private int id;
    private ArrayList<SubTask> subTasks;

    public Task(){
         this.subTasks = new ArrayList<>();
    }

    public Task(String naam, LocalDateTime datum, String beschrijving){
        this.subTasks = new ArrayList<>();
        setNaam(naam);
        setDatum(datum);
        setBeschrijving(beschrijving);
    }

    public void addSubTask(SubTask subTask){
        this.subTasks.add(subTask);
    }

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setBeschrijving(String beschrijving){
        this.beschrijving = beschrijving;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public String getDatumString(){return this.datum.getYear() + "-" + this.datum.getMonth() + " " + this.datum.getDayOfMonth() + " " + this.datum.getHour() + ":" + this.datum.getMinute();}

    public String getNaam() {
        return naam;
    }

    public String getBeschrijving(){
        return this.beschrijving;
    }

    public ArrayList<SubTask> getSubTasks() {
        return subTasks;
    }

    public String toString(){
        return this.naam + ": due " + this.getDatumString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
