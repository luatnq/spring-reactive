package com.example.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillDTO implements Serializable {
    private String id;

    @JsonProperty("orders")
    private List orders;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("address")
    private String address;

    @JsonProperty("total_money")
    private long totalMoney;

    @JsonProperty("uid")
    private String uid;

    @JsonProperty("phone_number")
    private String phoneNumber;
}
