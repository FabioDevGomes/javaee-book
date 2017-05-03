package br.com.casadocodigo.loja.infra;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

@RequestScoped
public class FileSaver {
	
	@Inject
	private HttpServletRequest request;
	private static final String CONTENT_DISPOSITION = "content-disposition";
	
	public String writer(String baseFolder, Part multpartFile){
		String serverPath = request.getServletContext().getRealPath("/"+ baseFolder);
		String fileName = extractFileName(multpartFile.getHeader(CONTENT_DISPOSITION));
		String path = serverPath +"/"+ fileName;
		
		try {
			multpartFile.write(path);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return baseFolder +"/"+ fileName;
	}
	
	private String extractFileName(String contentDisposition){
		if(contentDisposition == null){
			return null;
		}
		String fileNameKey = "filename=";
		int startIndex = contentDisposition.indexOf(fileNameKey) + fileNameKey.length();
		if(startIndex == -1){
			return null;
		}
		String fileName = contentDisposition.substring(startIndex, contentDisposition.length());
		if(fileName.startsWith("\"")){
			int endIndex = fileName.indexOf("\"", 1);
			if(endIndex != -1){
				return fileName.substring(1, endIndex);
			}
		}else{
			int endIndex = fileName.indexOf(";");
			if(endIndex != -1){
				return fileName.substring(0, endIndex);
			}
		}
		return fileName;
	}

	
}
