package system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import ride.DriverRide;
import ride.Ride;
import ride.RiderRide;
import users.Driver;
import users.Rider;

public class MainClass {


    private static Availability  availability;
    private static  List<Driver> drivers;
    private static List<Rider>   riders;

    private static HashMap<String,Boolean> phoneNumbers;


    static void  driverWrapper(String name, String phone, double rating, int age, String carId, int carCapacity, Availability availability){
        phoneNumbers.put(phone,true);
        Driver d1 = new Driver(name, phone,rating,age,carId,carCapacity,availability);
        availability.doDriverLogin(d1);
        drivers.add(d1);
    }

    static void riderWrapper(String name, String phone, double rating, int age,Availability availability){
        phoneNumbers.put(phone,true);
        riders.add(new Rider(name,phone,rating,age,availability));
    }



    static void showDriverMenu(Scanner sc,String phone){
        Driver d = null;
        for(Driver t : drivers){
            if(t.getPhone().equals(phone)){
                d = t;
            }
        }

        if(d == null){
            System.out.println("Error fetching your details.. Try again");
            return;
        }




        Ride.status status = d.getRides().get(d.getRides().size()-1).getStatus();

        switch(status){
            case ONRIDE:  System.out.println("\n1. Want to  complete the Current Ride");
                          int a = Integer.parseInt(sc.next());
                          if(a == 1){
                              d.endRide();
                          }
                          break;
            case CONFIRMED: System.out.println("\n 1.Start the Ride  2.LogOff");
                            int b = Integer.parseInt(sc.next());
                            if(b == 1){
                                d.startRide();
                            }else if(b == 2){
                                d.logoff();
                            }
                           break;
            case ENDED:    System.out.println("\n 1. Logoff.");
                             int c = Integer.parseInt(sc.next());
                             if(c ==1 ){
                                 d.logoff();
                            }
                           break;
            case CANCELLED: System.out.println("\n 1. Logoff");
                              int e = Integer.parseInt(sc.next());
                              if(e ==1 ){
                                 d.logoff();
                              }
                           break;
        }
    }

    static void bookRide(Scanner sc,Rider rider){
        System.out.println("What you want to do  \n 1. Get a ride   2. Get a pool Ride");
        int choice  = sc.nextInt();
        switch(choice){
            case 1: RiderRide ride = rider.requestRide();
                System.out.println("Your ride details are : "+ "   Driver : "+ride.getDriverName()+ "   CarNumber: "+ride.getCarId());
                break;
            case 2: rider.requestPoolRide();
                break;
            default: break;

        }
    }

    static void showRiderMenu(Scanner sc, String phone){
        Rider rider = null;
        for(Rider t : riders){
            if(t.getPhone().equals(phone)){
                rider = t;
            }
        }
        if(rider == null){
            System.out.println("\nError fetching your details.. Try again");
        }
        List<RiderRide> users_Rides = rider.getRides();

        if(users_Rides.size() == 0){
            bookRide(sc,rider);
        }else{
            RiderRide fetch = users_Rides.get(users_Rides.size()-1);
            Ride.status status = fetch.getStatus();

            if(!status.equals(Ride.status.ENDED) && !status.equals(Ride.status.CANCELLED)){
                System.out.println("\n  Your ride details..");
                System.out.println("Your Latest ride in on status  "+status);
                if(status.equals(Ride.status.ONRIDE)){
                    System.out.println("Your ride details are : StartTime "+ fetch.getStartTime() + "   Driver : "+fetch.getDriverName()+ "   CarNumber: "+fetch.getCarId());
                }else if(status.equals(Ride.status.CONFIRMED)){
                    System.out.println("Waiting for the Driver confirmation/Arrival   DriverName :  "+fetch.getDriverName()+ "   CarNumber: "+fetch.getCarId());
                }
            }else{
                if(status.equals(Ride.status.ENDED)){
                    System.out.println("Your last ended ride details are : StartTime "+ fetch.getStartTime() + " EndTime : "+fetch.getEndTime()+ "   Driver : "+fetch.getDriverName()+ "   CarNumber: "+fetch.getCarId());
                }
                bookRide(sc,rider);
            }
        }
    }

    static void registerDriver(Scanner sc, Availability availability){
       try {
           System.out.println("\n\n");
           System.out.println("Enter the following Details seperated by comma\n");
           System.out.println("1.Name,2.phone,3.age,4.carId,5.carCapacity\n");
           String[] input = sc.next().trim().split(",");
           String name = input[0];
           String phone = input[1];
           int age = Integer.parseInt(input[2]);
           String carId = input[3];
           int carCapacity = Integer.parseInt(input[4]);

           if (phoneNumbers.containsKey(phone)) {
               System.out.println("\nRegistration failed, The Phone number is already registered. Try with a new One\n");
               return;
           }

           Driver driver = new Driver(name,phone,4.0,age,carId,carCapacity,availability);
           phoneNumbers.put(phone,true);
           drivers.add(driver);

        }catch(NumberFormatException ne){
           System.out.println("Exception error"+ne);
        }catch (Exception e){
           System.out.println("Exception occured"+e);
        }
    }


    static void registerRider(Scanner sc, Availability availability){

        try {
            System.out.println("\n\n");
            System.out.println("Enter the following Details seperated by comma\n");
            System.out.println("1.Name,2.phone,3.age");

            String[] input = sc.nextLine().split(",");
            String name = input[0];
            String phone = input[1];
            int age = Integer.parseInt(input[2]);

            if (!phoneNumbers.containsKey(phone)) {
                System.out.println("Registration failed, The Phone number is already registered. Try with a new One");
                return;
            }

            Rider rider = new Rider(name, phone, 4.0, age, availability);
            phoneNumbers.put(phone, true);
            riders.add(rider);
        }catch (NumberFormatException ne){
            System.out.println("Exception at"+ne);
        }catch (Exception e){
            System.out.println("Exception here"+e);
        }
    }




    static void preload(){
        driverWrapper("D1","90000000001",4.0,27,"KA10001",4,availability);
        driverWrapper("D2","90000000002",3.0,26,"KA10002",4,availability);
        driverWrapper("D3","90000000003",3.5,25,"KA10003",6,availability);
        driverWrapper("D4","90000000004",2.0,26,"KA10004",4,availability);
        driverWrapper("D5","90000000005",2.6,28,"KA10005",6,availability);
        driverWrapper("D6","90000000006",3.7,24,"KA10006",4,availability);
        driverWrapper("D7","90000000007",4.2,26,"KA10007",6,availability);
        driverWrapper("D8","90000000008",4.4,25,"KA10008",4,availability);
        driverWrapper("D9","90000000009",3.4,24,"KA10009",4,availability);


         riderWrapper("R1","8000000001",3.5,48,availability);
         riderWrapper("R1","8000000002",4.5,48,availability);
         riderWrapper("R1","8000000003",2.8,48,availability);
         riderWrapper("R1","8000000004",3.8,48,availability);
         riderWrapper("R1","8000000005",3.2,48,availability);
         riderWrapper("R1","8000000006",4.7,48,availability);
         riderWrapper("R1","8000000007",3.1,48,availability);
         riderWrapper("R1","8000000008",2.5,48,availability);





    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        availability = new Availability();
        drivers = new ArrayList<>();
        riders = new ArrayList<>();
        phoneNumbers = new HashMap<String,Boolean>();

        preload();

        while (true){
          System.out.println("\n\n=== Enter as  1. Driver 2. Rider   3. Register as Driver 4. Register as Rider ===");
          int loginNumber = Integer.parseInt(sc.next());

          switch (loginNumber) {
            case 1: System.out.println("\nEnter your phone number");
                    String phone1 = sc.next();
                    if(!phoneNumbers.containsKey(phone1)) {
                        System.out.println("Phone Number doesnt exist. Please try login with correct credentails");
                        break;
                    }
                    showDriverMenu(sc,phone1);
                    break;
            case 2: System.out.println("\nEnter your phone number");
                    String phone2 = sc.next();
                    if(!phoneNumbers.containsKey(phone2)){
                        System.out.println("Phone Number doesnt exist. Please try login with correct credentails");
                        break;
                    }
                    showRiderMenu(sc,phone2);
                    break;
            case 3: registerDriver(sc,availability);
                    break;
            case 4: registerRider(sc,availability);
                    break;
            default:
                System.out.println("Wrong Choice. Try Again");
          }
        }


    }
}
