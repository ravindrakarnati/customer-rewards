package com.retailer.customer.rewards.dao;

import com.retailer.customer.rewards.model.CustomerTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Repository
public class CustomerTransactionDao {
    private List<CustomerTransaction> customerTransactions = new ArrayList<>();

    @Autowired
    public void CustomerTransactionDao() {
        CustomerTransaction transaction1 = new CustomerTransaction( 1, LocalDate.of(2021, 12, 12), 120);
        CustomerTransaction transaction2 = new CustomerTransaction( 1, LocalDate.of(2021, 11, 26), 70);
        CustomerTransaction transaction3 = new CustomerTransaction( 1, LocalDate.of(2021, 10, 27), 100);
        CustomerTransaction transaction4 = new CustomerTransaction( 1, LocalDate.of(2021, 10, 30), 70);
        CustomerTransaction transaction5 = new CustomerTransaction( 2, LocalDate.of(2021, 11, 12), 70);
        this.customerTransactions.add(transaction1);
        this.customerTransactions.add(transaction2);
        this.customerTransactions.add(transaction3);
        this.customerTransactions.add(transaction4);
        this.customerTransactions.add(transaction5);
    }

    public List<CustomerTransaction> getAllCustomersData() {
        return this.customerTransactions;
    }

}
