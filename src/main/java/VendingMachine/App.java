package VendingMachine;
import VendingMachine.controller.VendingMachineController;
import VendingMachine.dao.VendingMachineAuditDao;
import VendingMachine.dao.VendingMachineAuditFileImpl;
import VendingMachine.dao.VendingMachineDao;
import VendingMachine.dao.VendingMachineDaoFileImpl;
import VendingMachine.service.VendingMachineServiceLayer;
import VendingMachine.service.VendingMachineServiceLayerImpl;
import VendingMachine.ui.UserIO;
import VendingMachine.ui.UserIOConsoleImpl;
import VendingMachine.ui.VendingMachineView;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Ryan Taylor
 * @created 02/07/2022 - 00:12
 * App class acts as the ignition of the application.
 */
public class App {
    public static void main(String[] args)  {

        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("VendingMachine");
        appContext.refresh();

        VendingMachineController controller = appContext.getBean("vendingMachineController", VendingMachineController.class);
        controller.run();


        /*
        // Instantiate the UserIO implementation
        UserIO myIo = new UserIOConsoleImpl();
        // Instantiate the View and wire the UserIO implementation into it
        VendingMachineView myView = new VendingMachineView(myIo);
        // Instantiate the DAO
        VendingMachineDao myDao = new VendingMachineDaoFileImpl();
        // Instantiate the Audit DAO
        VendingMachineAuditDao myAuditDao = new VendingMachineAuditFileImpl();
        // Instantiate the Service Layer and wire the DAO and Audit DAO into it
        VendingMachineServiceLayer myService = new VendingMachineServiceLayerImpl(myDao, myAuditDao);
        // Instantiate the Controller and wire the Service Layer into it
        VendingMachineController controller = new VendingMachineController(myService, myView);
        // Kick off the Controller
        controller.run();

         */
    }
}
