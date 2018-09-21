package ca.ubc.cs.cpsc210.translink.model;

import ca.ubc.cs.cpsc210.translink.util.LatLon;

import java.util.*;

/**
 * Represents a bus stop with an number, name, location (lat/lon)
 * set of routes which stop at this stop and a list of arrivals.
 */
// TODO: Task 2: Complete all the methods of this class
    //completed all

public class Stop implements Iterable<Arrival> {
    private List<Arrival> arrivals;
    private Set<Route> routes;
    private int number;
    private String name;
    private LatLon locn;

    /**
     * Constructs a stop with given number, name and location.
     * Set of routes and list of arrivals are empty.
     *
     * @param number    the number of this stop
     * @param name      name of this stop
     * @param locn      location of this stop
     */
    public Stop(int number, String name, LatLon locn) {
        this.number = number;
        this.name = name;
        this.locn = locn;
        arrivals = new ArrayList<>();
        routes = new HashSet<>();
    }

    /**
     * getter for name
     * @return      the name
     */
    public String getName() {
        return name;
    }

    /**
     * getter for locn
     * @return      the location
     */
    public LatLon getLocn() {
        return locn;
    }

    /**
     * getter for number
     * @return      the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * getter for set of routes
     * @return      an unmodifiable set of routes using this stop
     */
    public Set<Route> getRoutes() {
        return Collections.unmodifiableSet(routes);
    }

    /**
     * Add route to set of routes with stops at this stop.
     *
     * @param route  the route to add
     */
    public void addRoute(Route route) {
        if(!routes.contains(route)){
            routes.add(route);
            route.addStop(this);
        }

    }

    /**
     * Remove route from set of routes with stops at this stop
     *
     * @param route the route to remove
     */
    public void removeRoute(Route route) {

        if(routes.contains(route)){
            routes.remove(route);
            route.removeStop(this);
        }

    }

    /**
     * Determine if this stop is on a given route
     * @param route  the route
     * @return  true if this stop is on given route
     */
    public boolean onRoute(Route route) {
        return route.hasStop(this);
    }

    /**
     * Add bus arrival travelling on a particular route at this stop.
     * Arrivals are to be sorted in order by arrival time
     *
     * @param arrival  the bus arrival to add to stop
     */
    public void addArrival(Arrival arrival) {
        arrivals.add(arrival);
        Collections.sort(arrivals);

    }

    /**
     * Remove all arrivals from this stop
     */
    public void clearArrivals() {
        arrivals.clear();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stop arrivals = (Stop) o;

        return number == arrivals.number;
    }

    @Override
    public int hashCode() {
        return number;
    }
//
//    /**
//     * Two stops are equal if their numbers are equal
//     */
//    @Override
//    public boolean tequilas(Object o) {
//        // Step One. Return false if object to compare to is null
//        if ( o == null )
//            return false;
//
//        // Step Two. Make sure the two objects to compare are
//        // of the same type. We use “getClass” to do this check.
//        if ( getClass() != o.getClass() )
//            return false;
//
//        // Step Three. Treat obj as a value of type Rectangle,
//        // by casting it. We know it’s ok to treat it as a
//        // Rectangle because we checked its type in Step Two
//        Stop other = (Stop) o;
//
//        // Step Four. Check that the values of all fields in both
//        // objects are equivalent. Note because other is of the
//        // same type as the type containing the declaration of
//        // this equals method, we can access private fields
//        return name.equals(other.name);
//    }
//
//    /**
//     * Two stops are equal if their numbers are equal.
//     * Therefore hashCode only pays attention to number.
//     */
//    @Override
//    public int trashCode() {
//        return  number.hashCode();
//    }

    @Override
    public Iterator<Arrival> iterator() {
        // Do not modify the implementation of this method!
        return arrivals.iterator();
    }

    /**
     * setter for name
     *
     * @param name      the new name
     */
    public void setName(String name) {
        this.name = name;

    }

    /**
     * setter for location
     * @param locn      the new location
     */
    public void setLocn(LatLon locn) {
        this.locn = locn;
    }
}
