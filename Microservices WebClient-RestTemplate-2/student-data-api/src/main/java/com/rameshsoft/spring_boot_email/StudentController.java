package com.rameshsoft.spring_boot_email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private WebClient.Builder webClient;
	
	@GetMapping("/{studentId}")
	public ResponseEntity<ResponseData> getstudentData(@PathVariable("studentId") Long studentId){
		
		ResponseData response = new ResponseData();
		
		Student s1 = new Student();
		s1.setStudentId(1l);
		
		s1.setStudentName("Anurag");
		s1.setAddress("Secnderabad");
		s1.setCollegeId(1l);
		
		//s1.setCollegeId(); hear you have to call RestTemplate or WebClient
		
		response.setStudent(s1);
		
		Long collegeId = s1.getCollegeId(); 
		
		//RestTemplate	
		  RestTemplate restTemplate = new RestTemplate();
		  
		  String endPoint = "http://localhost:9012/college/{collegeId}";
		  ResponseEntity<College> data = restTemplate.getForEntity(endPoint, College.class, collegeId); 
		  if(data.getStatusCodeValue() == 200){
			  College c1 = data.getBody(); 		  
		  response.setCollege(c1); 
		  
		  }
		 
		
		//Webclient
//		College c1 = webClient.build()
//				.get()
//				.uri("http://localhost:9012/college/"+collegeId)
//				.retrieve()
//				.bodyToMono(Coll ege.class)
//				.block();
//		
//		response.setCollege(c1);
		  
		return new ResponseEntity<ResponseData>(response,HttpStatus.OK);
	}

}
