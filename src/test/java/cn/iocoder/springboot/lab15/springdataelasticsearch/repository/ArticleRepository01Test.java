package cn.iocoder.springboot.lab15.springdataelasticsearch.repository;

import cn.iocoder.springboot.lab15.springdataelasticsearch.Application;
import cn.iocoder.springboot.lab15.springdataelasticsearch.bo.Article;
import cn.iocoder.springboot.lab15.springdataelasticsearch.bo.ProductConditionBO;
import cn.iocoder.springboot.lab15.springdataelasticsearch.result.ArticleSearchResultMapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Map;
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ArticleRepository01Test {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void test() {
        // 创建 ES 搜索条件
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder()
                .withIndices("article")
                .withPageable(PageRequest.of(0, 15));
        // 筛选
        nativeSearchQueryBuilder.withQuery(QueryBuilders.multiMatchQuery("蔡康永爱产品和技术",
                "title", "content"));
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("title").field("content")
                .preTags("<em style='color:red'>")
                .postTags("</em>")
                .fragmentSize(100);

        nativeSearchQueryBuilder.withHighlightBuilder(highlightBuilder);
        // 聚合
//        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("cids").field("cid")); // 商品分类
        // 执行查询
        NativeSearchQuery searchQuery = nativeSearchQueryBuilder.build();
        AggregatedPage<Article> articles = elasticsearchTemplate.queryForPage(searchQuery, Article.class, new ArticleSearchResultMapper());

        // 后续遍历 condition.categories 数组，查询商品分类，设置商品分类名。
        log.info("res:{}", articles);
    }

}
