package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;

@Document(collection = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EnableMongoAuditing
public class Product implements Serializable {
    @Id
    private String id;

    @Field("uid")
    private String uid;

    @Field("product_name")
    private String productName;

    @Field("category")
    private String publisherName;

    @Field("price")
    private long price;

    @Field("rating")
    private double rating;

    @Field("imageURL")
    private String imageURL;

    @Field("updated_date")
    @CreatedDate
    @JsonIgnore
    private LocalDateTime updatedDate = LocalDateTime.now();

    @Field("created_date")
    @LastModifiedDate
    @JsonIgnore
    private LocalDateTime createdDate ;
}
