package transmission;

/**
 * Class which implements the interface to automatically determines the current gear by the current
 * speed of the car.
 */
public class AutomaticTransmission implements Transmission {

  /**
   * Variable to store the speed threshold.
   */
  private final int[] speed;
  /**
   * Variable to store current speed of the car.
   */
  private int currentSpeed;
  /**
   * Stores the current gear of the car.
   */
  private Gear gear;

  /**
   * Constructor to initialize the currentSpeed, gear and threshold speed values.
   *
   * @param speed1 To store the first threshold speed
   * @param speed2 To store the second threshold speed
   * @param speed3 To store the third threshold speed
   * @param speed4 To store the fourth threshold speed
   * @param speed5 To store the fifth threshold speed
   */
  public AutomaticTransmission(final int speed1, final int speed2, final int speed3,
                               final int speed4, final int speed5) {
    if (speed1 <= 0 || speed2 <= speed1 || speed3 <= speed2
            || speed4 <= speed3 || speed5 <= speed4) {
      throw new IllegalArgumentException("Invalid speed entered");
    }
    speed = new int[]{speed1, speed2, speed3, speed4, speed5};
    currentSpeed = 0;
    this.gear = Gear.zero;
  }

  /**
   * Function to set the gear based on the current speed of the car.
   */
  private void setGear() {
    if (currentSpeed == 0) {
      this.gear = Gear.zero;
    } else {
      if (currentSpeed > 0 && currentSpeed < speed[0]) {
        this.gear = gear.one;
      } else if (currentSpeed >= speed[0] && currentSpeed < speed[1]) {
        this.gear = gear.two;
      } else if (currentSpeed >= speed[1] && currentSpeed < speed[2]) {
        this.gear = gear.three;
      } else if (currentSpeed >= speed[2] && currentSpeed < speed[3]) {
        this.gear = gear.four;
      } else if (currentSpeed >= speed[3] && currentSpeed < speed[4]) {
        this.gear = gear.five;
      } else if (currentSpeed >= speed[4]) {
        this.gear = gear.six;
      }

    }

  }

  /**
   * Function to increase the current speed of the car by 1.
   */
  @Override
  public void increaseSpeed() {
    currentSpeed = currentSpeed + 1;
    setGear();
  }


  /**
   * Decrease the current speed of the car by 1.
   */
  @Override
  public void decreaseSpeed() {
    if (currentSpeed < 1) {
      throw new IllegalStateException();
    }
    currentSpeed = currentSpeed - 1;
    setGear();
  }


  /**
   * Function to return the current speed of the car.
   *
   * @return current speed
   */
  @Override
  public int getSpeed() {
    return currentSpeed;
  }

  /**
   * Helper function to calculate the gear of the car based on the enum value.
   *
   * @return gear in int
   */
  private int calculateGear() {
    if (this.gear == gear.one) {
      return 1;
    }
    if (this.gear == gear.two) {
      return 2;
    }
    if (this.gear == gear.three) {
      return 3;
    }
    if (this.gear == gear.four) {
      return 4;
    }
    if (this.gear == gear.five) {
      return 5;
    }
    if (this.gear == gear.six) {
      return 6;
    } else {
      return 0;
    }
  }

  /**
   * Function to return the gear of the car.
   *
   * @return gear
   */
  @Override
  public int getGear() {
    return calculateGear();
  }

  /**
   * Function to return the Transmission speed and gear in string format.
   *
   * @return formatted string
   */
  @Override
  public String toString() {
    return String.format("Transmission (speed = %d, gear = %d)", getSpeed(),
            getGear());
  }

}


