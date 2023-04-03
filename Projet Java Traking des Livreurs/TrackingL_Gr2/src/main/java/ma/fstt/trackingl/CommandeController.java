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
import ma.fstt.model.Commande;
import ma.fstt.model.CommandeDAO;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CommandeController implements Initializable {


    @FXML
    private TextField nom_produit ;


    @FXML
    private TextField nom_livreur ;

    @FXML
    private TextField datedebut ;

    @FXML
    private TextField datefin ;

    @FXML
    private TextField nomm ;


    @FXML
    private TableView<Commande> mytable ;


    @FXML
    private TableColumn<Commande ,Long> col_id ;

    @FXML
    private TableColumn <Commande ,Long> col_produit ;

    @FXML
    private TableColumn <Commande ,Long> col_livreur ;

    @FXML
    private TableColumn <Commande ,String> col_debut ;

    @FXML
    private TableColumn <Commande ,String> col_fin ;

    public CommandeController() {
    }


    @FXML
    protected void onSaveButtonClick() {
        Commande newCommande = new Commande();
        newCommande.setNom_produit(String.valueOf(nom_produit.getText()));
        newCommande.setNom_livreur(String.valueOf(nom_livreur.getText()));
        newCommande.setDatedebut(datedebut.getText());
        newCommande.setDatefin(datefin.getText());

        try {
            CommandeDAO commandeDAO = new CommandeDAO();
            commandeDAO.save(newCommande);
            UpdateTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private Button search;




    @FXML
    protected void onSearchButtonClick(ActionEvent event) {
        String searchID = nomm.getText();

        try {
            CommandeDAO commandeDAO = new CommandeDAO();
            ObservableList<Commande> commandeList = FXCollections.observableArrayList();
            for (Commande ettemp : commandeDAO.getAll()) {
                if (ettemp.getId_commande().equals(searchID)) {
                    commandeList.add(ettemp);
                }
            }
            mytable.setItems(commandeList);
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
            stage.setTitle("Tracking Des Livreurs");
            stage.show();


        }catch (Exception e) {
            // TODO: handle exception e.printStackTrace();
        }
    }

    @FXML
    private void onDeleteButtonClick() {
        Commande selectedCommande = mytable.getSelectionModel().getSelectedItem();
        if (selectedCommande == null) {
            // No item selected, display error message
            System.out.println("Error: no item selected for deletion");
            return;
        }
        try {
            CommandeDAO commandeDAO = new CommandeDAO();
            commandeDAO.delete(selectedCommande);
            UpdateTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onUpdateButtonClick() {
        // Get the currently selected Commande from the table
        Commande selectedCommande = mytable.getSelectionModel().getSelectedItem();
        if (selectedCommande == null) {
            // No item selected, display error message
            System.out.println("Error: no item selected for update");
            return;
        }

        // Update the fields of the selected Commande with the values entered in the input fields
        selectedCommande.setNom_produit(String.valueOf(nom_produit.getText()));
        selectedCommande.setNom_livreur(String.valueOf(nom_livreur.getText()));
        selectedCommande.setDatedebut(datedebut.getText());
        selectedCommande.setDatefin(datefin.getText());

        try {
            CommandeDAO commandeDAO = new CommandeDAO();
            commandeDAO.update(selectedCommande);
            UpdateTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<>("id_commande"));
        col_produit.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
        col_livreur.setCellValueFactory(new PropertyValueFactory<>("nom_livreur"));
        col_debut.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
        col_fin.setCellValueFactory(new PropertyValueFactory<>("datefin"));

        mytable.setItems(getDataCommandes());
    }

    public static ObservableList<Commande> getDataCommandes(){

        CommandeDAO commandeDAO = null;

        ObservableList<Commande> listfx = FXCollections.observableArrayList();

        try {
            commandeDAO = new CommandeDAO();
            for (Commande ettemp : commandeDAO.getAll())
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