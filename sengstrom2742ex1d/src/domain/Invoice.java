package domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;


public class Invoice {
    private int invoiceId;
    private int status;
    private LocalDateTime invoiceDate;
    private LocalDateTime dueDate;
    private ArrayList<LineItem> lineItems = new ArrayList<LineItem>();

    private LineItem lineItem;
    private Apartment apartment = null;


    public Invoice(int status, LocalDateTime invoiceDate, LocalDateTime dueDate, Apartment apartment) {
        this.invoiceId = DbContext.getNextInvoiceId();
        this.status = status;
        this.invoiceDate = invoiceDate;
        this.dueDate = dueDate;
        this.apartment = apartment;
    }

    public Invoice(Invoice invoice) {
        this.invoiceId = invoice.invoiceId;
        this.status = invoice.status;
        this.invoiceDate = invoice.invoiceDate;
        this.dueDate = invoice.dueDate;
//        this.lineItems = invoice.lineItems;
        this.apartment = invoice.apartment;
        for (LineItem lineItem : invoice.lineItems) {
            this.lineItems.add(lineItem.copy());}
    }

    public Invoice copy() {
        return new Invoice(this);
    }

    public void addLineItem(LineItem lineItem) {
        this.lineItems.add(new LineItem(lineItem));
    }

    public void addLineItem(int index, LineItem lineItem){
        this.lineItems.add(index, new LineItem(lineItem));
    }

    public LineItem removeLineItem(int index) {
        LineItem lineItem = null;
        if (index >=0 && index < this.lineItems.size()){
            lineItem = this.lineItems.get(index).copy();
            this.lineItems.remove(index);
        }
        return lineItem;
    }

    public LineItem removeLineItem(LineItem lineItem) {
        LineItem removedLineItem = null;
        int index = this.lineItems.indexOf(lineItem);

        if(lineItems.indexOf(lineItem) != -1){
            removedLineItem = this.lineItems.get(index).copy();
            this.lineItems.remove(lineItem.copy());
        }
        return removedLineItem;
    }

    public double total() {
        double total = 0.0;
        for (int i = 0; i < lineItems.size(); i++) {
            double amount = lineItems.get(i).getAmount();
            total += amount;
        }
        return total;
    }

    public LineItem getLineItem (int index) {
        LineItem lineitem = null;
        if (index< this.lineItems.size())
            lineitem = lineItems.get(index).copy();
        return lineitem;
    }
    public ArrayList<LineItem> getLineItems() {
        ArrayList<LineItem> lineItems = new ArrayList<LineItem>();
        for(LineItem lineItem : this.lineItems) {
            lineItems.add(lineItem.copy());
        }
        return lineItems;
    }




    public int getInvoiceId() {
        return invoiceId;
    }

    public int getStatus() {
        return status;
    }

    public LocalDateTime getInvoiceDate() {
        return invoiceDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return "Invoice{" +
                "invoiceId=" + invoiceId +
                ", status=" + status +
                ", invoiceDate=" + invoiceDate.format(formatter) +
                ", dueDate=" + dueDate.format(formatter) +
                '}';
    }
    public String toShortString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return Integer.toString(invoiceId) +
                ", status:" + status +
                ", " + invoiceDate.format(formatter);
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setInvoiceDate(LocalDateTime invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return getInvoiceId() == invoice.getInvoiceId() &&
                getStatus() == invoice.getStatus() &&
                this.invoiceDate.equals(invoice.invoiceDate) &&
                this.dueDate.equals(invoice.dueDate);
    }


//    @Override
//    public int hashCode() {
//        return Objects.hash(getInvoiceId(), getStatus(), getInvoiceDate(), getDueDate());
//    }
}
