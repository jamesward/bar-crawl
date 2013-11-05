package util;

import java.io.BufferedReader;


import java.net.URLEncoder;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.base.Optional;
import models.GeoPoint;
import play.Logger;
import play.libs.WS;

public class GeocodeHelper {

    private static final String GOOGLE_URL = "http://maps.googleapis.com/maps/api/geocode/json";

    enum StatusCode {
        OK,
        ZERO_RESULTS,
        OVER_QUERY_LIMIT,
        REQUEST_DENIED,
        INVALID_REQUEST,
        UNKNOWN_ERROR;

        public boolean isFine() {
            return this == OK;
        }

        public boolean isRejected() {
            return this == OVER_QUERY_LIMIT || this == REQUEST_DENIED;
        }
    }

    public static Optional<GeoPoint> getGeoPoint(String address)  {
        Optional<GeoPoint> result = Optional.absent();
        try {
            JsonNode root = getGeoData(address);
            if (isOK(root)) {
                result = getLatLng(root);
            }
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);
        }

        return result;
    }

    private static Optional<GeoPoint> getLatLng(JsonNode root) {
        Optional<GeoPoint> result = Optional.absent();
        JsonNode location = null;
        JsonNode results = root.get("results");

        //if we have a possible location, take the first one
        if (results != null && results.size() > 0){
            location = results.get(0).get("geometry").get("location");
        }

        if (location != null) {
            result = Optional.of(new GeoPoint(location.get("lat").asDouble(), location.get("lng").asDouble()));
        }

        return result;
    }

    private static boolean isOK(JsonNode root) {
        String status = root.get("status").asText();
        StatusCode code = StatusCode.valueOf(status);
        Logger.info(code.toString());
        return code.isFine();
    }


    private static JsonNode getGeoData(String address) throws GeocodeException {
        // Logger.info("Google Address: " + address);
        try {
            JsonNode rootNode = WS.url(GOOGLE_URL).
                    setQueryParameter("address", address).
                    setQueryParameter("sensor", "false").
                    get().get(5, TimeUnit.SECONDS).asJson();
            return rootNode;
        } catch (Exception e) {
            throw new GeocodeException("Could not fetch json  for " + address, e);
        }
    }
}
