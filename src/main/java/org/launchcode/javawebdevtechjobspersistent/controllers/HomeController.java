package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Employer;
import org.launchcode.javawebdevtechjobspersistent.models.Job;
import org.launchcode.javawebdevtechjobspersistent.models.Skill;
import org.launchcode.javawebdevtechjobspersistent.models.data.EmployerRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.JobRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {
    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private JobRepository jobRepository;

    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("title", "My Jobs");

        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        model.addAttribute("skills", skillRepository.findAll());
        model.addAttribute("employers", employerRepository.findAll());
        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                    Errors errors, Model model, @RequestParam int employerId, @RequestParam List<Integer> skills) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            model.addAttribute("skills", skillRepository.findAll());
            model.addAttribute("employers", employerRepository.findAll());
            return "add";
        }

        List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
        newJob.setSkills((List<Skill>) skillObjs);

        Optional optJob = employerRepository.findById(employerId);
        if (optJob.isPresent()) {
            Employer employer = (Employer) optJob.get();
            newJob.setEmployer(employer);
        }

            jobRepository.save(newJob);
            return "view";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {
        Optional optNew = jobRepository.findById(jobId);
        if (optNew.isPresent()) {
            Job job = (Job) optNew.get();
            model.addAttribute("job", job);
            return "view";
        } else {
            return "redirect:";
        }
    }
}

