import java.time.LocalDate;

public class Household {
    private final int id;
    private String name;
    private String address;
    private LocalDate joiningDate;
    private int totalPoints;

    public Household(int id,String name,String address,LocalDate joiningDate){
        this.id=id;
        this.name=name;
        this.address=address;
        this.joiningDate=joiningDate;
        this.totalPoints=0;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getAddress(){
        return address;
    }
    public LocalDate getJoiningDate(){
        return joiningDate;
    }
    public int getTotalPoints(){
        return totalPoints;
    }
    public void addPoints(double points){
        totalPoints+=points;
    }
    public void setTotalPoints(int totalPoints){
        this.totalPoints=totalPoints;
    }
}
