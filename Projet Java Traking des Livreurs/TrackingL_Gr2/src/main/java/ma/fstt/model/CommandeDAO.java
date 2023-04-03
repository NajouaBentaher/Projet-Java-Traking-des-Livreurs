package ma.fstt.model;

import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



        import java.sql.SQLException;
        import java.util.ArrayList;
        import java.util.List;

public class CommandeDAO extends BaseDAO<Commande>{
    public CommandeDAO() throws SQLException {

        super();
    }

    @Override
    public void save(Commande object) throws SQLException {

        // Check if the product exists
        String checkProductQuery = "SELECT COUNT(*) FROM produit WHERE id_produit = ?";
        PreparedStatement checkProductStatement = this.connection.prepareStatement(checkProductQuery);
        checkProductStatement.setString(1, object.getNom_produit());
        ResultSet resultSet = checkProductStatement.executeQuery();
        resultSet.next();
        int productCount = resultSet.getInt(1);

        if (productCount == 0) {
            // Product does not exist, display an alert
            Alert alert = new Alert(Alert.AlertType.ERROR, "N'existe pas ! ");
            alert.showAndWait();

        }


        // Product exists, insert the command into the database
        String insertCommandeQuery = "INSERT INTO commande (nom_produit, nom_livreur, datedebut, datefin) VALUES (?, ?, ?, ?)";
        this.preparedStatement = this.connection.prepareStatement(insertCommandeQuery);
        // mapping
        this.preparedStatement.setString(1, object.getNom_produit());
        this.preparedStatement.setString(2, object.getNom_livreur());
        this.preparedStatement.setString(3, object.getDatedebut());
        this.preparedStatement.setString(4, object.getDatefin());

        this.preparedStatement.execute();
    }

    @Override
    public void update(Commande object) throws SQLException {
        String request = "UPDATE commande SET nom_produit = ?, nom_livreur = ?, datedebut = ?, datefin = ? WHERE id_commande = ?";

        this.preparedStatement = this.connection.prepareStatement(request);

        this.preparedStatement.setString(1, object.getNom_produit());
        this.preparedStatement.setString(2, object.getNom_livreur());
        this.preparedStatement.setString(3, object.getDatedebut());
        this.preparedStatement.setString(4, object.getDatefin());
        this.preparedStatement.setLong(5, object.getId_commande());

        this.preparedStatement.executeUpdate();

        this.preparedStatement.close();
    }


    @Override
    public void delete(Commande object) throws SQLException {
        String req = "DELETE FROM commande WHERE id_commande = ?;";

        this.preparedStatement = this.connection.prepareStatement(req);
        this.preparedStatement.setLong(1, object.getId_commande());
        this.preparedStatement.execute();
    }

    @Override
    public List<Commande> getAll()  throws SQLException {

        List<Commande> mylist = new ArrayList<Commande>();

        String request = "select c.id_commande, p.nom_produit, l.nom , c.datedebut, c.datefin from commande c, produit p, livreur l where c.nom_produit = p.id_produit and c.nom_livreur = l.id_livreur";


        this.statement = this.connection.createStatement();

        this.resultSet =   this.statement.executeQuery(request);

// parcours de la table
        while ( this.resultSet.next()){

// mapping table objet
            mylist.add(new Commande(this.resultSet.getLong(1) ,
                    this.resultSet.getString(2) , this.resultSet.getString(3) , this.resultSet.getString(4) , this.resultSet.getString(5)));
        }

        return mylist;
    }

    @Override
    public Commande getOne(Long id_commande) throws SQLException {

        return null;
    }

}
