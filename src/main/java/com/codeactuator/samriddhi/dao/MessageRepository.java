package com.codeactuator.samriddhi.dao;

import java.util.List;

import com.codeactuator.samriddhi.dao.exceptions.DBException;
import com.codeactuator.samriddhi.domain.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {

	public List<Message> findByFrom(String loginId) throws DBException;
	
	public List<Message> findByTo(String loginId) throws DBException;
}
