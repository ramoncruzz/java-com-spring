package com.candangas.controllers.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.candangas.DAO.util.ArquivoDAO;
import com.candangas.helpers.JsonString;
import com.candangas.model.util.Arquivo;
import com.candangas.services.S3Services;

@RestController
@RequestMapping("/v0/arquivo")
public class ArquivoController {
	
	@Autowired
	private ArquivoDAO arquivoDao;
	@Autowired 
	private S3Services s3Services;
	@Value("${jsa.s3.mainlink}")
	private String mainLink;
	@Value("${jsa.s3.bucket}")
	private String bucket;
	
	@GetMapping(produces="application/json")
	public String listaTodosArquivos()
	{
		try
		{
			return JsonString.geraJsonArray(arquivoDao.findAll());
		}catch (Exception e) {
			// TODO: handle exception
			return JsonString.jsonErroMensagem( e.getMessage());
		}
	}
	
	@PostMapping(produces="application/json")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public String recebeArquivo(@RequestParam("file") MultipartFile uploadfiles)
	{
		try {
			File file=new File(uploadfiles.getOriginalFilename());
			Arquivo arquivoInf = geraObjetoArquivo(uploadfiles.getOriginalFilename());
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(uploadfiles.getBytes());
			fos.close();
			if(file!=null)
				s3Services.uploadFile(arquivoInf.getFullPathToSave(),file);
			arquivoDao.save(arquivoInf);
			return arquivoInf.toString();
			
		} catch (IOException e) {
			return JsonString.jsonErroMensagem( e.getMessage());
		}
	}
	
	
	
	private Arquivo geraObjetoArquivo(String fileName)
	{
		String[] splitted = fileName.split("\\.");
		Arquivo arquivo = new Arquivo();
		if(splitted.length==3)
		{
			arquivo.setDiretorio(splitted[0]);
			arquivo.setNome(UUID.randomUUID().toString());
			arquivo.setTipo(splitted[2]);
			arquivo.setLink(mainLink+"/"+bucket+"/"+arquivo.getFullPathToSave());
		}
		if(splitted.length==2)
		{
			arquivo.setNome(UUID.randomUUID().toString());
			arquivo.setTipo(splitted[1]);
			arquivo.setLink(mainLink+"/"+bucket+"/"+arquivo.getFullPathToSave());
		}
		
		
		return arquivo;
	}
	

}
