package com.example.reviewservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO implements Serializable {


    private String id;

    @JsonProperty("content")
    private String content;

    @JsonProperty("rating")
    private int rating;

    @JsonProperty("username")
    private String username;

    @JsonProperty("product_id")
    private String productId;

    @JsonProperty("created_at")
    @CreatedDate
    private Instant createdAt = Instant.now();

    @JsonProperty("updated_at")
    @LastModifiedDate
    private Instant updateAt;
}
