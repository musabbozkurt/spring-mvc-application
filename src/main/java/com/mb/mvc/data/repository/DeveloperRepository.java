package com.mb.mvc.data.repository;

import com.mb.mvc.data.entity.Developer;
import org.springframework.data.repository.CrudRepository;

public interface DeveloperRepository extends CrudRepository<Developer, Long> {

}
