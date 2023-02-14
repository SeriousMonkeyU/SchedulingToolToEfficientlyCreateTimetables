package mediator;

import model.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

/**
 * A class responsible for loading data about sessions from xml file
 *
 * @author Group 5
 * @version v1 December 2021
 */

public class LoadSchedule
{
  private SchedulingManagerModel model;

  /**
   * 1-argument constructor
   * @param model <code>SchedulingManager</code> object
   */

  public LoadSchedule(SchedulingManager model)
  {
    this.model = model;
  }

  /**
   * Method loading data from xml file into the system
   */

  public void loadXmlFile()
  {
    try
    {
      DocumentBuilderFactory factory =   DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse("Sessions.xml");
      Transformer transformer= TransformerFactory.newInstance().newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "no");

      Date date = null;
      Room room = null;
      Course course = null;
      TimeSlot timeSlot = null;
      StudentClass studentClass = null;
      Time startTime = null;
      Time endTime = null;
      int day = 0, month = 0, year = 0, numberOfLessons = 0, hour = 0, minute = 0, semester = 0;
      String roomName = null, symbol = null, courseName = null;


      NodeList rootList= doc.getElementsByTagName("Session");
      for(int index = 0; index < rootList.getLength(); index++)
      {
        Node rootNode  = rootList.item(index);
        NodeList subNodes = rootNode.getChildNodes();

        for (int i = 0; i < subNodes.getLength(); i++)
        {
          if (subNodes.item(i).getNodeName().equals("Date"))
          {
            NodeList dateNodes = subNodes.item(i).getChildNodes();
            for (int j = 0; j < dateNodes.getLength(); j++)
            {
              Node d = dateNodes.item(j);
              if (d.getNodeName().equals("day"))
              {
                day = Integer.parseInt(d.getTextContent());
              }
              else if (d.getNodeName().equals("month"))
              {
                month = Integer.parseInt(d.getTextContent());
              }
              else if (d.getNodeName().equals("year"))
              {
                year = Integer.parseInt(d.getTextContent());
              }
            }
            date = new Date(day,month,year);
          }
          else if (subNodes.item(i).getNodeName().equals("Room"))
          {
            NodeList roomNodes = subNodes.item(i).getChildNodes();
            for (int j = 0; j < roomNodes.getLength(); j++)
            {
              Node r = roomNodes.item(j);
              if (r.getNodeName().equals("name"))
              {
                roomName = r.getTextContent();
                break;
              }
            }
            room = model.getRoomByName(roomName);
          }
          else if (subNodes.item(i).getNodeName().equals("TimeSlot"))
          {
            NodeList timeSlotNodes = subNodes.item(i).getChildNodes();
            for (int j = 0; j < timeSlotNodes.getLength(); j++)
            {
              Node t = timeSlotNodes.item(j);
              if (t.getNodeName().equals("numberOfLessons"))
              {
                numberOfLessons = Integer.parseInt(t.getTextContent());
              }
              else if (t.getNodeName().equals("startTime"))
              {
                NodeList subTimeSlotNodes = t.getChildNodes();
                for (int k = 0; k < subTimeSlotNodes.getLength(); k++)
                {
                  Node st = subTimeSlotNodes.item(k);
                  if (st.getNodeName().equals("hour"))
                  {
                    hour = Integer.parseInt(st.getTextContent());
                  }
                  else if (st.getNodeName().equals("minute"))
                  {
                    minute = Integer.parseInt(st.getTextContent());
                  }
                }
                startTime = new Time(hour,minute);
              }
              else if (t.getNodeName().equals("endTime"))
              {
                NodeList subTimeSlotNodes = t.getChildNodes();
                for (int k = 0; k < subTimeSlotNodes.getLength(); k++)
                {
                  Node st = subTimeSlotNodes.item(k);
                  if (st.getNodeName().equals("hour"))
                  {
                    hour = Integer.parseInt(st.getTextContent());
                  }
                  else if (st.getNodeName().equals("minute"))
                  {
                    minute = Integer.parseInt(st.getTextContent());
                  }
                }
                endTime = new Time(hour,minute);
              }
            }
            timeSlot = new TimeSlot(numberOfLessons,startTime,endTime);
          }
          else if (subNodes.item(i).getNodeName().equals("StudentClass"))
          {
            NodeList classNodes = subNodes.item(i).getChildNodes();
            for (int j = 0; j < classNodes.getLength(); j++)
            {
              Node c = classNodes.item(j);
              if (c.getNodeName().equals("semester"))
              {
                semester = Integer.parseInt(c.getTextContent());
              }
              else if (c.getNodeName().equals("symbol"))
              {
                symbol = c.getTextContent();
              }
            }
            studentClass = new StudentClass(semester,symbol);
          }
          else if (subNodes.item(i).getNodeName().equals("Course"))
          {
            NodeList courseNodes = subNodes.item(i).getChildNodes();
            for (int j = 0; j < courseNodes.getLength(); j++)
            {
              Node c = courseNodes.item(j);
              if (c.getNodeName().equals("name"))
              {
                courseName = c.getTextContent();
              }
            }
            course = model.getCourseByName(courseName);
          }

        }
        Session session = new Session(date,room,timeSlot,studentClass,course);
        model.addSession(session);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

  }
}
