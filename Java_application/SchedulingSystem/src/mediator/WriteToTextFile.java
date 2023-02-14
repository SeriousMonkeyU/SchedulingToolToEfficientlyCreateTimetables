package mediator;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * A class responsible for writing data from system into text files in order to preserve the data
 *
 * @author Group 5
 * @version v1 December 2021
 */

public class WriteToTextFile
{
  private File file;
  private SchedulingManagerModel model;

  /**
   * 2-arguments constructor initializing file object
   * @param filename name of the file or path to the file if it is not it same directory as system
   * @param model <code>SchedulingManager</code> object
   */

  public WriteToTextFile(String filename, SchedulingManager model)
  {
    file = new File(filename);
    this.model = model;
  }

  /**
   * Method writing the student data from the system into text file
   */
  public void writeToStudentFile()
  {
    PrintWriter out = null;
    try
    {
      out = new PrintWriter(file);

      for (int i = 0; i < model.getAllStudents().getNumberOfStudents(); i++)
      {
        String s = model.getStudent(i).getSemester() + "," + model.getStudent(i).getClassLetters() + ","
            + model.getStudent(i).getId() + "," + model.getStudent(i).getName();

        for (int j = 0; j < model.getStudent(i).getNumberOfCourses(); j++)
        {
          s += "," + model.getStudent(i).getCourse(j).getName() + "," + model.getStudent(i).getCourse(j).getTeacher().getId();
        }

        out.println(s);
      }

    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    finally
    {
      out.flush();
      out.close();
    }
  }

  /**
   * Method writing the room data from the system into text file
   */

  public void writeToRoomFile()
  {
    PrintWriter out = null;
    try
    {
      out = new PrintWriter(file);

      for (int i = 0; i < model.getAllRooms().getNumberOfRooms(); i++)
      {
        String s = model.getRoom(i).getName() + "," + model.getRoom(i).getCapacity() + ",";
        if (model.getRoom(i).getMergedRoomName() != null)
        {
          s += model.getRoom(i).getMergedRoomName();
        }
        out.println(s);
      }

    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    finally
    {
      out.flush();
      out.close();
    }
  }

  /**
   * Method writing the course and teacher data from the system into text file
   */

  public void writeToCourseFile()
  {
    PrintWriter out = null;
    try
    {
      out = new PrintWriter(file);

      for (int i = 0; i < model.getAllCourses().getNumberOfCourses(); i++)
      {
        String currentName = model.getCourse(i).getName();
        String properName = null;
        if (currentName.contains("DK"))
        {
          properName = currentName.substring(0, currentName.length()-2);
        }
        else
        {
          properName = currentName.substring(0, currentName.length()-1);
        }

        String s = model.getCourse(i).getSemester() + "," + model.getCourse(i).getClassLetters() + "," + properName;

        if (model.getCourse(i).getTeacher() != null)
        {
          s += "," + model.getCourse(i).getTeacher().getId();
        }
        else
        {
          s += ",null";
        }

        s += "," + model.getCourse(i).getEcts();

        out.println(s);
      }

    }
    catch (IOException e)
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
