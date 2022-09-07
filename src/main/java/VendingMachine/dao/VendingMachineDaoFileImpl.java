package VendingMachine.dao;

import VendingMachine.dto.Item;
import org.springframework.stereotype.Component;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Ryan Taylor
 * @created 02/07/2022 - 01:41
 * Class is responsible for any retrieval of information. CANNOT ACCESS THE VIEW.
 */

@Component
public class VendingMachineDaoFileImpl implements VendingMachineDao {
    private Map<String, Item> items = new HashMap<>();
    public static final String DELIMITER = "::";
    public final String VENDING_MACHINE_TXT = "VendingMachineInventory.txt";


    @Override
    public List<Item> getItems() throws VendingMachinePersistenceException {
        loadInventory();
        return new ArrayList<>(items.values());
    }


    @Override
    public int updateItem(String itemId) throws VendingMachinePersistenceException {
        loadInventory();
        int prevInv = items.get(itemId).getInInventory();
        int remo = items.get(itemId).setInInventory(prevInv-1);
        writeInventory();
        return remo;
    }


    private void loadInventory() throws VendingMachinePersistenceException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(VENDING_MACHINE_TXT)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException("-_- Could not load roster data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentStudent holds the most recent student unmarshalled
        Item currentItem;
        // Go through ROSTER_FILE line by line, decoding each line into a
        // Student object by calling the unmarshallStudent method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a Student
            currentItem = unmarshallItem(currentLine);

            // We are going to use the student id as the map key for our student object.
            // Put currentStudent into the map using student id as the key
            items.put(currentItem.getItemId(), currentItem);
        }
        // close scanner
        scanner.close();
    }

    private Item unmarshallItem(String itemAsText){
        // itemAsText is expecting a line read in from our file.
        // For example, it might look like this:
        // 1234::Ada::Lovelace::Java-September1842
        //
        // We then split that line on our DELIMITER - which we are using as ::
        // Leaving us with an array of Strings, stored in itemTokens.
        // Which should look like this:
        // ______________________________________
        // |    |   |        |                  |
        // |1234|Ada|Lovelace|Java-September1842|
        // |    |   |        |                  |
        // --------------------------------------
        //  [0]  [1]    [2]         [3]
        String[] itemTokens = itemAsText.split(DELIMITER);

        // Given the pattern above, the student Id is in index 0 of the array.
        String itemId = itemTokens[0];

        // Which we can then use to create a new Student object to satisfy
        // the requirements of the Student constructor.
        Item itemFromFile = new Item(itemId);

        // However, there are 3 remaining tokens that need to be set into the
        // new student object. Do this manually by using the appropriate setters.

        // Index 1 - FirstName
        itemFromFile.setName(itemTokens[1]);

        // Index 2 - LastName
        itemFromFile.setCost(new BigDecimal(itemTokens[2]));

        // Index 3 - Cohort
        itemFromFile.setInInventory(Integer.parseInt(itemTokens[3]));

        // We have now created a student! Return it!
        return itemFromFile;
    }

    private String marshallItem(Item anItem){
        // We need to turn a Student object into a line of text for our file.
        // For example, we need an in memory object to end up like this:
        // 4321::Charles::Babbage::Java-September1842

        // It's not a complicated process. Just get out each property,
        // and concatenate with our DELIMITER as a kind of spacer.

        // Start with the student id, since that's supposed to be first.
        String studentAsText = anItem.getItemId() + DELIMITER;

        // add the rest of the properties in the correct order:

        // FirstName
        studentAsText += anItem.getName() + DELIMITER;

        // LastName
        studentAsText += anItem.getCost() + DELIMITER;

        // Cohort - don't forget to skip the DELIMITER here.
        studentAsText += anItem.getInInventory();

        // We have now turned a student to text! Return it!
        return studentAsText;
    }

    public void writeInventory() throws VendingMachinePersistenceException {
        // NOTE FOR APPRENTICES: We are not handling the IOException - but
        // we are translating it to an application specific exception and
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us.  It is the responsibility of the calling code to
        // handle any errors that occur.
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(VENDING_MACHINE_TXT));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException(
                    "Could not save student data.", e);
        }

        // Write out the Student objects to the roster file.
        // NOTE TO THE APPRENTICES: We could just grab the student map,
        // get the Collection of Students and iterate over them but we've
        // already created a method that gets a List of Students so
        // we'll reuse it.
        String studentAsText;
        List<Item> itemList = this.getItems();
        for (Item currentItem : itemList) {
            // turn a Student into a String
            studentAsText = marshallItem(currentItem);
            // write the Student object to the file
            out.println(studentAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

}
