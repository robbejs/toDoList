package SO.toDoList.domain;

public class SubTask {
    private String naam;
    private String beschrijving;

    public SubTask(){

    }

    public SubTask(String naam, String beschrijving){
        setNaam(naam);
        setBeschrijving(beschrijving);
    }

    public String getNaam() {
        return naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }
}
