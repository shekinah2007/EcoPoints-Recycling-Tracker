import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

public class EcoPointsManager {
    HashMap<Integer, Household> households = new HashMap<>();
    HashMap<Integer, ArrayList<RecycleEvent>> recyclingEvents = new HashMap<>();

    private final String HOUSEHOLD_FILE = "household_file.txt";
    private final String EVENT_FILE = "event_file.txt";

    private int houseHoldId = 1;

    public void registerHousehold(String name, String address) {
        Household household = new Household(houseHoldId, name, address, LocalDate.now());
        households.put(houseHoldId, household);
        saveData();
        System.out.println("Househould registered ID:" + household);
        houseHoldId++;
    }

    public void saveData() {
        try (BufferedWriter householdWriter = Files.newBufferedWriter(Paths.get(HOUSEHOLD_FILE));
             BufferedWriter eventWriter = Files.newBufferedWriter(Paths.get(EVENT_FILE));
        ) {
            for (Household household : households.values()) {
                householdWriter.write(household.getId() + "|" + household.getName()
                        + "|" + household.getAddress() + "|" + household.getJoiningDate() + "|" +
                        household.getTotalPoints());
                householdWriter.newLine();
            }
            for (Map.Entry<Integer, ArrayList<RecycleEvent>> entry : recyclingEvents.entrySet()) {
                for (RecycleEvent e : entry.getValue()) {
                    eventWriter.write(entry.getKey() + "|" + e.getMaterialType() + "|" +
                            e.getWeight() + "|" + e.getRecyclingDate() + "|" + e.getEcoPoints());
                    eventWriter.newLine();
                }
            }
            System.out.println("Data saved successfully");
        } catch (IOException e) {
            System.out.println("File saving error" + e.getMessage());
        }
    }
    public void logRecycleEvents(int houseHoldId,String materialType,double weight){
        Household household=households.get(houseHoldId);
        if(household==null)
            throw new IllegalArgumentException("Household not found");
        if(weight<=0)
            throw new IllegalArgumentException("weight must be positive");

        materialType=materialType.toLowerCase().trim();
        if(!Arrays.asList("plastic","glass","metal","paper").contains(materialType))
            throw new IllegalArgumentException("invalid material type");

        double points=10*weight;
        RecycleEvent recycleEvent=new RecycleEvent(materialType,weight,LocalDate.now(),points);
        recyclingEvents.get(houseHoldId).add(recycleEvent);
        saveData();
        household.addPoints(points);
        System.out.println("Recycle Event Recorded");
        System.out.println("Total points: "+points);
    }
    public void displayAllHouseholds(){
        if(households.isEmpty()){
            System.out.println("No households registered");
            return;
        }
           for(Household h: households.values()){
            System.out.println("\nID: "+h.getId());
            System.out.println("Name: "+h.getName());
            System.out.println("Address: "+h.getAddress());
            System.out.println("Joining date: "+h.getJoiningDate());
            System.out.println("Total points: "+h.getTotalPoints());
        }
    }
    public void displayAllRecycleEvents(int houseHoldId){
        if(!households.containsKey(houseHoldId)){
            System.out.println("Household not found");
            return;
        }
        ArrayList<RecycleEvent>  events=recyclingEvents.get(houseHoldId);
        if(events.isEmpty()){
            System.out.println("No recycle events found");
            return;
        }
        for(RecycleEvent r:events){
            System.out.println("\nMaterial Type: "+r.getMaterialType());
            System.out.println("Weight: "+r.getWeight());
            System.out.println("Date: "+r.getRecyclingDate());
            System.out.println("Ecopoints: "+r.getEcoPoints());
        }
    }
    public void displayTotalWeight(int houseHoldId){
        if(!households.containsKey(houseHoldId)){
            System.out.println("Household not found");
            return;
        }
        ArrayList<RecycleEvent> events=recyclingEvents.get(houseHoldId);
        if(events.isEmpty()){
            System.out.println("No recycle events found");
            return;
        }
        double totalWeight=0;

        for(RecycleEvent r:events){
            totalWeight+=r.getWeight();
        }
        System.out.println("Total Weight: "+totalWeight);
    }
    public void displayTotalEcoPoints(int houseHoldId){
        Household household = households.get(houseHoldId);

        if (household == null) {
            throw new IllegalArgumentException("Household not found!");
        }
        System.out.println("Total Ecopoints: "+household.getTotalPoints());
    }
    public void topHousehold(){
        if(households.isEmpty()){
            System.out.println("No households registered");
            return;
        }
        Household top=households.values()
                .stream()
                .max(Comparator.comparingDouble(Household::getTotalPoints))
                .orElse(null);

        if(top==null){
            System.out.println("No households registered");
            return;
        }
        System.out.println("Top Household: "+top.getName());
        System.out.println("Household ID: "+top.getId());
        System.out.println("Total Points: "+top.getTotalPoints());
    }
    public void displayCommunityWeight(){
        double total=0;
        for(ArrayList<RecycleEvent> events:recyclingEvents.values()){
            for(RecycleEvent r:events){
                total+=r.getWeight();
            }
        }
        System.out.println("Total community weight: "+total);
    }
    public void loadData(){
       try{
           Path householdPath=Paths.get(HOUSEHOLD_FILE);
           Path eventPath=Paths.get(EVENT_FILE);

           if(Files.exists(householdPath)){
               for(String line:Files.readAllLines(householdPath)){
                   if(line.isBlank())
                       continue;

                   String[] data=line.split("\\|", -1);
                   int id = Integer.parseInt(data[0]);
                   Household h=new Household(
                           id,
                           (data[1]),
                           (data[2]),
                           LocalDate.parse(data[3])
                   );
                   h.setTotalPoints(Integer.parseInt(data[4]));
                   households.put(id,h);
                   recyclingEvents.put(id,new ArrayList<>());
                   houseHoldId=Math.max(houseHoldId,id+1);
               }
           }
           if(Files.exists(eventPath)){
               for(String line:Files.readAllLines(eventPath)){
                   if(line.isBlank())
                       continue;

                   String[] data = line.split("\\|", -1);

                   int ID=Integer.parseInt(data[0]);
                   if(!households.containsKey(ID))
                       continue;

                   RecycleEvent event = new RecycleEvent(
                           data[1],
                           Double.parseDouble(data[2]),
                           LocalDate.parse(data[3]),
                           Double.parseDouble(data[4])
                   );

                   recyclingEvents.get(ID).add(event);
               }
           }
           System.out.println("Data Loaded successfully");
       }catch(IOException | RuntimeException e){
           System.out.println("File loading error "+e.getMessage());
       }
    }
}