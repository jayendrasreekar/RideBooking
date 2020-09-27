package system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import ride.DriverRide;
import ride.Ride;
import ride.RiderRide;
import users.Driver;
import users.Rider;

public class Availability {


    List<Driver> availableDrivers;
    List<Driver> availablePoolDrivers;

    List<Driver> driversOnRide;

    Availability(){
        availableDrivers = new ArrayList<>();
        availablePoolDrivers = new ArrayList<>();
        driversOnRide = new ArrayList<>();

    }

    public boolean doDriverLogin(Driver driver){
        driver.setAvailableStatus(true);
        availableDrivers.add(driver);
        return true;
    }

    public boolean doDriverLogout(Driver driver){
        driver.setAvailableStatus(false);
        availableDrivers.remove(driver);
        return true;
    }

    public RiderRide matchRiderToDriver(Rider rider, int duration_in_minutes){
        Driver assigned = null;
        for(Driver d : availableDrivers){
            if(d.getRating() <= rider.getRating()){
                assigned = d;
                break;
            }
        }

        if(assigned == null){
            assigned = availableDrivers.get(0);
        }

        availableDrivers.remove(assigned);

        RiderRide ride =  new RiderRide(Ride.status.CONFIRMED,assigned.getName(),assigned.getPhone(),assigned.getCarId());
        DriverRide dride = new DriverRide(Ride.status.CONFIRMED,false,assigned.getCarCapacity());

        ArrayList<Rider> l = new ArrayList<>();
        l.add(rider);
        dride.setRiders(l);
        assigned.getRides().add(dride);
        return ride;
    }

    public RiderRide matchRiderToPoolDriver(Rider rider, int duration_in_minutes){
        Driver assigned = null;

        if(availablePoolDrivers.size() == 0){
            for(Driver d: availableDrivers){
                if(d.getRating() <= rider.getRating()){
                    assigned = d;
                    break;
                }
            }
            if(assigned == null){
                assigned = availableDrivers.get(0);
            }

            availablePoolDrivers.add(assigned);
            availableDrivers.remove(assigned);

        }
        else {
            for (Driver d : availablePoolDrivers) {
                if (d.getRating() <= rider.getRating()) {
                    assigned = d;
                    break;
                }
            }
            if(assigned == null){
                availablePoolDrivers.get(0);
            }
        }

        if(assigned == null){
            assigned = availablePoolDrivers.get(0);
        }


        RiderRide ride =  new RiderRide(Ride.status.CONFIRMED,assigned.getName(),assigned.getPhone(),assigned.getCarId());

        if(assigned.getRides().get(assigned.getRides().size()-1).getStatus()==Ride.status.ENDED || assigned.getRides().get(assigned.getRides().size()-1).getStatus()==Ride.status.ENDED){
           DriverRide dride = new DriverRide(Ride.status.CONFIRMED,false,assigned.getCarCapacity());
         }else{
            assigned.getRides().get(assigned.getRides().size()-1).setCurrCapacity(assigned.getRides().get(assigned.getRides().size()-1).getCurrCapacity()+1);
        }
        if(assigned.getRides().get(assigned.getRides().size()-1).getCurrCapacity() == assigned.getCarCapacity()) {
            availablePoolDrivers.remove(assigned);
        }

        return ride;
    }


    public void startRide(Driver d){

        DriverRide dr = d.getRides().get(d.getRides().size()-1);
        dr.setStartTime(new Date());
        dr.setStatus(Ride.status.ONRIDE);
        Rider r = dr.getRiders().get(0);
        r.getRides().get(r.getRides().size()-1).setStartTime(new Date());
        r.getRides().get(r.getRides().size()-1).setStatus(Ride.status.ONRIDE);
    }


    public void endRide(Driver d){
        DriverRide dr = d.getRides().get(d.getRides().size()-1);
        dr.setEndtime(new Date());
        dr.setStatus(Ride.status.ENDED);
        Rider r = dr.getRiders().get(0);
        r.getRides().get(r.getRides().size()-1).setEndTime(new Date());
        r.getRides().get(r.getRides().size()-1).setStatus(Ride.status.ENDED);
    }


}
