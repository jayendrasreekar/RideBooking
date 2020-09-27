package ride;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import users.Rider;

public class DriverRide {
    private Ride.status status;
    private Date        startTime;
    private Date        endtime;
    private boolean     isPool;
    private int         currCapacity;
    private List<Rider> riders;


    public DriverRide(Ride.status status,boolean isPool,int currCapacity)
    {
        this.status = status;
        this.startTime = null;
        this.endtime = null;
        this.isPool = isPool;
        this.currCapacity = currCapacity;
        riders = new ArrayList<>();
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

    public List<Rider> getRiders() {
        return riders;
    }

    public void setRiders(List<Rider> riders) {
        this.riders = riders;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public boolean isPool() {
        return isPool;
    }

    public void setPool(boolean pool) {
        isPool = pool;
    }

    public int getCurrCapacity() {
        return currCapacity;
    }

    public void setCurrCapacity(int currCapacity) {
        this.currCapacity = currCapacity;
    }
}
