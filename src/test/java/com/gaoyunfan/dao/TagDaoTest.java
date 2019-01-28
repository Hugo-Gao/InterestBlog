package com.gaoyunfan.dao;

import com.gaoyunfan.model.Tag;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/**
 * @author yunfan.gyf
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class TagDaoTest {
    @Autowired
    private TagDao tagDao;

    @Test
    public void testInsert() {
        List<String> tags = Lists.newArrayList();
        tags.add("Java");
        tags.add("Python");
        tags.add("数据结构");
        tagDao.insertTags(tags);
    }

    @Test
    public void testSelect() {
        List<Tag> tags = tagDao.selectTags(-1);
        assert tags.size() > 0;
    }



}