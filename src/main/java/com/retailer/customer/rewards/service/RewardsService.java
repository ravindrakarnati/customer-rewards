package com.retailer.customer.rewards.service;

import com.retailer.customer.rewards.dao.CustomerTransactionDao;
import com.retailer.customer.rewards.model.CustomerRewardsResponse;
import com.retailer.customer.rewards.model.CustomerTransaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RewardsService {

    @Autowired
    private CustomerTransactionDao customerTransactionDao;


    public List<CustomerRewardsResponse> getCustomerRewardPoints() {
        log.debug("Retrieving rewards point for all customers");

        List<CustomerTransaction> customerTransactions = customerTransactionDao.getAllCustomersData();


        if (customerTransactions != null && customerTransactions.size() > 0) {
            Map<Integer, List<CustomerTransaction>> customerTransactionMap = getCustomerTransactionMap(customerTransactions);
            return customerTransactionMap.entrySet().stream().map(entrySet-> getCustomerRewards(entrySet.getKey(),entrySet.getValue())).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

    private CustomerRewardsResponse getCustomerRewards(Integer customerId, List<CustomerTransaction> customerTransactions) {
        CustomerRewardsResponse response = new CustomerRewardsResponse();
        Map<String, Integer> rewardPointsByMonth = CustomerRewardsUil
                .getCustomerRewardPointsByMonth(customerTransactions);
        response.setCustomerId(customerId);
        response.setCustomerRewardPointsPerMonth(rewardPointsByMonth);
        response.setTotalRewardPoints(rewardPointsByMonth.values().stream().reduce(0, Integer::sum));
        return response;
    }

    public Map<Integer,List<CustomerTransaction>> getCustomerTransactionMap(List<CustomerTransaction> customerTransactions) {
        return customerTransactions.stream().collect(Collectors.groupingBy(customerTransaction -> customerTransaction.getCustomerId()));
    }
}
