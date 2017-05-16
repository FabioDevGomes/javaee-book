package br.com.casadocodigo.loja.infra;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.Part;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;

@RequestScoped
public class FileSaver {

	@Inject
	private AmazonS3Client s3;
	private static final String CONTENT_DISPOSITION = "content-disposition";

	public String writer(String baseFolder, Part multpartFile) {
		//String serverPath = request.getServletContext().getRealPath("/" + baseFolder);
		//String path = serverPath + "/" + fileName;

		String fileName = extractFileName(multpartFile.getHeader(CONTENT_DISPOSITION));

		try {
			s3.putObject("casadocodigo", fileName, multpartFile.getInputStream(),
					new ObjectMetadata());

			return "http://localhost:9444/s3/casadocodigo/"+ baseFolder +"_"+ fileName +"?noAuth=true";
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
	

}
