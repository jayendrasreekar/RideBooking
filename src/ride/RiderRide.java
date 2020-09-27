package ride;

import java.util.Date;

public class RiderRide implements Ride {
    private Ride.status status;
    private Date        startTime;
    private Date        endTime;
    private String      driverName;
    private String      driverPhone;
    private String carId;


    public RiderRide(Ride.status status, String driverName, String driverPhone, String carId) {
        this.status = status;
        this.startTime = null;
        this.endTime = null;
        this.driverName = driverName;
        this.driverPhone = driverPhone;
        this.carId = carId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public Ride.status getStatus() {
        return status;
    }

    public void setStatus(Ride.status status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endtime) {
        this.endTime = endtime;
    }
}
