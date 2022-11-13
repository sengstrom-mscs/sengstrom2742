package ui;

import domain.Invoice;
import domain.LineItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class InvoiceController {
    @FXML
    public TextField invoiceIdTextField;
    @FXML
    public TextField statusTextField;
    @FXML
    public TextField invoiceDateTextField;
    @FXML
    public TextField dueDateTextField;
    //    @FXML
//    public ComboBox invoicesComboBox;
    @FXML
    public ComboBox<Invoice> invoicesComboBox;
    //    @FXML
//    public ListView lineItemsListView;
    @FXML
    public ListView<LineItem> lineItemsListView;
    @FXML
    public TextField descriptionTextField;
    @FXML
    public TextField amountTextField;
    @FXML
    public TextField totalTextField;

    private ArrayList<Invoice> invoices = new ArrayList<Invoice>();


//    public InvoiceController() {
//        this.invoices = DbContext.getInvoices();
//    }

    public InvoiceController() { }

    public  void initData(ArrayList<Invoice> invoices){
        this.invoices = invoices;
    }
    @FXML
    private void displayInvoice(Invoice invoice){
        this.invoiceIdTextField.setText(Integer.toString(invoice.getInvoiceId()));
        this.statusTextField.setText(Integer.toString(invoice.getStatus()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        this.invoiceDateTextField.setText(invoice.getInvoiceDate().format(formatter));
        this.dueDateTextField.setText(invoice.getDueDate().format(formatter));
    }
    @FXML
    private void displayLineItems(LineItem lineItem) {
        this.descriptionTextField.setText(lineItem.getDescription());
        this.amountTextField.setText(Double.toString(lineItem.getAmount()));
    }

    @FXML
    protected void initialize(){
        if (this.invoices.size()>0){
            for (Invoice invoice:this.invoices){
                invoicesComboBox.getItems().add(invoice);
            }
            this.invoices = null;
            invoicesComboBox.getSelectionModel().selectFirst();
            Invoice invoice = invoicesComboBox.getSelectionModel().getSelectedItem();
            this.displayInvoice(invoice);
            this.displayLineItems(invoice);
        }
    }

    @FXML
    public void invoiceComboBoxItemSelected(ActionEvent actionEvent) {
        int index = invoicesComboBox.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            Invoice invoice = this.invoices.get(index);
            this.displayInvoice(invoice);
            this.displayLineItems(invoice);
        }
    }

    @FXML
    private void displayLineItems(Invoice invoice){
        //Clear lineItemView
        this.lineItemsListView.getItems().clear();

        //Fill LineItemView
        ArrayList<LineItem> lineItems = invoice.getLineItems();
        for (LineItem lineItem: lineItems) {
            this.lineItemsListView.getItems().add(lineItem);
        }
        //Display LineItem
        this.descriptionTextField.setText("");
        this.amountTextField.setText("");
        this.lineItemsListView.getSelectionModel().selectFirst();
//        if (lineItems.size() >0 ) {
//            displayLineItems(lineItems.get(0));
//        }
        //ex1e
        LineItem lineItem = lineItemsListView.getSelectionModel().getSelectedItem();
        if (lineItem != null) {
            displayLineItem(lineItem);
        }
        //Display total
        this.totalTextField.setText((String.format("0.2f", invoice.total())));
    }

    @FXML
    private void displayLineItem(LineItem lineItem) {
        this.descriptionTextField.setText(lineItem.getDescription());
        this.amountTextField.setText(String.format("0.2f", lineItem.getAmount()));


    }

    @FXML
    public void lineItemsListViewClicked(MouseEvent mouseEvent) {
        //get lineItems
        ArrayList<LineItem> lineItems = invoicesComboBox.getSelectionModel().getSelectedItem().getLineItems();

        //get selected LineItem
        int lineItemIndex = this.lineItemsListView.getSelectionModel().getSelectedIndex();
        LineItem lineItem = lineItems.get(lineItemIndex);

        displayLineItems(lineItem);
    }

    @FXML
    public void saveInvoiceButtonClicked(ActionEvent actionEvent) {
        int index = this.invoicesComboBox.getSelectionModel().getSelectedIndex();
        invoices.get(index).getLineItems();
        Invoice invoice = this.invoices.get(index);
        int invoiceStatus = Integer.parseInt(this.statusTextField.getText());
        invoice.setStatus(invoiceStatus);


//        LocalDateTime invoiceDate = new LocalDateTime(
//                Integer.parseInt(this.invoiceDateTextField.getText().substring(0, 4)),
//                Integer.parseInt(this.invoiceDateTextField.getText().substring(5, 7)),
//                Integer.parseInt(this.invoiceDateTextField.getText().substring(8, 10)));
//
//        invoice.setInvoiceDate(invoiceDate);
//
//        LocalDateTime invoiceDueDate = new LocalDateTime(
//                Integer.parseInt(this.dueDateTextField.getText().substring(0, 4)),
//                Integer.parseInt(this.dueDateTextField.getText().substring(5, 7)),
//                Integer.parseInt(this.dueDateTextField.getText().substring(8, 10)));
//
//        invoice.setDueDate(invoiceDueDate);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//        invoice.setInvoiceDate((LocalDateTime.parse(this.invoiceDateTextField.getText(),formatter)));
        LocalDate date;
        date = LocalDate.parse(this.invoiceDateTextField.getText(), formatter);
        invoice.setInvoiceDate(date.atStartOfDay());
//        invoice.setDueDate((LocalDateTime.parse(this.dueDateTextField.getText(),formatter)));
        date = LocalDate.parse(this.dueDateTextField.getText(), formatter);
        invoice.setDueDate(date.atStartOfDay());


        this.invoicesComboBox.getItems().remove(index);
        this.invoicesComboBox.getItems().add(invoice);
        this.invoicesComboBox.getSelectionModel().select(index);

    }

    @FXML
    public void saveLineItemButtonClicked(ActionEvent actionEvent) {
        int invoiceIndex = invoicesComboBox.getSelectionModel().getSelectedIndex();
        Invoice invoice = this.invoices.get(invoiceIndex);
        int lineItemIndex = lineItemsListView.getSelectionModel().getSelectedIndex();
        invoice.removeLineItem(lineItemIndex);
//        LineItem lineItem = (LineItem) this.lineItemsListView.getItems().get(lineItemIndex);
        LineItem lineItem = new LineItem(Double.parseDouble(
                this.amountTextField.getText()),
                this.descriptionTextField.getText().trim());
        invoice.addLineItem(lineItemIndex, lineItem);

        this.lineItemsListView.getItems().remove(lineItemIndex);
        this.lineItemsListView.getItems().add(lineItemIndex, lineItem);
        this.lineItemsListView.getSelectionModel().select(lineItemIndex);
        this.totalTextField.setText(String.format("0.2f", invoice.total()));

    }

    @FXML
    public void addLineItemButtonClicked(ActionEvent actionEvent) {
        int invoiceIndex = invoicesComboBox.getSelectionModel().getSelectedIndex();
        Invoice invoice = this.invoices.get(invoiceIndex);
        int lineItemIndex = lineItemsListView.getSelectionModel().getSelectedIndex();
        LineItem lineItem = new LineItem(0.0, "");
        invoice.addLineItem(lineItemIndex, lineItem);
        this.lineItemsListView.getItems().add(lineItemIndex, lineItem);
        lineItemsListView.getSelectionModel().selectLast();
        lineItemsListView.scrollTo(lineItemIndex-1);
        this.displayLineItems(lineItem);
        descriptionTextField.requestFocus();

        this.totalTextField.setText(String.format("0.2f", invoice.total()));

    }

    @FXML
    public void deleteLineItemButtonClicked(ActionEvent actionEvent) {
        this.descriptionTextField.setText("");
        this.amountTextField.setText("");

        int invoiceIndex = this.invoicesComboBox.getSelectionModel().getSelectedIndex();
        if (invoiceIndex >= 0) {
            Invoice invoice = this.invoices.get(invoiceIndex);
            int lineItemIndex = lineItemsListView.getSelectionModel().getSelectedIndex();

            if(lineItemIndex >= 0){
                invoice.removeLineItem(lineItemIndex);
                this.lineItemsListView.getItems().remove(lineItemIndex);
                this.lineItemsListView.getSelectionModel().selectLast();
//                lineItemIndex = this.lineItemsListView.getSelectionModel().getSelectedIndex();
//                if (lineItemIndex >=0) {
//                    this.lineItemsListView.scrollTo(lineItemIndex);
//                    LineItem lineItem = invoice.getLineItem(lineItemIndex);
//                    displayLineItems(lineItem);
//                }
                LineItem lineItem = this.lineItemsListView.getSelectionModel().getSelectedItem();
                if (lineItem != null) {
                    this.lineItemsListView.scrollTo(lineItemIndex);
                    displayLineItem(lineItem);
                }
                this.totalTextField.setText(String.format("0.2f", invoice.total()));
            }
        }
        //LineItem lineItem = new LineItem(0.0, "");



    }
}
