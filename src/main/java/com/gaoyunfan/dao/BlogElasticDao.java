package com.gaoyunfan.dao;

import com.gaoyunfan.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author yunfan.gyf
 **/
public interface BlogElasticDao extends ElasticsearchRepository<Blog, Integer> {
    Page<Blog> findByTitleLikeOrContentLike(String title, String content, Pageable pageable);
    Page<Blog> findByContentLike(String content, Pageable pageable);

}
