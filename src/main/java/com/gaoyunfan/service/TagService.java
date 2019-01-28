package com.gaoyunfan.service;

import com.gaoyunfan.dao.TagDao;
import com.gaoyunfan.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yunfan.gyf
 **/
@Service
public class TagService {

    @Autowired
    private TagDao tagDao;


    public String getTagById(int id) {
        return tagDao.selectTagById(id);
    }
}
