package com.ramon.services;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.transfer.TransferManager;

@Service
public class S3ServiceImpl implements S3Services{

	@Autowired
	private AmazonS3 s3client;
 
	@Value("${jsa.s3.bucket}")
	private String bucketName;

	@Override
	public void downloadFile(String keyName) {
		try
		{
			S3Object s3object = s3client.getObject(new GetObjectRequest(bucketName, keyName));
			System.out.println("Content-Type: "  + s3object.getObjectMetadata().getContentType());
			
		}catch ( AmazonServiceException ase) {
			
		}catch(AmazonClientException ace)
		{
			
		}
		
	}

	@Override
	public void uploadFile(String keyName, String uploadFilePath) {
		
		try
		{
			File file = new File(uploadFilePath);
			s3client.createBucket(new CreateBucketRequest("pasta-aleatoria"));
			PutObjectRequest request =new  PutObjectRequest(bucketName, keyName, file);
			s3client.putObject(request);
			
			
		}catch (AmazonServiceException ase) {
			System.err.println(ase.getErrorMessage());
		}catch(AmazonClientException ace)
		{
			System.err.println(ace.getMessage());
		}
		
	}

	@Override
	public void uploadFile(String keyName, File file) {
		
		try
		{
			
		s3client.putObject(new PutObjectRequest(bucketName, keyName, file));
		
			
		}catch (AmazonServiceException ase) {
			System.err.println(ase.getErrorMessage());
			
		}catch(AmazonClientException ace)
		{
			System.err.println(ace.getMessage());
		}
	}
 
}
