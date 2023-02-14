package mediator;

import model.Session;

/**
 * A class which only purpose is to keep data of one <code>Session</code> object between windows in GUI. Class has private constructor and final static instance
 * variable that calls the constructor to ensure that only one instance of this class exists in the system (singleton class).
 *
 * @author Group 5
 * @version v1 December 2021
 */


public final class SessionHolder
{
  private Session session;
  private final static SessionHolder INSTANCE = new SessionHolder();

  private SessionHolder() {}

  /**
   * Static method returning the only existing instance of this class
   * @return instance of <code>SessionHolder</code>
   */

  public static SessionHolder getInstance() {
    return INSTANCE;
  }

  /**
   * Method setting data of which object will the <code>SessionHolder</code> class keep
   * @param session <code>Session</code> object which data we want to keep in this holder
   */

  public void setSession(Session session) {
    this.session = session;
  }

  /**
   * Method returning the object which data we kept in this class
   * @return <code>Session</code> object that the class was "holding"
   */

  public Session getSession() {
    return this.session;
  }
}
