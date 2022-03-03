package com.mb.mvc.api;

import com.mb.mvc.data.entity.Developer;
import com.mb.mvc.data.entity.Skill;
import com.mb.mvc.data.repository.DeveloperRepository;
import com.mb.mvc.data.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class DeveloperController {

    private static final String DEVELOPER = "developer";
    private static final String DEVELOPERS = "developers";
    private static final String SKILLS = "skills";

    private final DeveloperRepository repository;
    private final SkillRepository skillRepository;

    @RequestMapping("/developer/{id}")
    public String getDeveloperById(@PathVariable Long id, Model model) {
        log.info("Received a request to get developer by id. getDeveloperById - id : {}", id);
        repository.findById(id).ifPresent(developer -> model.addAttribute(DEVELOPER, developer));
        model.addAttribute(SKILLS, skillRepository.findAll());
        return DEVELOPER;
    }

    @GetMapping(value = "/developers")
    public String getAllDevelopers(Model model) {
        log.info("Received a request to get all developers. getAllDevelopers");
        model.addAttribute(DEVELOPERS, repository.findAll());
        return DEVELOPERS;
    }

    @PostMapping(value = "/developers")
    public String createDeveloper(@RequestParam String email,
                                  @RequestParam String firstName,
                                  @RequestParam String lastName, Model model) {
        log.info("Received a request to create developer. createDeveloper - email : {} firstName : {} lastName : {}", email, firstName, lastName);

        Developer newDeveloper = repository.save(new Developer(firstName, lastName, email, null));

        model.addAttribute(DEVELOPER, newDeveloper);
        model.addAttribute(SKILLS, skillRepository.findAll());
        return "redirect:/developer/" + newDeveloper.getId();
    }

    @PostMapping(value = "/developer/{id}/skills")
    public String addSkillToDeveloper(@PathVariable Long id,
                                      @RequestParam Long skillId, Model model) {
        log.info("Received a request to add a skill to developer. addSkillToDeveloper - id : {} skillId : {}", id, skillId);

        Optional<Developer> optionalDeveloper = repository.findById(id);

        if (optionalDeveloper.isPresent()) {
            Developer developer = optionalDeveloper.get();
            Optional<Skill> optionalSkill = skillRepository.findById(skillId);
            if (optionalSkill.isPresent()) {
                Skill skill = optionalSkill.get();
                if (!developer.hasSkill(skill)) {
                    developer.getSkills().add(skill);
                }
            }
            repository.save(developer);
            model.addAttribute(DEVELOPER, developer);
            model.addAttribute(SKILLS, skillRepository.findAll());
            return "redirect:/developer/" + developer.getId();
        }

        model.addAttribute(DEVELOPERS, repository.findAll());
        return "redirect:/developers";
    }

}
