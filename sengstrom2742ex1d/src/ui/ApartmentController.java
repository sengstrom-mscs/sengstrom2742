package ui;

import domain.Apartment;
import domain.DbContext;
import domain.Invoice;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ApartmentController {
    @FXML
    public ComboBox aptComboBox;
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
    @FXML
    public ComboBox adminComboBox;
    @FXML
    public ComboBox tenantComboBox;

    private ArrayList<Apartment> apartments = new ArrayList<Apartment>();

    public ApartmentController() {
        this.apartments = DbContext.getApartments();
    }

    @FXML
    protected void initialize(){
        for (Apartment apartment:this.apartments){
            aptComboBox.getItems().add(apartment.toShortString());
        }
        aptComboBox.getSelectionModel().selectFirst();

        Apartment apartment = this.apartments.get(0);
        this.displayApartment(apartment);
    }

    @FXML
    private void displayApartment(Apartment apartment){
        this.aptNumTextField.setText(Integer.toString(apartment.getApartmentId()));
        this.squareFeetTextField.setText(Integer.toString(apartment.getSquareFeet()));
        this.bathroomsTextField.setText(Integer.toString(apartment.getBathrooms()));
        this.priceTextField.setText(String.format("0.2f",apartment.getPrice()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        this.updatedTextField.setText(apartment.getUpdated().format(formatter));
    }

    @FXML
    public void aptComboBox_ItemSelected(ActionEvent actionEvent) {
    }

    @FXML
    public void saveAptButton_Clicked(ActionEvent actionEvent) {
    }

    @FXML
    public void viewInvoiceDigButton_Clicked(ActionEvent actionEvent) {
    }
}
