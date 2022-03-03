package com.mb.mvc;

import com.mb.mvc.data.entity.Developer;
import com.mb.mvc.data.entity.Skill;
import com.mb.mvc.data.repository.DeveloperRepository;
import com.mb.mvc.data.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class MvcApplication implements CommandLineRunner {

    private final DeveloperRepository developerRepository;
    private final SkillRepository skillRepository;

    public static void main(String[] args) {
        SpringApplication.run(MvcApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Skill java = new Skill("java", "Java language skill");
        Skill python = new Skill("python", "Python language skill");
        Skill javascript = new Skill("javascript", "Javascript language skill");
        Skill ruby = new Skill("ruby", "Ruby language skill");
        Skill emberjs = new Skill("emberjs", "Emberjs framework");
        Skill angularjs = new Skill("angularjs", "Angularjs framework");

        skillRepository.save(java);
        skillRepository.save(python);
        skillRepository.save(javascript);
        skillRepository.save(ruby);
        skillRepository.save(emberjs);
        skillRepository.save(angularjs);

        List<Developer> developers = new LinkedList<>();

        developers.add(new Developer("RJ", "Smith", "rj.smith@example.com", Arrays.asList(java, javascript, ruby)));
        developers.add(new Developer("James", "Johnson", "james.johnson@example.com", Arrays.asList(python, emberjs, ruby)));
        developers.add(new Developer("Kobe", "Williams", "kobe.williams@example.com", Arrays.asList(python, angularjs, ruby)));
        developers.add(new Developer("Reggie", "Miller", "reggie.miller@example.com", Arrays.asList(java, emberjs, angularjs, javascript)));
        developers.add(new Developer("Bob", "Brown", "brown@example.com", Collections.singletonList(emberjs)));

        developerRepository.saveAll(developers);
    }

}
