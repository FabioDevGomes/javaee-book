package br.com.casadocodigo.loja.infra;

import javax.enterprise.context.RequestScoped;
import javax.servlet.http.Part;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;
import com.amazonaws.services.s3.model.ObjectMetadata;

@RequestScoped
public class FileSaver {

	//	@Inject
	//	private HttpServletRequest request;
//	@Inject
//	private AmazonS3Client s3;
	private static final String CONTENT_DISPOSITION = "content-disposition";

	public String writer(String baseFolder, Part multpartFile) {
		//String serverPath = request.getServletContext().getRealPath("/" + baseFolder);
		// String path = serverPath + "/" + fileName;

		AmazonS3Client s3 = client();
		String fileName = extractFileName(multpartFile.getHeader(CONTENT_DISPOSITION));

		try {
			s3.putObject("casadocodigo", fileName, multpartFile.getInputStream(),
					new ObjectMetadata());

			return "https://s3.amazonaws.com/casadocodigo/"+ fileName +"?noAuth=true";
			// multpartFile.write(path);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private String extractFileName(String contentDisposition) {
		if (contentDisposition == null) {
			return null;
		}
		String fileNameKey = "filename=";
		int startIndex = contentDisposition.indexOf(fileNameKey) + fileNameKey.length();
		if (startIndex == -1) {
			return null;
		}
		String fileName = contentDisposition.substring(startIndex,
				contentDisposition.length());
		if (fileName.startsWith("\"")) {
			int endIndex = fileName.indexOf("\"", 1);
			if (endIndex != -1) {
				return fileName.substring(1, endIndex);
			}
		} else {
			int endIndex = fileName.indexOf(";");
			if (endIndex != -1) {
				return fileName.substring(0, endIndex);
			}
		}
		return fileName;
	}

	private AmazonS3Client client() {
		AWSCredentials awsCredentials = new BasicAWSCredentials("AKIAIOSFODNN7EXAMPLE",
				"wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY");

		AmazonS3Client newClient = new AmazonS3Client(awsCredentials,
				new ClientConfiguration());

		newClient.setS3ClientOptions(new S3ClientOptions().withPathStyleAccess(true));
		newClient.setEndpoint("http://localhost:9444/s3");

		return newClient;
	}

}
