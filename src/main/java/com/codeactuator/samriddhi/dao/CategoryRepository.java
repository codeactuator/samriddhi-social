/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeactuator.samriddhi.dao;

import com.codeactuator.samriddhi.domain.Category;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Ramesh
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {

    public Category findByName(String name);
}
