package com.retailer.customer.rewards.model;

import lombok.Data;

import java.util.Map;

@Data
public class CustomerRewardsResponse {

    private Map<String, Integer> customerRewardPointsPerMonth;
    private Integer totalRewardPoints;
    private Integer customerId;

}