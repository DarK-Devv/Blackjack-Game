package view;

import java.io.IOException;
import java.sql.SQLException;

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
 * Login Controller for fxml ui
 */
public class LoginController {

    public ImageView blackScreen;
    public Text errorText;
    public Button okButton;
    public Text loginText;
    public Button volumeOFFbutton;
    public Button volumeONbutton;
    private TextField usernameTextField;
    private TextField passwordTextField;
    private Button logInButton;
    private StageManager stageManager;
    private UserController userController = new UserController();

    /**
     * Initialized stageManager
     * Checks the current volume state
     */
    public void initialize(){
        stageManager = StageManager.getInstance();
        checkVolume();
    }

    @FXML
    /**
     * Login button action that logs user in
     */
    public void logInButton() {

        Window owner = logInButton.getScene().getWindow();

        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        if (!userController.login(username, password)) {
            blackScreen.setVisible(true);
            errorText.setVisible(true);
            okButton.setVisible(true);
        } else {
            blackScreen.setVisible(true);
            loginText.setVisible(true);
            okButton.setVisible(true);
        }

        passwordTextField.setText("");
    }

    /**
     * Loads back to MainMenu
     * @throws IOException - if .fxml file is not found
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
     * Closes pop up screen and if log in is successful it loads back to Main menu as a logged in user
     * @throws IOException
     */
    public void okButton() throws IOException {
        if (loginText.isVisible()) {
            backToMainMenu();
        }
        blackScreen.setVisible(false);
        errorText.setVisible(false);
        loginText.setVisible(false);
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
        stageManager.getMediaPlayer().setVolume(0.25);
    }

    /**
     * Checks is the volume ON or OFF
     */
    public void checkVolume() {
        if(stageManager.getMediaPlayer().getVolume() == 0) {
            volumeOFF();
        }else {
            volumeON();
        }
    }
}