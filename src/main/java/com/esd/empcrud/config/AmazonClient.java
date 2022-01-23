package com.esd.empcrud.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;

@Service
public class AmazonClient {

    private AmazonS3 s3Client;

    @Value("${aws.accessKeyID}")
    private String accessKeyId;

    @Value("${aws.secretAccessKey}")
    private String secretAccessKey;

    @Value("${aws.bucket}")
    private String bucketName;

    @Value("${aws.endpoint}")
    private String endpoint;

    @PostConstruct
    private void init(){
        AWSCredentials credentials=new BasicAWSCredentials(accessKeyId,secretAccessKey);
        s3Client=new AmazonS3Client(credentials);
    }

    public void uploadFile(String fileName, File file){
        s3Client.putObject(new PutObjectRequest(bucketName,fileName,file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    public S3ObjectInputStream fetchFile(String fileName){
        S3Object obj=s3Client.getObject(new GetObjectRequest(bucketName,fileName));
        S3ObjectInputStream is=obj.getObjectContent();
        return is;
    }
}
