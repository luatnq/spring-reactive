package com.example.orderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Document("bill")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Bill implements Serializable {
    @Id
    private String id;

    @Field("orders")
    private List orders;

    @Field("full_name")
    private String fullName;

    @Field("email")
    private String email;

    @Field("address")
    private String address;

    @Field("phone_number")
    private String phoneNumber;

    @Field("total_money")
    private long totalMoney;

    @Field("created_at")
    private Instant createdAt = Instant.now();

    @Field("updated_at")
    private Instant updatedAt;

    @Field("uid")
    private String uid;
}
