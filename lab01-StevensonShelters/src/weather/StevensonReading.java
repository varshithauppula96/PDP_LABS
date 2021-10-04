package weather;


/**
 * Class to store a reading that was taken from a Stevenson Station and calculate various parameters
 * which are used to determine the actual temperature feel.
 */
public final class StevensonReading implements weather.WeatherReading {


  private final double airTemperature;
  private final double dewPoint;
  private final double windSpeed;
  private final int rain;
  private double relativeHumidity;
  private double heatIndex;
  private double windChill;

  /**
   * Constructor to initialize stevenson reading values.
   *
   * @param airTemperature To store the air temperature in Celsius
   * @param dewPoint       temperature in Celsius which cannot be greater than the air temperature
   * @param windSpeed      non-negative wind speed in miles per hour
   * @param rain           non-negative total rain received in the last 24 hours in millimeters
   */

  public StevensonReading(double airTemperature, double dewPoint, double windSpeed, int rain) {
    /* Check if Air temperature is invalid*/
    if (airTemperature < -273.15) {
      throw new IllegalArgumentException("Lowest possible temperature is -273.15 Celsius");
    }
    /* Check if Dew Point is invalid*/
    if (dewPoint > airTemperature) {
      throw new IllegalArgumentException("dew point needs to be lesser than air temperature");
    }
    /* Check if Wind Speed is negative*/
    if (windSpeed < 0) {
      throw new IllegalArgumentException("Negative wind speed are not supported");
    }
    /* Check if Rain is negative */
    if (rain < 0) {
      throw new IllegalArgumentException("Negative rain are not supported");
    }
    if (airTemperature - 20 > dewPoint) {
      throw new IllegalArgumentException("Temperature- 20 needs to be greater than dew point");
    }
    this.airTemperature = airTemperature;
    this.dewPoint = dewPoint;
    this.windSpeed = windSpeed;
    this.rain = rain;
    this.relativeHumidity = 0;
    this.heatIndex = 0;
    this.windChill = 0;
  }


  /**
   * Override method equals is implemented.
   *
   * @param o object of stevenson reading containing values of air temperature, dew point, wind
   *          speed, and rain
   * @return true or false based on comparision of parameters of stevenson readings
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!(o instanceof StevensonReading)) {
      return false;
    }

    StevensonReading that = (StevensonReading) o;
    return Double.compare(that.airTemperature, airTemperature) == 0
            && Double.compare(that.dewPoint, dewPoint) == 0
            && Double.compare(that.windSpeed, windSpeed) == 0
            && rain == that.rain;
  }

  /**
   * Override the hashcode function to create own hashcode by multiplying value with 17 and adding
   * it to the attributes.
   *
   * @return result
   */
  @Override
  public int hashCode() {
    int result = 17;
    if (windSpeed > 0) {
      result = 17 * result + Integer.hashCode(getWindChill());
    }
    if (rain > 0) {
      result = 17 * result + Integer.hashCode(rain);
    }
    result = 17 * result + Integer.hashCode(getTemperature());


    result = 17 * result + Integer.hashCode(getDewPoint());


    return result;
  }

  /**
   * Return the rounded air temperature value in int.
   *
   * @return air temperature
   */

  @Override
  public int getTemperature() {
    return (int) Math.round(airTemperature);
  }


  /**
   * Return the rounded Dew point.
   *
   * @return dew point
   */
  @Override
  public int getDewPoint() {
    return (int) Math.round(dewPoint);
  }

  /**
   * Return the rounded Speed.
   *
   * @return speed
   */
  @Override

  public int getWindSpeed() {
    return (int) Math.round(windSpeed);
  }

  /**
   * Return the value stored in rain.
   *
   * @return rain
   */
  @Override
  public int getTotalRain() {
    return rain;
  }


  /**
   * Calculate relative Humidity from temperature and dew point using the formula D=T−(100−R/5).
   */
  private void findRelativeHumidity() {
    relativeHumidity = (100 - (5 * airTemperature) + (5 * dewPoint));

  }

  /**
   * Calls the function findRelativeHumidity and Returns the value of relative humidity in int.
   *
   * @return relative Humidity
   */
  @Override
  public int getRelativeHumidity() {
    findRelativeHumidity();
    return (int) Math.round(relativeHumidity);
  }


  /**
   * Calculate heat index from air temperature and relative humidity.
   */
  private void findHeatIndex() {
    final double c1 = -8.78469475556;
    final double c2 = 1.61139411;
    final double c3 = 2.33854883889;
    final double c4 = -0.14611605;
    final double c5 = -0.012308094;
    final double c6 = -0.0164248277778;
    final double c7 = 0.002211732;
    final double c8 = 0.00072546;
    final double c9 = -0.000003582;
    getRelativeHumidity();

    heatIndex = (c1 + (c2 * airTemperature) + (c3 * relativeHumidity)
            + (c4 * airTemperature * relativeHumidity)
            + (c5 * airTemperature * airTemperature) + (c6 * relativeHumidity * relativeHumidity)
            + (c7 * airTemperature * airTemperature * relativeHumidity)
            + (c8 * airTemperature * relativeHumidity * relativeHumidity)
            + (c9 * airTemperature * airTemperature * relativeHumidity * relativeHumidity));

  }

  /**
   * Call the function findHeatIndex to calculate the heat index and return it.
   *
   * @return heat index
   */
  @Override
  public int getHeatIndex() {
    findHeatIndex();
    return (int) Math.round(heatIndex);
  }

  /**
   * Convert temperature from Celcius to Fahrenheit.
   *
   * @return temperature
   */
  private double temperatureConversion() {
    double temp;
    temp = ((1.8) * airTemperature) + 32;
    return temp;
  }


  /**
   * Calculate the windchill based on formulla and return it.
   */
  private void findWindChill() {
    double temperature = temperatureConversion();

    windChill = (35.74 + (0.6215 * temperature) - (35.75 * Math.pow(windSpeed, 0.16))
            + ((0.4275 * temperature) * Math.pow(windSpeed, 0.16)));
  }

  /**
   * Call function findWindChill to calculate wind chill.
   *
   * @return wind chill
   */

  @Override
  public int getWindChill() {

    findWindChill();
    return (int) Math.round(windChill);
  }

  /**
   * Function to return a string that displays the stephens reading in the below format Reading: T =
   * 23, D = 12, v = 3, rain = 12.
   *
   * @return string
   */

  @Override
  public String toString() {
    return String.format("Reading: T = %d, D = %d, v = %d, rain = %d", getTemperature(),
            getDewPoint(), getWindSpeed(), getTotalRain());
  }
}
