/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeactuator.samriddhi.dao;

import com.codeactuator.samriddhi.dao.exceptions.DBException;
import com.codeactuator.samriddhi.domain.ContentImage;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author shekharkumar
 */
public interface ContentImageRepository extends CrudRepository<ContentImage, Long> {

    public ContentImage findByContentId(long id) throws DBException;
}
