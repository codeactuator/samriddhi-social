/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeactuator.samriddhi.dao;

import com.codeactuator.samriddhi.domain.Document;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Ramesh
 */
public interface DocumentRepository extends CrudRepository<Document, Long> {


}
