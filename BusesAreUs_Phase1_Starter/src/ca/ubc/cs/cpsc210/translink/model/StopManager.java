package ca.ubc.cs.cpsc210.translink.model;

import ca.ubc.cs.cpsc210.translink.model.exception.StopException;
import ca.ubc.cs.cpsc210.translink.util.LatLon;
import ca.ubc.cs.cpsc210.translink.util.SphericalGeometry;

import java.util.*;

/**
 * Manages all bus stops.
 *
 * Singleton pattern applied to ensure only a single instance of this class that
 * is globally accessible throughout application.
 */
// TODO: Task 2: Complete all the methods of this class
// completed all

public class StopManager implements Iterable<Stop> {
    public static final int RADIUS = 10000;
    private static StopManager instance;
    // Use this field to hold all of the stops.
    // Do not change this field or its type, as the iterator method depends on it
    private Map<Integer, Stop> stopMap;
    private Stop selectedStop;

    /**
     * Constructs stop manager with empty collection of stops and null as the selected stop
     */
    private StopManager() {
        //stops = new ArrayList<>();
        selectedStop = null;
        stopMap = new HashMap<>();
    }

    /**
     * Gets one and only instance of this class
     *
     * @return  instance of class
     */
    public static StopManager getInstance() {
        // Do not modify the implementation of this method!
        if(instance == null) {
            instance = new StopManager();
        }

        return instance;
    }

    public Stop getSelected() {
        return selectedStop;
    }

    /**
     * Get stop with given number, creating it and adding it to the collection of all stops if necessary.
     * If it is necessary to create a new stop, then provide it with an empty string as its name,
     * and a default location somewhere in the lower mainland as its location.
     *
     * In this case, the correct name and location of the stop will be provided later
     *
     * @param number  the number of this stop
     *
     * @return  stop with given number
     */
    //TODO: make locn somewhere in the lower mainland
    // TODO: keep in mind instead of contains you might need for-each & equals
    public Stop getStopWithNumber(int number) {
        Stop newStop = new Stop(number, "", null );
        if(!stopMap.containsValue(newStop))
            stopMap.put(number, newStop);

        return newStop;
    }

    /**
     * Get stop with given number, creating it and adding it to the collection of all stops if necessary,
     * using the given name and location
     *
     * @param number  the number of this stop
     * @param name   the name of this stop
     * @param locn   the location of this stop
     *
     * @return  stop with given number
     */
    public Stop getStopWithNumber(int number, String name, LatLon locn) {
        Stop newStop = new Stop(number, name, locn );
        if(!stopMap.containsValue(newStop))
            stopMap.put(number, newStop);

        return newStop;
    }

    /**
     * Set the stop selected by user
     *
     * @param selected   stop selected by user
     * @throws StopException when stop manager doesn't contain selected stop
     */
    public void setSelected(Stop selected) throws StopException {
        if(stopMap.containsValue(selected))
            selectedStop = selected;
        throw new StopException("No such stop: " + selected.getNumber() + " " + selected.getName());
    }

    /**
     * Clear selected stop (selected stop is null)
     */
    public void clearSelectedStop() {
        selectedStop = null;
    }

    /**
     * Get number of stops managed
     *
     * @return  number of stops added to manager
     */
    public int getNumStops() {
        return stopMap.size();
    }

    /**
     * Remove all stops from stop manager
     */
    public void clearStops() {
        stopMap.clear();
    }

    /**
     * Find nearest stop to given point.  Returns null if no stop is closer than RADIUS metres.
     *
     * @param pt  point to which nearest stop is sought
     * @return    stop closest to pt but less than RADIUS away; null if no stop is within RADIUS metres of pt
     */
    public Stop findNearestTo(LatLon pt) {
        Stop toReturn = null;
        double distanceLeast = RADIUS;
        for (Stop stop: stopMap.values()) {
        double distance = SphericalGeometry.distanceBetween(pt,stop.getLocn() );
        if(distance < RADIUS)
            if(distance < distanceLeast){
                 distance = distanceLeast;
                 toReturn = stop;
            }
        }
        return toReturn;
    }

    @Override
    public Iterator<Stop> iterator() {
        // Do not modify the implementation of this method!
        return stopMap.values().iterator();
    }
}
