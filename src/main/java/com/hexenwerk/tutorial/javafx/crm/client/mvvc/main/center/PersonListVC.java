package com.hexenwerk.tutorial.javafx.crm.client.mvvc.main.center;

import com.hexenwerk.tutorial.javafx.crm.ResourceBundleUtil;
import com.hexenwerk.tutorial.javafx.crm.client.StageManager;
import com.hexenwerk.tutorial.javafx.crm.client.ViewController;
import com.hexenwerk.tutorial.javafx.crm.client.mvvc.login.LoginPaneVC;
import com.hexenwerk.tutorial.javafx.crm.client.mvvc.main.left.PersonDetailMV;
import com.hexenwerk.tutorial.javafx.crm.service.PersonService;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Component
@FxmlView("PersonList.fxml")
public class PersonListVC implements ViewController {

    private final StageManager stageManager;
    private final PersonListMV personListViewModel;
    private final PersonService personService;

    @FXML
    Button btnLogout;

    @FXML
    public TableView<PersonDetailMV> tableView;

    @FXML
    TableColumn<PersonDetailMV, Long> colUserId;
    @FXML
    TableColumn<PersonDetailMV, String> colFirstName;
    @FXML
    TableColumn<PersonDetailMV, String> colLastName;
    @FXML
    TableColumn<PersonDetailMV, LocalDate> colDOB;
    @FXML
    TableColumn<PersonDetailMV, String> colGender;
    @FXML
    TableColumn<PersonDetailMV, String> colRole;
    @FXML
    TableColumn<PersonDetailMV, String> colEmail;
    @FXML
    TableColumn<PersonDetailMV, Boolean> colEdit;

    @FXML
    private MenuItem deleteUsers;

    public PersonListVC(StageManager stageManager, PersonListMV personListViewModel, PersonService personService) {
        this.stageManager = stageManager;
        this.personListViewModel = personListViewModel;
        this.personService = personService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        personListViewModel.bind(this);
        createTableColumns();

        btnLogout.setOnAction(event -> stageManager.displayScene(LoginPaneVC.class, ResourceBundleUtil.getValue("loginPane.title")));
        deleteUsers.setOnAction(event -> {
            List<PersonDetailMV> users = this.tableView.getSelectionModel().getSelectedItems();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete selected?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) personService.deleteInBatch(users.stream().map(PersonDetailMV::getModel).collect(Collectors.toList()));
        });
    }

    @Override
    public void clear() {
        this.tableView.getItems().clear();
    }

    private void createTableColumns() {
        // set column properties
        this.colUserId.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        this.colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        this.colDOB.setCellValueFactory(new PropertyValueFactory<>("birthDay"));
        this.colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        this.colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        this.colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        this.colEdit.setCellFactory(new Callback<>() {
            @Override
            public TableCell<PersonDetailMV, Boolean> call(final TableColumn<PersonDetailMV, Boolean> param) {
                return new TableCell<>() {
                    final Image imgEdit = new Image(getClass().getResourceAsStream("/images/edit.png"));
                    final Button btnEdit = new Button();

                    @Override
                    public void updateItem(Boolean check, boolean empty) {
                        super.updateItem(check, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            //                        btnEdit.setOnAction(e -> userDetailPaneManager.refreshUserPane(getTableView().getItems().get(getIndex())));
                            btnEdit.setStyle("-fx-background-color: transparent;");
                            ImageView iv = new ImageView();
                            iv.setImage(imgEdit);
                            iv.setPreserveRatio(true);
                            iv.setSmooth(true);
                            iv.setCache(true);
                            btnEdit.setGraphic(iv);

                            setGraphic(btnEdit);
                            setAlignment(Pos.CENTER);
                            setText(null);
                        }
                    }
                };
            }
        });
    }

}
