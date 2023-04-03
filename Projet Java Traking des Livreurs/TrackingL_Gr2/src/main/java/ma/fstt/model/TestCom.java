package ma.fstt.model;

import java.sql.SQLException;

        import java.sql.SQLException;
        import java.util.List;

public class TestCom {

    public static void main(String[] args) {
// trait bloc try catch
        try {

            CommandeDAO commandeDAO = new CommandeDAO();
            Commande com = new Commande(0l , "produ" , "aicha" , "02/02/2022" , "06/02/2030");
            commandeDAO.save(com);

            Commande commande = new Commande(12l,"produ" , "aicha" , "02/02/2022" , "06/02/2030");
            commandeDAO.update(commande);

            Commande commande1 = new Commande(16l, "produ" , "aicha" , "02/02/2022" , "06/02/2030");
            commandeDAO.update(commande1);

            Commande commande01 = new Commande(1l, "produ" , "aicha" , "02/02/2022" , "06/02/2030");
            commandeDAO.update(commande01);

            Commande commande11 = new Commande(1l, "produ" , "aicha" , "02/02/2022" , "06/02/2030");

                    commandeDAO.delete(commande11);


           /*List<Produit> livlist =  produitDAO.getAll();

            for (Produit prod :livlist) {

                System.out.println(prod.toString());
            }*/

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}

