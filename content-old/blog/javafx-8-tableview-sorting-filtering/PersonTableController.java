package ch.makery.sortfilter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


/**
 * View-Controller for the person table.
 * 
 * @author Marco Jakob
 */
public class PersonTableController {
	
	@FXML
	private TextField filterField;
	@FXML
	private TableView<Person> personTable;
	@FXML
	private TableColumn<Person, String> firstNameColumn;
	@FXML
	private TableColumn<Person, String> lastNameColumn;

	private ObservableList<Person> masterData = FXCollections.observableArrayList();

	/**
	 * Just add some sample data in the constructor.
	 */
	public PersonTableController() {
		masterData.add(new Person("Hans", "Muster"));
		masterData.add(new Person("Ruth", "Mueller"));
		masterData.add(new Person("Heinz", "Kurz"));
		masterData.add(new Person("Cornelia", "Meier"));
		masterData.add(new Person("Werner", "Meyer"));
		masterData.add(new Person("Lydia", "Kunz"));
		masterData.add(new Person("Anna", "Best"));
		masterData.add(new Person("Stefan", "Meier"));
		masterData.add(new Person("Martin", "Mueller"));
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 * 
	 * Initializes the table columns and sets up sorting and filtering.
	 */
	@FXML
	private void initialize() {
		// 0. Initialize the columns.
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
		
		// 1. Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList<Person> filteredData = new FilteredList<>(masterData, p -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(person -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (person.getFirstName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches first name.
				} else if (person.getLastName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Person> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(personTable.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		personTable.setItems(sortedData);
	}
}
