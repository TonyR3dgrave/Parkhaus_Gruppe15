package de.hbrs.team89.se1_starter_repo;

import javax.json.JsonObject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * common superclass for all servlets
 * groups all auxiliary common methods used in all servlets
 */
public abstract class ParkhausServlet extends HttpServlet {

    /* abstract methods, to be defined in subclasses */
    abstract String NAME(); // each ParkhausServlet should have a name, e.g. "Level1"
    abstract int MAX(); // maximum number of parking slots of a single parking level
    abstract String config(); // configuration of a single parking level
    /**
     * HTTP GET
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String cmd = request.getParameter("cmd");
        System.out.println( cmd + " requested: " + request.getQueryString());

        switch ( cmd ){
            case "config":
                // Overwrite Parkhaus config parameters
                // Max, open_from, open_to, delay, simulation_speed
                out.println( config() );
                break;
            case "sum":
                out.println( "sum = " + getSum() / 100);
                break;
            case "avg":
                out.println("avg = " + (getSum() / getCarCounter()) / 100);
                break;
            case "min":
                out.println( "min = " + getMin() / 100);
                break;
            case "max":
                out.println( "max = " + getMax() / 100);
                break;
            case "cars":
                // TODO: Send list of cars stored on the server to the client.
                // Cars are separated by comma.
                // Values of a single car are separated by slash.
                // Format: Nr, timer begin, duration, price, Ticket, color, space, client category, vehicle type, license (PKW Kennzeichen)
                // For example:
                // TODO replace by real list of cars
                // out.println("1/1648465400000/_/_/Ticket1/#0d1e0a/2/any/PKW/1,2/1648465499999/_/_/Ticket2/#dd10aa/3/any/PKW/2");
                break;
            case "chart":
                List<?> genders = new SpecificListBuilder(cars(),"Gender").makeSpecificList();
                JsonObject chart = new JsonBuilder(Car.GENDERS, genders).makePie();
                out.println(chart.toString());
                break;
            default:
                System.out.println("Invalid Command: " + request.getQueryString());
        }

    }

    /**
     * HTTP POST
     *
     * params:
     * [0] enter / leave
     * [1] nr
     * [2] Einfahrts-Zeit in sekunden nach 1970 (Unix Timestamp)
     * [3] Dauer in Sekunden (impl. Ausfahrts-Zeit)
     * [4] Preis
     * [5] Ticket hash-Wert
     * [6] Farbe des Autos
     * [7] Parkplatz-Nr.
     * [8] Geschlecht
     * [9] Auto-Typ (PKW, SUV, ...)
     * [10] Kennzeichen
     * [11] = [2] ???
     * [12] Parkhaus (Etage) (=NAME)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String body = getBody( request );
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        System.out.println( body );
        String[] params = body.split(",");
        String event = params[0];
        String[] restParams = Arrays.copyOfRange(params, 1, params.length);

        switch( event ){
            case "enter":               //nr,           gender,         type,           license
                CarIF newCar = new Car(restParams[0], restParams[7], restParams[8], restParams[9] );
                cars().add( newCar );
                System.out.println( "enter," + newCar );

                // re-direct car to another parking lot
                out.println( locator( newCar ) );
                break;
            case "leave":
                CarIF oldCar = removeCarFromList( Integer.parseInt(restParams[0]) );

                if (oldCar == null) System.out.println("Car " + restParams[0] + " not in cars list!");

                // Fetch the price
                double price = 0.0d;
                if ( params.length > 4 ){
                    String priceString = params[4];
                    if ( ! "_".equals( priceString ) ){
                        // "\\D+" = 1 or more digit
                        price = (double)new Scanner( priceString ).useDelimiter("\\D+").nextInt();
                        price /= 100.0d;            // Convert to cents
                        price = Math.floor(price);  // Cut off excess digits

                        // Store new sum in ServletContext
                        getContext().setAttribute("sum", getSum() + price );
                        getContext().setAttribute("carCounter", getCarCounter() + 1);
                        // Update minimum and maximum, if necessary
                        if (price < getMin()) {
                            getContext().setAttribute("min",price);
                        }
                        if (price > getMax()) {
                            getContext().setAttribute("max",price);
                        }
                    }
                }
                out.println( price );  // server calculated price
                System.out.println( "leave," + oldCar + ", price = " + price );
                break;
            case "invalid": case "occupied":
                System.out.println( body );
                break;
            case "tomcat":
                out.println( getServletConfig().getServletContext().getServerInfo()
                        + getServletConfig().getServletContext().getMajorVersion()
                        + getServletConfig().getServletContext().getMinorVersion() );
                break;
            default:
                System.out.println( body );
        }

    }


    // auxiliary methods used in HTTP request processing

    /**
     * @return the servlet context
     */
    ServletContext getContext(){
        return getServletConfig().getServletContext();
    }

    /**
     * TODO: replace this by your own function
     * @return the number of the free parking lot to which the next incoming car will be directed
     */
    int locator( CarIF car ){
        // numbers of parking lots start at 1, not zero
        // return 1;  // always use the first space
        return 1 + (( cars().size() - 1 ) % this.MAX());
    }

    /**
     * @return the list of all cars stored in the servlet context so far
     */
    @SuppressWarnings("unchecked")
    List<CarIF> cars(){
        if ( getContext().getAttribute( "cars"+NAME() ) == null ){
            getContext().setAttribute( "cars"+NAME(), new ArrayList<Car>() );
        }
        return (List<CarIF>) getContext().getAttribute( "cars"+NAME() );
    }

    /**
     * @param request the HTTP POST request
     * @return the body of the request
     */
    String getBody( HttpServletRequest request ) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if ( inputStream != null ) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public void destroy() {
        System.out.println("Servlet destroyed.");
    }


    private ServletContext getApplication() {
        return getServletConfig().getServletContext();
    }
    private double getSum() {
        Double sum;
        ServletContext app = getApplication();
        sum = (Double) app.getAttribute("sum");
        return (sum == null) ? 0.0 : sum;
    }

    private int getCarCounter(){
        Integer carCounter;
        ServletContext app = getApplication();
        carCounter = (Integer) app.getAttribute("carCounter");
        return (carCounter == null) ? 0 : carCounter;
    }

    private double getMin(){
        Double min;
        ServletContext app = getApplication();
        min = (Double) app.getAttribute("min");
        return (min == null) ? Double.MAX_VALUE : min;
    }
    private double getMax(){
        Double max;
        ServletContext app = getApplication();
        max = (Double) app.getAttribute("max");
        return (max == null) ? Double.MIN_VALUE : max;
    }

    private CarIF removeCarFromList(int nr) {
        List<CarIF> cars = cars();

        for (CarIF car : cars) {
            if (car.nr() == nr) {
                cars.remove(car);
                return car;
            }
        }
        return null;
    }
}