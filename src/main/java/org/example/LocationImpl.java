package org.example;

public class LocationImpl implements Location{
    private String location;

    public LocationImpl() {}
    public LocationImpl(String newLocation) {
        location = newLocation;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public void setLocation(String location) {
        this.location = location;
    }

    public String toString() {
        return location;
    }
}
