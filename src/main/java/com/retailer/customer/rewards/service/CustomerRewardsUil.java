package com.retailer.customer.rewards.service;



import com.retailer.customer.rewards.model.CustomerTransaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CustomerRewardsUil {

    private static Function<CustomerTransaction, Integer> pointCalculation = a -> {
        Integer amount = a.getTransactionAmount();
        if (amount > 100) {
            return (amount - 100) * 2 + 50;
        } else if (amount > 50) {
            return (amount - 50);
        }
        return 0;
    };

    public static Map<String, Integer> getCustomerRewardPointsByMonth(List<CustomerTransaction> customerTransactions) {
        Map<String, List<CustomerTransaction>> transactionsByMonth = getTransactionsByMonth(customerTransactions);
        Map<String, Integer> rewardPointsByMonth = new HashMap<>();

        for (Map.Entry<String, List<CustomerTransaction>> entry : transactionsByMonth.entrySet()) {
            rewardPointsByMonth.put(entry.getKey(),
                    entry.getValue().stream().map(pointCalculation).reduce(0, Integer::sum));
        }
        return rewardPointsByMonth;
    }

    public static Map<String, List<CustomerTransaction>> getTransactionsByMonth(List<CustomerTransaction> customerTransactions) {
        return customerTransactions.stream()
                .collect(Collectors.groupingBy(e -> e.getTransactionDate().getMonth().toString()));
    }
}