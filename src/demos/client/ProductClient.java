/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package demos.client;

import demos.db.Product;
import demos.model.ProductManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import javax.persistence.OptimisticLockException;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author cristina
 */
public class ProductClient {

    private static final Logger logger = Logger.getLogger(ProductClient.class.getName());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ProductManager pm = new ProductManager("ProductClientPU");
            
            //Pag 47. Punto 4
            //Product p = pm.findProduct(1);
            //System.out.println(p);
            
            //Pag 47. Punto 6
            List<Product> products = pm.findProductByName("Co%");
            products.stream().forEach(p->System.out.println(p));
            
            //Pag 48. Punto 9
            Product p = pm.findProduct(1);
            p.setPrice(BigDecimal.valueOf(2.5));
            p.setBestBefore(LocalDate.now().plusDays(1));
            pm.update(p);
            
            //Pag 49. Punto 10 - PROBAR
            //p.setPrice(BigDecimal.valueOf(0.1));
            //p.setName("x");
            //p.setBestBefore(LocalDate.now().plusDays(1));
            //pm.update(p);
            
            //Pag 49. Punto 11
            p.setPrice(BigDecimal.valueOf(5));
            Scanner s = new Scanner(System.in);
            System.out.println("Click here and then press enter to contine");
            s.nextLine();
            pm.update(p);
            
            pm.closeEntityManager();

        } catch (Exception ex) {
            Throwable cause = ex.getCause();
            if (cause instanceof ConstraintViolationException) {
                ConstraintViolationException e = (ConstraintViolationException) cause;
                e.getConstraintViolations().stream().forEach(v -> logger.log(java.util.logging.Level.INFO, e.getMessage()));

            } else if (cause instanceof OptimisticLockException) {
                OptimisticLockException e = (OptimisticLockException) cause;
                logger.log(java.util.logging.Level.INFO, e.getMessage());
            } else {
                logger.log(java.util.logging.Level.SEVERE, "Product Manager Error", ex);
            }
        }
    }

}
