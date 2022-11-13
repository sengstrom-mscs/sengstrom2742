package ui;

import domain.DbContext;
import domain.Invoice;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        ArrayList<Invoice> invoices = DbContext.getInvoices();
        for(Invoice invoice: invoices){
            System.out.println(invoice.toShortString());}
    }
}