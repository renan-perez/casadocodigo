package org.com.casadocodigo.loja.infra;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Component
public class FileServer {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private AmazonS3 s3;

	public String write(String baseFolder, MultipartFile file) {
		String realPath = request.getServletContext().getRealPath(
				"/" + baseFolder);

		try {
			String path = realPath + "/" + file.getOriginalFilename();
			file.transferTo(new File(path));

			return baseFolder + "/" + file.getOriginalFilename();

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public String writeAmazonS3(String baseFolder, MultipartFile multpartFile) {

		try {
			
			Bucket bucket = new Bucket("springmvccasadocodigo");

			s3.putObject(bucket.getName(),
					multpartFile.getOriginalFilename(),
					multpartFile.getInputStream(), new ObjectMetadata());

			return "https://springmvccasadocodigo.s3.amazonaws.com/"
					+ multpartFile.getOriginalFilename() + "?noAuth=true";

		} catch (AmazonClientException | IOException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
