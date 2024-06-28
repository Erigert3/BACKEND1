package com.hendisantika.springbootrestapipostgresql.controller;

import com.hendisantika.springbootrestapipostgresql.entity.Developer;
import com.hendisantika.springbootrestapipostgresql.entity.Project;
import com.hendisantika.springbootrestapipostgresql.service.DeveloperService;
import com.hendisantika.springbootrestapipostgresql.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AdminController {

    @Autowired
    private DeveloperService developerService;

    @Autowired
    private ProjectService projectService;

    @GetMapping("/developers")
    public ResponseEntity<List<Developer>> getAllDevelopers() {
        return ResponseEntity.ok(developerService.findAll());
    }

    @GetMapping("/developers/{id}")
    public ResponseEntity<Developer> getDeveloperById(@PathVariable Long id) {
        Optional<Developer> developer = developerService.findById(id);
        return developer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/developers")
    public ResponseEntity<Developer> createDeveloper(@RequestBody Developer developer) {
        if (developer.getProjects() != null) {
            for (Project project : developer.getProjects()) {
                project.setDeveloper(developer);
            }
        }
        Developer savedDeveloper = developerService.save(developer);
        return ResponseEntity.ok(savedDeveloper);
    }

    @PutMapping("/developers/{id}")
    public ResponseEntity<Developer> updateDeveloper(@PathVariable Long id, @RequestBody Developer developerDetails) {
        Optional<Developer> optionalDeveloper = developerService.findById(id);

        if (optionalDeveloper.isPresent()) {
            Developer developer = optionalDeveloper.get();
            developer.setName(developerDetails.getName());
            developer.setSurname(developerDetails.getSurname());
            developer.setEmail(developerDetails.getEmail());
            developer.setExperienceField(developerDetails.getExperienceField());
            developer.setYearsOfExperience(developerDetails.getYearsOfExperience());
            developer.setSkills(developerDetails.getSkills());
            developer.setProfilePicture(developerDetails.getProfilePicture());

            if (developerDetails.getProjects() != null) {
                for (Project project : developerDetails.getProjects()) {
                    project.setDeveloper(developer);
                }
                developer.setProjects(developerDetails.getProjects());
            }

            Developer updatedDeveloper = developerService.save(developer);
            return ResponseEntity.ok(updatedDeveloper);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/developers/{id}")
    public ResponseEntity<Void> deleteDeveloper(@PathVariable Long id) {
        developerService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/developers/{id}/projects")
    public ResponseEntity<List<Project>> getDeveloperProjects(@PathVariable Long id) {
        Optional<Developer> developer = developerService.findById(id);
        return developer.map(d -> ResponseEntity.ok(d.getProjects())).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/developers/{id}/projects")
    public ResponseEntity<Project> addProjectToDeveloper(@PathVariable Long id, @RequestBody Project project) {
        Optional<Developer> optionalDeveloper = developerService.findById(id);

        if (optionalDeveloper.isPresent()) {
            Developer developer = optionalDeveloper.get();
            project.setDeveloper(developer);
            Project savedProject = projectService.save(project);
            developer.getProjects().add(savedProject);
            developerService.save(developer);
            return ResponseEntity.ok(savedProject);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/projects/{projectId}")
    public ResponseEntity<Project> updateProject(@PathVariable Long projectId, @RequestBody Project projectDetails) {
        Optional<Project> optionalProject = projectService.findById(projectId);

        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            project.setStartDate(projectDetails.getStartDate());
            project.setEndDate(projectDetails.getEndDate());
            project.setIndustry(projectDetails.getIndustry());
            project.setCompanyName(projectDetails.getCompanyName());
            project.setTitle(projectDetails.getTitle());
            project.setDescription(projectDetails.getDescription());
            Project updatedProject = projectService.save(project);
            return ResponseEntity.ok(updatedProject);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
