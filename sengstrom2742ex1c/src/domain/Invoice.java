package domain;

import java.util.ArrayList;

public class Invoice {
    private int invoiceId;
    private int status;
    private GDate invoiceDate;
    private GDate dueDate;
    private ArrayList<LineItem> lineItems = new ArrayList<LineItem>();

    public Invoice(int status, GDate invoiceDate, GDate dueDate) {
        this.invoiceId = DbContext.getNextInvoiceId();
        this.status = status;
        this.invoiceDate = new GDate(invoiceDate);
        this.dueDate = new GDate(dueDate);
    }

    public Invoice(Invoice invoice) {
        this.invoiceId = invoice.invoiceId;
        this.status = invoice.status;
        this.invoiceDate =  new GDate(invoice.invoiceDate);
        this.dueDate = new GDate(invoice.dueDate);
//        this.lineItems = invoice.lineItems;
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

    public GDate getInvoiceDate() {
        return invoiceDate.copy();
    }

    public GDate getDueDate() {
        return dueDate.copy();
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId=" + invoiceId +
                ", status=" + status +
                ", invoiceDate=" + invoiceDate +
                ", dueDate=" + dueDate +
                '}';
    }
    public String toShortString() {
        return Integer.toString(invoiceId) +
                ", status:" + status +
                ", " + invoiceDate;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setInvoiceDate(GDate invoiceDate) {
        this.invoiceDate = invoiceDate.copy();
    }

    public void setDueDate(GDate dueDate) {
        this.dueDate = dueDate.copy();
    }
}
