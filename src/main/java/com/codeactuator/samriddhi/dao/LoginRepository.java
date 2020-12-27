/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeactuator.samriddhi.dao;

import com.codeactuator.samriddhi.dao.exceptions.DBException;
import com.codeactuator.samriddhi.domain.Login;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface LoginRepository extends CrudRepository<Login, Long> {

    public List<Login> findByLoginId(String loginId) throws DBException;
    //public List<Login> findByProfession(String professionName) throws DBException;
    //public List<Login> search(String searchString) throws DBException;
    public Login findByLoginIdAndPassword(String loginId, String password) throws DBException;
}
