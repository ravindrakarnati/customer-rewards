package com.retailer.customer.rewards.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.retailer.customer.rewards.model.CustomerRewardsResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RewardsControllerTest {

    @Autowired
    TestRestTemplate restTemplate;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testGetCustomerRewardPointsForAllCustomers() {
        URI targetUrl= UriComponentsBuilder.fromUriString("/api/customers/getRewardPoints")
                .build()
                .encode()
                .toUri();

        List<CustomerRewardsResponse> responses = this.restTemplate.getForObject(targetUrl, List.class);
        List<CustomerRewardsResponse> customerRewardsResponseList = objectMapper.convertValue(
                responses,
                new TypeReference<List<CustomerRewardsResponse>>(){}
        );
        assertEquals(customerRewardsResponseList.get(0).getTotalRewardPoints(), 180);
        assertEquals(customerRewardsResponseList.get(1).getTotalRewardPoints(), 20);
        assertEquals(customerRewardsResponseList.get(1).getCustomerRewardPointsPerMonth().size(), 1);
    }




}