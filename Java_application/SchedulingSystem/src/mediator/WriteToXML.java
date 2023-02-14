package mediator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.temporal.WeekFields;

import model.*;

/**
 * A class responsible for writing data of all sessions in the system into structured xml file in order to preserve data about sessions
 *
 * @author Group 5
 * @version v1 December 2021
 */

public class WriteToXML
{
  private File file;

  /**
   * Constructor initializing file object
   */

  public WriteToXML()
  {
    file = new File("Sessions.xml");
  }

  /**
   * Method preparing content of xml file and then writing it into the file
   * @param sessionList list of sessions which data will be written into the file
   */

  public void writeScheduleToXml(SessionList sessionList)
  {

    PrintWriter out = null;
    try
    {
      out = new PrintWriter (file);

      String xml = "";
      xml += "<?xml version =\"1.0\" encoding=\"UTF-8\"" + " standalone=\"no\"?>\n";
      xml += "<SessionList>";
      for (int i = 0; i < sessionList.getNumberOfSessions(); i ++)
      {
        xml += "\n<Session>";
        //----------------------------------------Week----------------------------------------------
        xml += "\n<Week>";
        xml += "\n<weekNumber>" + LocalDate.of(sessionList.getSession(i).getDate().getYear(),sessionList.getSession(i).getDate().getMonth(),sessionList.getSession(i).getDate().getDay()).get(
            WeekFields.ISO.weekOfWeekBasedYear()) + "</weekNumber>";
        xml += "\n<weekDay>" + Date.dayOfWeek(sessionList.getSession(i).getDate().getDay(),sessionList.getSession(i).getDate().getMonth(),sessionList.getSession(i).getDate().getYear()) + "</weekDay>";
        xml += "\n</Week>";
        //----------------------------------------Date----------------------------------------------
        xml += "\n<Date>";
        xml += "\n<day>" + sessionList.getSession(i).getDate().getDay() + "</day>";
        xml += "\n<month>" + sessionList.getSession(i).getDate().getMonth() + "</month>";
        xml += "\n<year>" + sessionList.getSession(i).getDate().getYear() + "</year>";
        xml += "\n</Date>";
        //--------------------------------------Room----------------------------------------------
        xml += "\n<Room>";
        xml += "\n<name>" + sessionList.getSession(i).getRoom().getName() + "</name>";
        xml += "\n<capacity>" + sessionList.getSession(i).getRoom().getCapacity() + "</capacity>";
        xml += "\n<mergedRoomName>" + sessionList.getSession(i).getRoom().getMergedRoomName() + "</mergedRoomName>";
        xml += "\n<reservedDates>";
        for (int j = 0; j < sessionList.getSession(i).getRoom().getReservedDates().size(); j ++)
        {
          xml += "\n<reservedDate>";
          xml += "\n<day>" + sessionList.getSession(i).getRoom().getReservedDates().get(j).getDay() + "</day>";
          xml += "\n<month>" + sessionList.getSession(i).getRoom().getReservedDates().get(j).getMonth() + "</month>";
          xml += "\n<year>" + sessionList.getSession(i).getRoom().getReservedDates().get(j).getYear() + "</year>";
          xml += "\n</reservedDate>";
        }
        xml += "\n</reservedDates>";
        xml += "\n<reservedTimeSlots>";
        for (int j = 0; j < sessionList.getSession(i).getRoom().getReservedTimeSlots().size(); j ++)
        {
          xml += "\n<reservedTimeSlot>";
          xml += "\n<numberOfLessons>" + sessionList.getSession(i).getRoom().getReservedTimeSlots().get(j).getNumberOfLessons() + "</numberOfLessons>";
          xml += "\n<startTime>";
          xml += "\n<hour>" + sessionList.getSession(i).getRoom().getReservedTimeSlots().get(j).getStartTime().getHour() + "</hour>";
          xml += "\n<minute>" + sessionList.getSession(i).getRoom().getReservedTimeSlots().get(j).getStartTime().getMinute() + "</minute>";
          xml += "\n</startTime>";
          xml += "\n<endTime>";
          xml += "\n<hour>" + sessionList.getSession(i).getRoom().getReservedTimeSlots().get(j).getEndTime().getHour() + "</hour>";
          xml += "\n<minute>" + sessionList.getSession(i).getRoom().getReservedTimeSlots().get(j).getEndTime().getMinute() + "</minute>";
          xml += "\n</endTime>";
          xml += "\n</reservedTimeSlot>";
        }
        xml += "\n</reservedTimeSlots>";
        xml += "\n</Room>";
        //--------------------------------------TimeSlot----------------------------------------------
        xml += "\n<TimeSlot>";
        xml += "\n<numberOfLessons>" + sessionList.getSession(i).getTimeSlot().getNumberOfLessons() + "</numberOfLessons>";
        xml += "\n<startTime>";
        xml += "\n<hour>" + sessionList.getSession(i).getTimeSlot().getStartTime().getHour() + "</hour>";
        xml += "\n<minute>" + sessionList.getSession(i).getTimeSlot().getStartTime().getMinute() + "</minute>";
        xml += "\n</startTime>";
        xml += "\n<endTime>";
        xml += "\n<hour>" + sessionList.getSession(i).getTimeSlot().getEndTime().getHour() + "</hour>";
        xml += "\n<minute>" + sessionList.getSession(i).getTimeSlot().getEndTime().getMinute() + "</minute>";
        xml += "\n</endTime>";
        xml += "\n</TimeSlot>";
        //--------------------------------------Student Class----------------------------------------------
        xml += "\n<StudentClass>";
        xml += "\n<semester>" + sessionList.getSession(i).getStudentClass().getSemester() + "</semester>";
        xml += "\n<symbol>" + sessionList.getSession(i).getStudentClass().getClassLetters() + "</symbol>";
        xml += "\n</StudentClass>";
        //--------------------------------------Course----------------------------------------------
        xml += "\n<Course>";
        xml += "\n<semester>" + sessionList.getSession(i).getCourse().getSemester() + "</semester>";
        xml += "\n<name>" + sessionList.getSession(i).getCourse().getName() + "</name>";
        xml += "\n<ects>" + sessionList.getSession(i).getCourse().getEcts() + "</ects>";
        xml += "\n<teacher>";
        xml += "\n<id>" + sessionList.getSession(i).getCourse().getTeacher().getId() + "</id>";
        xml += "\n<email>" + sessionList.getSession(i).getCourse().getTeacher().getEmail() + "</email>";
        // xml += ArrayList of courses are not written here (assigned to Teacher <- Person classes). Seems illogical in courses section.
        xml += "\n</teacher>";
        xml += "\n<classLetters>" + sessionList.getSession(i).getCourse().getClassLetters() + "</classLetters>";
        xml += "\n</Course>";
        //--------------------------------------Session-end----------------------------------------------
        xml += "\n</Session>";
      }
      xml += "\n</SessionList>";

      out.println(xml);

    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    finally
    {
      out.flush();
      out.close();
    }
  }
}
