/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeactuator.samriddhi.dao;

import com.codeactuator.samriddhi.dao.exceptions.DBException;
import com.codeactuator.samriddhi.domain.SubCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 *
 * @author shekharkumar
 */
public interface SubCategoryRepository extends CrudRepository<SubCategory, Long> {

}
