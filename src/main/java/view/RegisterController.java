package view;

import java.io.IOException;
import java.sql.SQLException;

import controller.SettingsController;
import controller.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Window;

/**
 *
 * Register Controller for fxml ui
 *
 */

public class RegisterController {

    public ImageView blackScreen;
    public Text errorText;
    public Button okButton;
    public Text registerText;
    public Button volumeOFFbutton;
    public Button volumeONbutton;
    private UserController userController = new UserController(this);

    @FXML
    public TextField usernameTextField;

    @FXML
    public TextField passwordTextField;

    @FXML
    public TextField repeatTextField;

    @FXML
    public Button submitButton;

    private StageManager stageManager;

    /**
     * Initializes stageManager
     */
    public void initialize() {
        stageManager = StageManager.getInstance();
    }

    @FXML
    private Button changePasswordButton;


    @FXML
    /**
     * Registers a new user
     */

    public void register(ActionEvent event) throws SQLException {

        Window owner = usernameTextField.getScene().getWindow();
        System.out.println(usernameTextField.getText());
        System.out.println(passwordTextField.getText());

        if (usernameTextField.getText().isEmpty()) {
            setErrorMessage("Please enter a username");
        }
        else if (passwordTextField.getText().isEmpty()) {
            setErrorMessage("Please enter a password");
        }
        else if (repeatTextField.getText().isEmpty()) {
            setErrorMessage("Please repeat password");
        }
        else  {
            String username = usernameTextField.getText();
            String password1 = passwordTextField.getText();
            String password2 = repeatTextField.getText();

            if (userController.tryToCreateNewUser(username, password1, password2)) {

                userController.login(username, password1);

                blackScreen.setVisible(true);
                registerText.setVisible(true);
                okButton.setVisible(true);
            }
        }

        passwordTextField.setText("");
        repeatTextField.setText("");

    }


    public void setErrorMessage(String message) {
        errorText.setText(message);
        blackScreen.setVisible(true);
        errorText.setVisible(true);
        okButton.setVisible(true);
    }

    /**
     * Loads back to Mainmenu
     * @throws IOException
     */
    public void backToMainMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/MainMenu.fxml"));
        Parent menuParent = loader.load();
        MainMenuController controller = loader.getController();
        if (userController.isUserLoggedIn()) {
            controller.loginButton.setVisible(false);
            controller.registerButton.setVisible(false);
            controller.logoutButton.setVisible(true);
            controller.changePasswordButton.setVisible(true);
        }
        Scene menuScene = new Scene(menuParent);
        stageManager.getPrimaryStage().setTitle("The Grand Myllypuro");
        stageManager.getPrimaryStage().setScene(menuScene);
        stageManager.getPrimaryStage().show();

    }


    /**
     * Button closes pop up screen and loads to MainMenu if registeration is successed
     * @throws IOException
     */
    public void okButton() throws IOException {
        if (registerText.isVisible()) {
            backToMainMenu();
        }
        blackScreen.setVisible(false);
        errorText.setVisible(false);
        okButton.setVisible(false);
    }


    /**
     * Mutes game music
     */
    public void volumeOFF() {
        volumeOFFbutton.setVisible(false);
        volumeONbutton.setVisible(true);
        stageManager.getMediaPlayer().setVolume(0);
    }

    /**
     * Turns game music back ON
     */
    public void volumeON() {
        volumeONbutton.setVisible(false);
        volumeOFFbutton.setVisible(true);
        stageManager.getMediaPlayer().setVolume(SettingsController.getInstance().getVolume());
    }


}