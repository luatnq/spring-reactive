package com.example.reviewservice.entity;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.Instant;

@Document(collection = "review")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review implements Serializable {
    @Id
    private String id;

    @Field("content")
    private String content;

    @Field("rating")
    private int rating;

    @Field("username")
    private String username;

    @Field("product_id")
    private String productId;

    @Field("created_at")
    @CreatedDate
    private Instant createdAt = Instant.now();

    @Field("updated_at")
    @LastModifiedDate
    private Instant updateAt;
}
