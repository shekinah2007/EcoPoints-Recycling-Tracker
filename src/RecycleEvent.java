import java.time.LocalDate;

public class RecycleEvent {
    private String materialType;
    private double weight;
    private LocalDate recyclingDate;
    private double ecoPoints;

    public RecycleEvent(String materialType,double weight,LocalDate recyclingDate,double ecoPoints){
        this.materialType=materialType;
        this.weight=weight;
        this.recyclingDate=recyclingDate;
        this.ecoPoints=ecoPoints;
    }
    public String getMaterialType(){
        return materialType;
    }
    public double getWeight(){
        return weight;
    }
    public LocalDate getRecyclingDate(){
        return recyclingDate;
    }
    public double getEcoPoints(){
        return ecoPoints;
    }
}
