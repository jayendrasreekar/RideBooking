package users;

import java.util.ArrayList;
import java.util.List;

import helper.Hasher;
import ride.DriverRide;
import ride.Ride;
import system.Availability;
import wallet.DriverWallet;
import wallet.Wallet;

public class Driver implements Actor {
    private String           id;
    private String           name;
    private String           phone;
    private double           rating;
    private int              age;
    private List<DriverRide> rides;
    private String           carId;
    private int              carCapacity;
    private Wallet           wallet;
    private Availability     availability;
    private boolean          availableStatus;
    private boolean          onRide;

    public boolean isOnRide() {
        return onRide;
    }

    public void setOnRide(boolean onRide) {
        this.onRide = onRide;
    }

    public int getCarCapacity() {
        return carCapacity;
    }

    public void setCarCapacity(int carCapacity) {
        this.carCapacity = carCapacity;
    }

    public Driver(String name, String phone, double rating, int age, String carId, int carCapacity, Availability availability){
        this.id = Hasher.getHash(name,phone,String.valueOf(age));
        this.name = name;
        this.phone = phone;
        this.rating = rating;
        this.age = age;
        this.carId = carId;
        this.carCapacity = carCapacity;
        this.rides = new ArrayList<>();
        this.wallet = new DriverWallet();
        this.availability = availability;
        this.availableStatus = false;
        this.onRide = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
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

    public List<DriverRide> getRides() {
        return rides;
    }


    public boolean isAvailableStatus() {
        return availableStatus;
    }

    public void setAvailableStatus(boolean availableStatus) {
        this.availableStatus = availableStatus;
    }




    public void login(){
        availableStatus = availability.doDriverLogin(this);
    }

    public void logoff(){
        availability.doDriverLogout(this);
    }



    public void startRide(){
        availability.startRide(this);
    }


    public void endRide(){
        availability.endRide(this);
    }
}
