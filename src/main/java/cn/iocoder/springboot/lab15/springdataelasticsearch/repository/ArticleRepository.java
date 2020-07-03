package cn.iocoder.springboot.lab15.springdataelasticsearch.repository;

import cn.iocoder.springboot.lab15.springdataelasticsearch.bo.Article;
import cn.iocoder.springboot.lab15.springdataelasticsearch.dataobject.ESProductDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ArticleRepository extends ElasticsearchRepository<Article, Long> {

}
