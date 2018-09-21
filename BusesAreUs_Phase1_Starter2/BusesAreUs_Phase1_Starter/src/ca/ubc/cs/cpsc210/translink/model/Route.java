package ca.ubc.cs.cpsc210.translink.model;

import java.util.*;
import java.util.List;

/**
 * Represents a bus route with a route number, name, list of stops, and list of RoutePatterns.
 * <p/>
 * Invariants:
 * - no duplicates in list of stops
 * - iterator iterates over stops in the order in which they were added to the route
 */

// TODO: Task 2: Implement all the methods in this class
    //completed all

public class Route implements Iterable<Stop> {
    private List<Stop> stops;
    private String number;
    private String name;
    private List<RoutePattern> routePatterns;

    /**
     * Constructs a route with given number.
     * Name is the empty string, List of route patterns is empty, List of stops is empty.
     *
     * @param number the route number
     */
    public Route(String number) {
        this.number = number;
        this.stops = new ArrayList<>();
        this.name = "";
        this.routePatterns = new ArrayList<>();
    }

    /**
     * Return the number of the route
     *
     * @return the route number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Set the name of the route
     * @param name  The name of the route
     */
    public void setName(String name) {
        this.name = name;

    }

    /**
     * Add the pattern to the route if it is not already there
     *
     * @param pattern
     */
    public void addPattern(RoutePattern pattern) {
        if(!routePatterns.contains(pattern))
        routePatterns.add(pattern);

    }

    /**
     * Add stop to route.
     *
     * @param stop the stop to add to this route
     */
    public void addStop(Stop stop) {
        if(!stops.contains(stop)) {
            stops.add(stop);
            stop.addRoute(this);
        }
    }

    /**
     * Remove stop from route
     *
     * @param stop the stop to remove from this route
     */
    public void removeStop(Stop stop) {
        if(stops.contains(stop)) {
            stops.remove(stop);
            stop.removeRoute(this);
        }
    }

    /**
     * Return all the stops in this route, in the order in which they were added
     *
     * @return      An unmodifiable list of all the stops
     */
    public List<Stop> getStops() {
        return Collections.unmodifiableList(stops);
    }

    /**
     * Determines if this route has a stop at a given stop
     *
     * @param stop the stop
     * @return true if route has a stop at given stop
     */
    public boolean hasStop(Stop stop) {
           if(stops.contains(stop))
               return true;
           return false;

    }

    /**
     * Two routes are equal if their numbers are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Route stops = (Route) o;

        return number != null ? number.equals(stops.number) : stops.number == null;
    }

    @Override
    public int hashCode() {
        return number != null ? number.hashCode() : 0;
    }

    /**
     * Two routes are equal if their numbers are equal.
     * Therefore hashCode only pays attention to number.
     */


    @Override
    public Iterator<Stop> iterator() {
        // Do not modify the implementation of this method!
        return stops.iterator();
    }

    /**
     * Return the name of this route
     *
     * @return      the name of the route
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Route " + getNumber();
    }

    /**
     * Return the pattern with the given name. If it does not exist, then create it and add it to the patterns.
     * In either case, update the destination and direction of the pattern.
     *
     * @param patternName       the name of the pattern
     * @param destination       the destination of the pattern
     * @param direction         the direction of the pattern
     * @return                  the pattern with the given name
     */
    public RoutePattern getPattern(String patternName, String destination, String direction) {

        for(RoutePattern rp : routePatterns){
            if(rp.getName().equals(patternName)){
                rp.setDestination(destination);
                rp.setDirection(direction);
                return rp;
            }
        }

        RoutePattern routePattern = new RoutePattern(patternName, destination, direction, this);
        routePatterns.add(routePattern);
        return routePattern;
    }

    /**
     * Return the pattern with the given name. If it does not exist, then create it and add it to the patterns
     * with empty strings as the destination and direction for the pattern.
     *
     * @param patternName       the name of the pattern
     * @return                  the pattern with the given name
     */
    public RoutePattern getPattern(String patternName) {

        for(RoutePattern rp : routePatterns){
            if(rp.getName().equals(patternName)){
                return rp;
            }
        }

        RoutePattern routePattern = new RoutePattern(patternName, "", "", this);
        routePatterns.add(routePattern);
        return routePattern;
    }

    /**
     * Return all the patterns for this route as a list
     *
     * @return      an unmodifiable list of the patterns for this route
     */
    public List<RoutePattern> getPatterns() {

        return Collections.unmodifiableList(routePatterns);
    }
}
