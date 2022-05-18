/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demos.model;

import demos.db.Product;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.naming.InitialContext;
import javax.naming.Context;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author cristina
 */
public class ProductManagerEJB {

    private EntityManager em;
    private Query productNameQuery;
    private static final Logger logger = Logger.getLogger(ProductManager.class.getName());

    private ProductFacadeRemote productFacade;

    public ProductManagerEJB() {
        try {
            Properties props = new Properties();
            props.setProperty("java.naming.factory.initial",
                    "com.sun.enterprise.naming.SerialInitContextFactory");
            //props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
            //props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");

            // glassfish default port value will be 3700,
            //props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
            InitialContext ctx = new InitialContext(props);
            productFacade = (ProductFacadeRemote) ctx.lookup("java:global/ProductApp/ProductApp-ejb/"
                    + "ProductFacade!demos.model.ProductFacadeRemote");
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error initialising EJB reference", ex);
        }

    }

    public void create(Product product) {
        productFacade.create(product);
    }

    public void update(Product product) {
        productFacade.update(product);
    }

    public void delete(Product product) {
        productFacade.delete(product);
    }

    public Product findProduct(Integer id) {
        return productFacade.findProduct(id);
    }

    public List<Product> findProductByName(String name) {
        return productFacade.findProductByName(name);
    }

}
