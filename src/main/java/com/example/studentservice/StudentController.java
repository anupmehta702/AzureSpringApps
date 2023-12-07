package com.example.studentservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/")
public class StudentController {

    @Autowired
    StudentRepository repository;

    Logger log = LoggerFactory.getLogger(StudentController.class);

    @Value("${deployed.from}")
    private String deployedFrom;

    @Value("${db.source}")
    private String dbSource;

    @RequestMapping(value = "/echoStudentName/{name}")
    public String echoStudentName(@PathVariable(name = "name") String name) {
        log.info("Logging name "+name+" from -->"+deployedFrom);
        return "Hello " + name + " .Welcome to Azure spring apps. Current time is :: " + new Date()+" and deployed from ::"+deployedFrom+ " using DB :: "+dbSource;
    }

    @PostMapping()
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        log.info("DB is set to -->"+dbSource);
        repository.save(student);
        return ResponseEntity.ok(student);
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent(@PathVariable(name = "id") Integer id){
        log.info("getting student details from DB -->"+dbSource);
        return ResponseEntity.ok(repository.findById(id).get());
    }
    @RequestMapping(value = "/getStudentDetails/{name}")
    public Student getStudentDetails(@PathVariable(name = "name") String name) {
        log.info("Logging name "+name+" from -->"+deployedFrom);
        return new Student(name, "Pune", "MCA");
    }

}
