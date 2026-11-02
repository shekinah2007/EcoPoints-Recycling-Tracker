import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        EcoPointsManager manager=new EcoPointsManager();

        manager.loadData();

        while(true){
            System.out.println("Choose an option");
            System.out.println("1. Register Household");
            System.out.println("2. Log Recycling Event");
            System.out.println("3. Display All Households");
            System.out.println("4. Display Recycling Events");
            System.out.println("5. Display Household Total Weight");
            System.out.println("6. Display Household Total Points");
            System.out.println("7. Household with Highest Points");
            System.out.println("8. Total Community Recycling Weight");
            System.out.println("9. Exit");

            try{
                int choice=sc.nextInt();
                sc.nextLine();

                switch(choice) {
                    case 1:
                        System.out.print("Enter the household name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter the Address");
                        String address = sc.nextLine();
                        manager.registerHousehold(name, address);
                        break;

                    case 2:
                        System.out.print("Enter household id:");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter material type (plastic/glass/metal/paper");
                        String material = sc.nextLine();
                        System.out.print("Enter weight in kg");
                        double weight = sc.nextDouble();

                        manager.logRecycleEvents(id, material, weight);
                        break;

                    case 3:
                        manager.displayAllHouseholds();
                        break;
                    case 4:
                        System.out.print("Enter the household id");
                        int rId=sc.nextInt();
                        manager.displayAllRecycleEvents(rId);
                        break;
                    case 5:
                        System.out.print("Enter the household id");
                        int RID= sc.nextInt();
                        manager.displayTotalWeight(RID);
                        break;
                    case 6:
                        System.out.print("Enter the household id");
                        int ID= sc.nextInt();
                        manager.displayTotalEcoPoints(ID);
                        break;
                    case 7:
                        manager.topHousehold();
                        break;
                    case 8:
                        manager.displayCommunityWeight();
                        break;
                    case 9:
                        System.out.print("Exiting EcoPoints");
                        sc.close();
                        return;
                    default:
                        System.out.print("Invalid option");
                }
            }catch (NumberFormatException e){
                System.out.println("Enter a valid option");
            }
            catch(IllegalArgumentException e){
                System.out.println("Error: "+e.getMessage());
            }
        }
    }
}
