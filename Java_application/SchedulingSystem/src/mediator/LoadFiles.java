package mediator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import model.*;

/**
 * A class responsible for loading data from text files into the system
 *
 * @author Group 5
 * @version v1 December 2021
 */
public class LoadFiles
{
  private File file;
  private SchedulingManagerModel model;

  /**
   * 2-arguments constructor initializing file object
   * @param filename name of the file or path to the file if it is not it same directory as system
   * @param model <code>SchedulingManager</code> object
   */

  public LoadFiles(String filename, SchedulingManager model)
  {
    file = new File(filename);
    this.model = model;
  }

  /**
   * Method importing data about courses and teachers from text file into the system
   */
  public void loadCourseFile()
  {

    Scanner in = null;
    try
    {
      in = new Scanner(file);

      while(in.hasNext())
      {
        String line = in.nextLine();
        String[] words = line.split(",");
        int semester = Integer.parseInt(words[0].trim());
        String classLetters = words[1].trim();
        String name = words[2].trim() + "" + words[1].trim();
        String teacherName = words[3].trim();
        int ects = Integer.parseInt(words[4].trim());
        Teacher teacher = new Teacher(teacherName);
        Course course = null;
        if (teacherName.equals("null"))
        {
          course = new Course(semester,name,ects,classLetters);
        }
        else
        {
          course = new Course(semester,name,ects,teacher,classLetters);
        }
        model.getAllCourses().addCourse(course);
      }
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    finally
    {
      in.close();
    }

  }

  /**
   * Method importing data about rooms from text file into the system
   */

  public void loadRoomFile()
  {
    Scanner in = null;
    try
    {
      in = new Scanner(file);

      while(in.hasNext())
      {
        String line = in.nextLine();
        String[] words = line.split(",");
        String name = words[0].trim();
        int capacity = Integer.parseInt(words[1].trim());
        Room room = new Room(name,capacity);

        if (words.length == 3)
        {
          String mergedRoomName = words[2].trim();
          room.setMergedRoom(mergedRoomName);
        }

        model.getAllRooms().addRoom(room);
      }
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    finally
    {
      in.close();
    }

  }

  /**
   * Method importing data about students from text file into the system
   */

  public void loadStudentFile()
  {
    Scanner in = null;
    try
    {
      in = new Scanner(file);

      while(in.hasNext())
      {
        String line = in.nextLine();
        String[] words = line.split(",");
        int semester = Integer.parseInt(words[0].trim());
        String classLetters = words[1].trim();
        String id = words[2].trim();
        String name =words[3].trim();
        Student student = new Student(name,id,semester,classLetters);
        model.addStudent(student);
        for (int i = 4; i < words.length; i+=2)
        {
          String courseName = words[i].trim();
          String teacherName = words[i+1].trim();
          Teacher teacher = model.getTeacherById(teacherName);
          model.getStudent(student).assignToCourse(model.getCourseByNameAndTeacher(courseName,teacher));
        }
      }
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    finally
    {
      in.close();
    }

  }


}
