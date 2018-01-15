package com.ramon.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.datapipeline.model.Field;
import com.ramon.services.S3Services;

import antlr.StringUtils;


@RestController
@RequestMapping("/v0")
public class HomeController {

	@Autowired
	S3Services s3Services;
	
	@Value("${jsa.s3.uploadfile}")
	private String uploadFilePath;
	
	@Value("${jsa.s3.key}")
	private String downloadKey;

	@GetMapping
	@ResponseBody
	public String index()
	{
		s3Services.uploadFile("arquivo_subido.pdf", uploadFilePath);
		return "Olá";
	}
	@GetMapping("/testa")
	public void testa()
	{
		s3Services.downloadFile("arquivo_subido.pdf");
	}
	
	@PostMapping
	public void recebeArquivo(@RequestParam("file") MultipartFile uploadfiles)
	{
		File file=new File(uploadfiles.getOriginalFilename());
		try {
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(uploadfiles.getBytes());
			fos.close();
			if(file!=null)
				s3Services.uploadFile(UUID.randomUUID().toString(),file);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

}
