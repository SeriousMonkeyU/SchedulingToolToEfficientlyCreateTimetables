package mediator;

import model.TimeSlot;

/**
 * A class which only purpose is to keep data of one <code>TimeSlot</code> object between windows in GUI. Class has private constructor and final static instance
 * variable that calls the constructor to ensure that only one instance of this class exists in the system (singleton class).
 *
 * @author Group 5
 * @version v1 December 2021
 */

public final class TimeSlotHolder
{
  private TimeSlot timeSlot;
  private final static TimeSlotHolder INSTANCE = new TimeSlotHolder();

  private TimeSlotHolder() {}

  /**
   * Static method returning the only existing instance of this class
   * @return instance of <code>TimeSlotHolder</code>
   */

  public static TimeSlotHolder getInstance() {
    return INSTANCE;
  }

  /**
   * Method setting data of which object will the <code>TimeSlotHolder</code> class keep
   * @param timeSlot <code>TimeSlot</code> object which data we want to keep in this holder
   */
  public void setTimeSlot(TimeSlot timeSlot) {
    this.timeSlot = timeSlot;
  }

  /**
   * Method returning the object which data we kept in this class
   * @return <code>TimeSlot</code> object that the class was "holding"
   */

  public TimeSlot getTimeSlot() {
    return this.timeSlot;
  }
}
