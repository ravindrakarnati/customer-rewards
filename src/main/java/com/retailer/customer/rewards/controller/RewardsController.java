package com.retailer.customer.rewards.controller;

import com.retailer.customer.rewards.model.CustomerRewardsResponse;
import com.retailer.customer.rewards.service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class RewardsController {

    @Autowired
    private RewardsService customerService;

    @GetMapping("/getRewardPoints")
    public ResponseEntity<List<CustomerRewardsResponse>> getCustomerRewardPointsForAllCustomers() {
        List<CustomerRewardsResponse> response = customerService.getCustomerRewardPoints();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
