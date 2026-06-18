package com.proelectricos.mdserp.service.sqlfactory;

import com.proelectricos.mdserp.model.entity.sqlfactory.CoInvNetoSql;
import com.proelectricos.mdserp.repository.sqlfactory.CoInvNetoSqlRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CoInvNetoSqlService {

    private final CoInvNetoSqlRepository repository;

    public List<CoInvNetoSql> findAll() {
        return repository.findAll();
    }
}
