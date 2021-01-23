import controller.Controller;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import view.RootLayoutController;

import java.io.IOException;

import static javafx.application.Application.launch;

/**
 * Main class of the app. Initializes the views, variables and controller
 */
public class main extends Application {
    public Stage primaryStage;
    private AnchorPane rootLayout;
    private Controller ctrl = new Controller();

    /**
     * main function to launch the app
     * @param args
     */
    public static void main(String[] args) {
        //Deck deck = new Deck();
        //deck.printDeck();
        launch(args);
    }

    /**
     * Function that is called from launch. Sets the views and initializes the stage.
     * @param primaryStage is the primary stage set for the view
     */
    public void start(Stage primaryStage) {
        // Building the User Interface
        try {

            primaryStage.setOnCloseRequest(new EventHandler<>() {
                @Override
                public void handle(WindowEvent t) {
                    Platform.exit();
                    System.exit(0);
                }
            });
            this.primaryStage = primaryStage;
            this.primaryStage.setTitle("Casinosovellus");

            initRootLayout();
            showLauncher();

            // Set the application icon. (These can be used later)

            //Image icon = new Image(getClass().getResource("img/....png").toExternalForm());
            //this.primaryStage.getIcons().add(icon);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads up the launcher view and sets the controller
     */
    public void showLauncher() {
        try {
            // Load the launcher's FXML file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(main.class.getResource("/Launcher.fxml"));
            AnchorPane launcher = loader.load();


            //rootLayout.setCenter(launcher);

            //LauncherController controller = loader.getController();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads up the root layout and sets the controller, shows the primary stage
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(main.class.getResource("/RootLayout.fxml"));
            rootLayout = loader.load();
            RootLayoutController controller = loader.getController();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
