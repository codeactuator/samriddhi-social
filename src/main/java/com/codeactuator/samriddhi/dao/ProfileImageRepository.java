package com.codeactuator.samriddhi.dao;

import com.codeactuator.samriddhi.dao.exceptions.DBException;
import com.codeactuator.samriddhi.domain.ProfileImage;
import org.springframework.data.repository.CrudRepository;

public interface ProfileImageRepository extends CrudRepository<ProfileImage, Long> {


}
