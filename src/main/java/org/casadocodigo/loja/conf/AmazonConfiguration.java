package org.casadocodigo.loja.conf;

import org.springframework.context.annotation.Bean;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;

public class AmazonConfiguration {

	private final String ACCESS_KEY = "";
	private final String SECRET_KEY = "";

	@Bean
	private AmazonS3 amazonS3client() {
		
		//Credenciais de acesso
		AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
		
		AmazonS3 s3Client = new AmazonS3Client(credentials);
		s3Client.setS3ClientOptions(new S3ClientOptions().withPathStyleAccess(true));
		
		s3Client.setEndpoint("http://springmvccasadocodigo.s3.amazonaws.com/");
		
		return s3Client;
	}

}
