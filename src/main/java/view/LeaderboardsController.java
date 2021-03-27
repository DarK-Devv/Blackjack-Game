package view;

import controller.UserController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.CasinoDAO;
import model.User;
import model.UserCredentialHandler;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class LeaderboardsController {
    public Text loggedUser;
    public Button volumeOFFbutton;
    public Button volumeONbutton;
    public TableView<User> leaderboardTable;
    public TableColumn rankingColumn;
    public TableColumn usernameColumn;
    public TableColumn balanceColumn;
    public TableColumn roundColumn;
    private StageManager stageManager;
    private UserController userController = new UserController();
    CasinoDAO casinoDAO = new CasinoDAO();

    /**
     * Initializes stageManager
     * Checks the current volume state
     */
    public void initialize() {
        stageManager = StageManager.getInstance();
        checkVolume();
        if (UserCredentialHandler.getInstance().getLoggedInUser() != null) {
            loggedUser.setText("Logged in as: " + UserCredentialHandler.getInstance().getLoggedInUser().getUsername());
        }
        setTable();
    }

    /**
     * Loads back to Main menu
     * @throws IOException - if .fxml file is not found
     */
    public void leaderboardsBackButton () throws IOException {
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
     * Sets rankings to the leaderboardsTableView
     */
    public void setTable() {
        rankingColumn.setCellValueFactory(new PropertyValueFactory<>("ranking"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        balanceColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));
        roundColumn.setCellValueFactory(new PropertyValueFactory<>("rounds"));

        leaderboardTable.setItems(getLeaderboards());
    }

    /**
     * Gets every row from UsersTable in database to Observablelist
     * @return - Returns Observablelist
     */
    public ObservableList<User> getLeaderboards() {
        ObservableList<User> leaderboards = FXCollections.observableArrayList();
        User[] u = casinoDAO.getAllUserRows();
        leaderboards.addAll(Arrays.asList(u));

        Comparator<User> comparator = Comparator.comparingInt(User::getBalance);
        comparator = comparator.reversed();
        FXCollections.sort(leaderboards, comparator);

        for (int i=0; i<leaderboards.size(); i++ ) {
            leaderboards.get(i).setRanking(i+1);
            casinoDAO.updateUser(leaderboards.get(i));
        }

        return leaderboards;
    }

    /**
     * Mutes game music
     */
    public void volumeOFF () {
        volumeOFFbutton.setVisible(false);
        volumeONbutton.setVisible(true);
        stageManager.getMediaPlayer().setVolume(0);
    }

    /**
     * Turns game music back ON
     */
    public void volumeON () {
        volumeONbutton.setVisible(false);
        volumeOFFbutton.setVisible(true);
        stageManager.getMediaPlayer().setVolume(0.25);
    }

    /**
     * Checks is the volume ON or OFF
     */
    public void checkVolume () {
        if (stageManager.getMediaPlayer().getVolume() == 0) {
            volumeOFF();
        } else {
            volumeON();
        }
    }
}
