package ma.fstt.trackingl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ma.fstt.model.Livreur;
import ma.fstt.model.LivreurDAO;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HelloController implements Initializable {


    @FXML
    private TextField nom ;


    @FXML
    private TextField tele ;

    @FXML
    private TextField nomm ;


    @FXML
    private TableView<Livreur> mytable ;


    @FXML
    private TableColumn<Livreur ,Long> col_id ;

    @FXML
    private TableColumn <Livreur ,String> col_nom ;

    @FXML
    private TableColumn <Livreur ,String> col_tele ;


    @FXML
    protected void onSaveButtonClick() {

        // accees a la bdd

        try {
            LivreurDAO livreurDAO = new LivreurDAO();

            Livreur liv = new Livreur(0l , nom.getText() , tele.getText());

            livreurDAO.save(liv);


            UpdateTable();




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    private Button search;

    @FXML
    void onSearchButtonClick(ActionEvent event) {
        onSearchButtonClick();
    }

    @FXML
    protected void onSearchButtonClick() {
        String searchName = nomm.getText();

        try {
            LivreurDAO livreurDAO = new LivreurDAO();
            ObservableList<Livreur> livreurList = FXCollections.observableArrayList();
            for (Livreur ettemp : livreurDAO.getAll()) {
                if (ettemp.getNom().equals(searchName)) {
                    livreurList.add(ettemp);

                }
            }
            mytable.setItems(livreurList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    private Button button;
    @FXML
    void AfficherAccueil(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("accueil-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 672, 600);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Welcome to Tracking Des Livreurs");
            stage.show();


        }catch (Exception e) {
            // TODO: handle exception e.printStackTrace();
        }
    }

    @FXML
    private void onDeleteButtonClick() {
        Livreur selectedLivreur = mytable.getSelectionModel().getSelectedItem();
        if (selectedLivreur == null) {
            // No item selected, display error message
            System.out.println("Error: no item selected for deletion");
            return;
        }
        try {
            LivreurDAO livreurDAO = new LivreurDAO();
            livreurDAO.delete(selectedLivreur);
            UpdateTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onUpdateButtonClick() {

        Livreur selecteLivreur = mytable.getSelectionModel().getSelectedItem();

        if (selecteLivreur == null) {
            // No item selected, display error message
            System.out.println("Error: no item selected for update");
            return;
        }

        try {
            LivreurDAO livreurDAO = new LivreurDAO();

            // Update the client  object with the new data
            selecteLivreur.setNom(nom.getText());
            selecteLivreur.setTelephone(tele.getText());

            livreurDAO.update(selecteLivreur);

            UpdateTable();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<Livreur,Long>("id_livreur"));
        col_nom.setCellValueFactory(new PropertyValueFactory<Livreur,String>("nom"));

        col_tele.setCellValueFactory(new PropertyValueFactory<Livreur,String>("telephone"));

        mytable.setItems(this.getDataLivreurs());
    }

    public static ObservableList<Livreur> getDataLivreurs(){

        LivreurDAO livreurDAO = null;

        ObservableList<Livreur> listfx = FXCollections.observableArrayList();

        try {
            livreurDAO = new LivreurDAO();
            for (Livreur ettemp : livreurDAO.getAll())
                listfx.add(ettemp);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listfx ;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();

    }
}