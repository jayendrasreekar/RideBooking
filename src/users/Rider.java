package users;

import java.util.ArrayList;
import java.util.List;

import ride.Ride;
import ride.RiderRide;
import system.Availability;
import wallet.DriverWallet;
import wallet.UserWallet;
import wallet.Wallet;

public class Rider {
    String          name;
    String          phone;
    double          rating;
    int             age;
    List<RiderRide> rides;
    int             carCapacity;
    Wallet          wallet;
    Availability    availability;
    boolean         isOnRide;



    public Rider(String name, String phone, double rating, int age,Availability availability){
        this.name = name;
        this.phone = phone;
        this.rating = rating;
        this.age = age;
        this.carCapacity = carCapacity;
        this.rides = new ArrayList<>();
        this.wallet = new UserWallet();
        this.availability =availability;
        this.isOnRide = false;
    }

    public boolean isOnRide() {
        return isOnRide;
    }

    public void setOnRide(boolean onRide) {
        isOnRide = onRide;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<RiderRide> getRides() {
        return rides;
    }

    public void setRides(List<RiderRide> rides) {
        this.rides = rides;
    }

    public int getCarCapacity() {
        return carCapacity;
    }

    public void setCarCapacity(int carCapacity) {
        this.carCapacity = carCapacity;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    public RiderRide requestRide(){
        RiderRide ride = availability.matchRiderToDriver(this,30);
        rides.add(ride);
        return ride;
    }


    public RiderRide requestPoolRide(){
        RiderRide ride = availability.matchRiderToPoolDriver(this,30);
        return ride;
    }

//    cancelRide(RiderRide ride){
//
//    }
}
