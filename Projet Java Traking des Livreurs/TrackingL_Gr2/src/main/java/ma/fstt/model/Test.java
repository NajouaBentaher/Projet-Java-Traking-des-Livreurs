package ma.fstt.model;

import java.sql.SQLException;
import java.util.List;

public class Test {

    public static void main(String[] args) {
// trait bloc try catch
        try {

          LivreurDAO livreurDAO = new LivreurDAO();
          Livreur liv = new Livreur(0l , "liv3" , "200000000");
          livreurDAO.save(liv);

          Livreur livreur = new Livreur(12l, "liv1_modifié", "300000000");
          livreurDAO.update(livreur);

          Livreur livreur1 = new Livreur(16l, "liv16_modifié", "300590000");
          livreurDAO.update(livreur1);

          Livreur livreur01 = new Livreur(1l, "livreur 1", "06158795125");
          livreurDAO.update(livreur01);

            Livreur livreur11 = new Livreur(1l, "livreur 1", "06158795125");

            livreurDAO.delete(livreur11);


           /*List<Livreur> livlist =  livreurDAO.getAll();

            for (Livreur liv :livlist) {

                System.out.println(liv.toString());
            }*/

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
