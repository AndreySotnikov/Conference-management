package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.DAO.TestEntityRepository;
import project.Entity.TestEntity;

/**
 * Created by andrey on 11.07.15.
 */
@Service
public class TestEntityService {
    @Autowired
    TestEntityRepository testEntityRepository;

    @Transactional
    public void add(TestEntity testEntity){
        testEntityRepository.save(testEntity);
    }

    @Transactional
    public TestEntity find(int id){
        return testEntityRepository.findOne(id);
    }
}
