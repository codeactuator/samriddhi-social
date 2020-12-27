/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeactuator.samriddhi.dao;

import com.codeactuator.samriddhi.dao.exceptions.DBException;
import com.codeactuator.samriddhi.domain.Comment;
import com.codeactuator.samriddhi.domain.Document;
import com.codeactuator.samriddhi.domain.Content;
import com.codeactuator.samriddhi.domain.Video;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 *
 * @author shekharkumar
 */
public interface ContentRepository extends CrudRepository<Content, Long>, ContentCustomRepository {

    public List<Content> findBySearchKeys(String seachString) throws DBException;

    public List<Content> findByCategory(String category) throws DBException;

}
