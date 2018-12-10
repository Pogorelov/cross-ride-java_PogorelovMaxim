package com.crossover.techtrial.constants;

/**
 * Created by pogorelov on 12/9/18.
 */
public class ConstantsTest {

    // RIDES
    public static final String RIDE_100_OK = "{\"id\": \"100\", \"driver\": {\"id\": \"1\"}, \"rider\": {\"id\": \"900\"},"
            + " \"startTime\":\"2018-08-08T13:00:00\",\"endTime\":\"2018-08-08T14:00:00\",\"distance\":\"201\" }";

    public static final String RIDE_1000_OK = "{\"id\": \"1000\", \"driver\": {\"id\": \"1\"}, \"rider\": {\"id\": \"900\"},"
            + " \"startTime\":\"2018-08-28T13:00:00\",\"endTime\":\"2018-08-28T14:00:00\",\"distance\":\"201\" }";

    public static final Object RIDE_100_WRONG_ENDTIME = "{\"id\": \"100\", \"driver\": {\"id\": \"1\"}, \"rider\": {\"id\": \"900\"},"
            + " \"startTime\":\"2018-08-18T12:20:00\",\"endTime\":\"2018-08-18T12:00:00\",\"distance\":\"150L\" }";

    public static final Object RIDE_200_WRONG_DRIVER_NOT_EXISTS = "{\"id\": \"200\", \"driver\": {\"id\": \"9999\"}, \"rider\": {\"id\": \"900\"},"
            + " \"startTime\":\"2018-08-19T12:00:00\",\"endTime\":\"2018-08-19T12:40:00\",\"distance\":\"50L\" }";

    public static final Object RIDE_300_WRONG_RIDER_NOT_EXISTS = "{\"id\": \"300\", \"driver\": {\"id\": \"1\"}, \"rider\": {\"id\": \"9999\"},"
            + " \"startTime\":\"2018-08-19T12:00:00\",\"endTime\":\"2018-08-19T12:40:00\",\"distance\":\"50L\" }";

    public static final Object RIDE_400_WRONG_NULL_FIELDS= "{\"id\": \"400\", \"driver\": {\"id\": \"\"}, \"rider\": {\"id\": \"\"},"
            + " \"startTime\":\"\",\"endTime\":\"\",\"distance\":\"\" }";

    // DRIVERS
    public static final String DRIVER_100 = "{\"id\": \"100\",\"name\": \"driver 100\", \"email\": \"driver10000000000001@gmail.com\","
            + " \"registrationNumber\": \"41DCT\",\"registrationDate\":\"2018-08-08T12:12:12\" }";

    // RIDERS
    public static final String RIDER_900 = "{\"id\": \"900\",\"name\": \"rider 900\", \"email\": \"rider900@gmail.com\","
            + " \"registrationNumber\": \"41RCT\",\"registrationDate\":\"2018-08-08T12:12:12\" }";

}
