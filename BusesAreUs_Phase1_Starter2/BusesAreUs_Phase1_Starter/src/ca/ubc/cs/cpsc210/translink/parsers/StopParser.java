package ca.ubc.cs.cpsc210.translink.parsers;

import ca.ubc.cs.cpsc210.translink.model.Stop;
import ca.ubc.cs.cpsc210.translink.model.StopManager;
import ca.ubc.cs.cpsc210.translink.parsers.exception.StopDataMissingException;
import ca.ubc.cs.cpsc210.translink.providers.DataProvider;
import ca.ubc.cs.cpsc210.translink.providers.FileDataProvider;
import ca.ubc.cs.cpsc210.translink.util.LatLon;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * A parser for the data returned by Translink stops query
 */
public class StopParser {

    private String filename;

    public StopParser(String filename) {
        this.filename = filename;
    }
    /**
     * Parse stop data from the file and add all stops to stop manager.
     *
     */
    public void parse() throws IOException, StopDataMissingException, JSONException{
        DataProvider dataProvider = new FileDataProvider(filename);

        parseStops(dataProvider.dataSourceToString());
    }
    /**
     * Parse stop information from JSON response produced by Translink.
     * Stores all stops and routes found in the StopManager and RouteManager.
     *
     * @param  jsonResponse    string encoding JSON data to be parsed
     * @throws JSONException when:
     * <ul>
     *     <li>JSON data does not have expected format (JSON syntax problem)</li>
     *     <li>JSON data is not an array</li>
     * </ul>
     * If a JSONException is thrown, no stops should be added to the stop manager
     * @throws StopDataMissingException when
     * <ul>
     *  <li> JSON data is missing Name, StopNo, Routes or location (Latitude or Longitude) elements for any stop</li>
     * </ul>
     * If a StopDataMissingException is thrown, all correct stops are first added to the stop manager.
     */

    public void parseStops(String jsonResponse)
            throws JSONException, StopDataMissingException {
        // TODO: Task 4: Implement this method
        // on it, being experimental af
        JSONArray stops = new JSONArray(jsonResponse);
        boolean didItThrow = false;

        for (int i = 0; i < stops.length(); i++)
        {
            try{
                JSONObject ithroute = stops.getJSONObject(i);
                readStops(ithroute);
            }catch (StopDataMissingException e){
                didItThrow = true;
            }
        }

        if(didItThrow)
            throw new StopDataMissingException();


    }


    public void readStops(JSONObject ithroute)
            throws JSONException, StopDataMissingException {
        try {
            String name = ithroute.getString("Name");
        //    String atStreet = ithroute.getString("AtStreet");
        //    int wheelchair = ithroute.getInt("WheelChairAccess");
        //    String bayNo = ithroute.getString("BayNo");
            double latitude = ithroute.getDouble("Latitude");
            double longitude = ithroute.getDouble("Longitude");
            int stopNo = ithroute.getInt("StopNo");
        //    String onStreet = ithroute.getString("OnStreet");
            String routes = ithroute.getString("Routes");
        //    String city = ithroute.getString("City");
             LatLon latLon = new LatLon(latitude, longitude);
            // make new stop
            Stop newStop = StopManager.getInstance().getStopWithNumber(stopNo, name,latLon );

        }catch (JSONException e){
            throw new StopDataMissingException();
        }


    }
}
