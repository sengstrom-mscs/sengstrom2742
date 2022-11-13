package ui;

import domain.Apartment;
import domain.DbContext;
import domain.Invoice;
import domain.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ApartmentController {
    //    @FXML
//    public ComboBox aptComboBox; //ex1e
    @FXML
    public ComboBox<Apartment> aptComboBox;
    @FXML
    public TextField aptNumTextField;
    @FXML
    public TextField squareFeetTextField;
    @FXML
    public TextField bathroomsTextField;
    @FXML
    public TextField priceTextField;
    @FXML
    public TextField updatedTextField;
    //    @FXML
//    public ComboBox adminComboBox;
    @FXML
    private  ComboBox<Person> adminComboBox;    //ex1e
    //    @FXML
//    public ComboBox tenantComboBox;
    @FXML
    private ComboBox<Person> tenantComboBox;   //ex1e


//    private ArrayList<Apartment> apartments = new ArrayList<Apartment>();
//    private  ArrayList<Person> person = new ArrayList<Person>();

    public ApartmentController() {

//        this.apartments = DbContext.getApartments();
//        this.person = DbContext.getPeople();
    }

    @FXML
    protected void initialize(){
        ArrayList<Apartment> apartments = DbContext.getApartments();  //e1e
        for (Apartment apartment:apartments){
            aptComboBox.getItems().add(apartment);
        }
        aptComboBox.getSelectionModel().selectFirst();
//        Apartment apartment = apartments.get(0);  //ex1e

        ArrayList<Person> people = DbContext.getPeople();
        for (Person person:people){
//            this.adminComboBox.getItems().add(person.toShortString());
            this.adminComboBox.getItems().add(person);   //ex1e
        }
        for (Person person:people){
//            this.tenantComboBox.getItems().add(person.toShortString());
            this.tenantComboBox.getItems().add(person);  //ex1e
        }
        this.displayApartment(this.aptComboBox.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void displayApartment(Apartment apartment){
        this.aptNumTextField.setText(Integer.toString(apartment.getApartmentId()));
        this.squareFeetTextField.setText(Integer.toString(apartment.getSquareFeet()));
        this.bathroomsTextField.setText(Integer.toString(apartment.getBathrooms()));
        this.priceTextField.setText(String.format("0.2f",apartment.getPrice()));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        this.updatedTextField.setText(apartment.getUpdated(LocalDateTime.now()).format(formatter));

        int selectedIndex = -1;

//        for (int i = 0; i< this.person.size(); i++){  //ex1e
        for (int i = 0; i< this.adminComboBox.getItems().size(); i++){
//            Person person = this.person.get(i);  //ex1e
            Person person = this.adminComboBox.getItems().get(i);
            if (person.equals(apartment.getAdministrator())){
                selectedIndex = i;
            }
        }
        this.adminComboBox.getSelectionModel().select(selectedIndex);

        selectedIndex = -1;
        for (int i = 0; i< this.tenantComboBox.getItems().size(); i++){
//            Person person = this.person.get(i);  //ex1e
            Person person = this.tenantComboBox.getItems().get(i);

            if (person.equals(apartment.getTenant())){
                selectedIndex = i;
            }
        }
        this.tenantComboBox.getSelectionModel().select(selectedIndex);

    }

    @FXML
    public void aptComboBox_ItemSelected(ActionEvent actionEvent) {
        int selectedIndex = aptComboBox.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0){
            this.displayApartment(this.aptComboBox.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    public void saveAptButton_Clicked(ActionEvent actionEvent) {
        //Get selected apartment
        int selectedAptIndex = this.aptComboBox.getSelectionModel().getSelectedIndex();
        Apartment apartment = aptComboBox.getSelectionModel().getSelectedItem();
        ArrayList<Person> people = DbContext.getPeople();

        //Update Apartment Fields
        apartment.setApartmentNum(aptNumTextField.getText());
        apartment.setSquareFeet(Integer.parseInt(squareFeetTextField.getText()));
        apartment.setBathrooms((Integer.parseInt(bathroomsTextField.getText())));
        apartment.setPrice(Double.parseDouble(priceTextField.getText()));
        apartment.getUpdated(LocalDateTime.now());

        int selectedAdminIndex = this.adminComboBox.getSelectionModel().getSelectedIndex();
//        apartment.setAdministrator(people.get(selectedAdminIndex));
        apartment.setAdministrator(this.adminComboBox.getSelectionModel().getSelectedItem());


        int selectedTenantIndex = this.tenantComboBox.getSelectionModel().getSelectedIndex();
//        apartment.setTenant(people.get(selectedTenantIndex));
        apartment.setTenant(this.tenantComboBox.getSelectionModel().getSelectedItem());

        this.aptComboBox.getItems().remove(selectedAptIndex);
        this.aptComboBox.getItems().add(selectedAdminIndex, apartment);
        this.aptComboBox.getSelectionModel().select(selectedAptIndex);

    }

    @FXML
    public void viewInvoiceDigButton_Clicked(ActionEvent actionEvent) {
//        int selectedIndex = aptComboBox.getSelectionModel().getSelectedIndex();
//        if (selectedIndex >= 0){
//            Apartment apartment = apartments.get(selectedIndex);
        Apartment apartment = aptComboBox.getSelectionModel().getSelectedItem();
        if (apartment != null){
            ArrayList<Invoice> invoices = apartment.getInvoices();
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InvoiceView.fxml"));
                InvoiceController invoiceController = new InvoiceController();
                invoiceController.initData(invoices);
                fxmlLoader.setController(invoiceController);
                Pane pane = (Pane) fxmlLoader.load();

//        Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Invoice");
                stage.setScene(new Scene(pane, 380,400));
                stage.show();}
            catch (IOException e){
                e.printStackTrace();}
        }
    }
}