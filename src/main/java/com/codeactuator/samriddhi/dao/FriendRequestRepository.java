package com.codeactuator.samriddhi.dao;

import java.util.List;

import com.codeactuator.samriddhi.dao.exceptions.DBException;
import com.codeactuator.samriddhi.domain.FriendRequest;
import org.springframework.data.repository.CrudRepository;

public interface FriendRequestRepository extends CrudRepository<FriendRequest, Long> {

    public List<FriendRequest> findByRequestFrom(String requestFromLoginId) throws DBException;
    public List<FriendRequest> findByRequestTo(String requestTooginId) throws DBException;
}
