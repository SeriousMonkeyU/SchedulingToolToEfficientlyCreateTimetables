import javafx.application.Application;
import javafx.stage.Stage;
import mediator.SchedulingManager;
import mediator.SchedulingManagerModel;
import view.ViewHandler;

public class MyApplication extends Application
{
  @Override public void start(Stage primaryStage) throws Exception
  {
    SchedulingManagerModel model = new SchedulingManager();
    ViewHandler view = new ViewHandler(model);
    view.start(primaryStage);
  }
}
