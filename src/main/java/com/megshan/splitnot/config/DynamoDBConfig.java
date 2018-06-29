/*
 * Copyright (c) 2018 LogMeIn
 * All Rights Reserved Worldwide.
 *
 * THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO LOGMEIN
 * AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.megshan.splitnot.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Shantanu on 6/28/18.
 */
@Configuration
public class DynamoDBConfig {

    @Autowired
    private ApplicationProperties applicationProperties;

    @Bean
    public DynamoDBMapper dynamoDBMapper() {
        DynamoDBMapperConfig dynamoDBMapperConfig = new DynamoDBMapperConfig.Builder().build();
        return new DynamoDBMapper(amazonDynamoDBClient(), dynamoDBMapperConfig);
    }

    private AmazonDynamoDB amazonDynamoDBClient() {
        return AmazonDynamoDBClientBuilder.standard()
                .withCredentials(awsCredentialsProvider())
                .withRegion(Regions.US_WEST_2).build();
    }

    private AWSCredentialsProvider awsCredentialsProvider() {
        String accessKeyId = applicationProperties.getDynamoDB().getAccessKeyId();
        String secretAccessKey = applicationProperties.getDynamoDB().getSecretAccessKey();
        BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKeyId, secretAccessKey);
        return new AWSStaticCredentialsProvider(basicAWSCredentials);
    }
}
