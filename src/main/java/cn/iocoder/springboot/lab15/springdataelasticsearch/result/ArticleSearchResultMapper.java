package cn.iocoder.springboot.lab15.springdataelasticsearch.result;

import cn.hutool.core.convert.Convert;
import cn.iocoder.springboot.lab15.springdataelasticsearch.bo.Article;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.document.DocumentField;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 返回文章查询结果的映射
 */
@Slf4j
public class ArticleSearchResultMapper implements SearchResultMapper {
    @Override
    public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
        List<T> result = new ArrayList<>();
        long totalHits = response.getHits().getTotalHits();

        for (SearchHit searchHit : response.getHits()) {

            // 命中率 == 0 返回 null
            if (response.getHits().getHits().length <= 0) {
                return null;
            }
            // 高亮正文
            String summaryWithHighlight = null;
            HighlightField highlightFieldContent = searchHit.getHighlightFields().get("content");
            // 如果高亮的正文为null 将其前100个字符设为摘要
            if (highlightFieldContent != null && highlightFieldContent.fragments() != null) {
                summaryWithHighlight = Arrays.stream(highlightFieldContent.fragments())
                        .map(Text::toString)
                        .collect(Collectors.joining("\n[...]\n"));
            } else {
                summaryWithHighlight = (String) searchHit.getSourceAsMap().get("content");
            }

            // 如果高亮的标题为null 将原始标题设为标题
            String titleWithHighlight = null;

            HighlightField highlightFieldTitle = searchHit.getHighlightFields().get("title");


            if (highlightFieldTitle != null && highlightFieldTitle.fragments() != null) {
                titleWithHighlight = highlightFieldTitle.fragments()[0].toString();
            } else {
                titleWithHighlight = (String) searchHit.getSourceAsMap().get("title");
            }
            Article article = new Article();

            article.setId(Convert.toLong(searchHit.getSourceAsMap().get("id")));
            article.setAuthor((String) searchHit.getSourceAsMap().get("author"));
            article.setRelease(Convert.toLocalDateTime(searchHit.getSourceAsMap().get("release")));
            article.setContent(summaryWithHighlight);
            article.setTitle(titleWithHighlight);
            log.info(searchHit.getSourceAsString());
            result.add((T) article);
        }
        log.info("reponse:{}",response);
        log.info("clazz:{}",clazz);
        log.info("pageable:{}",pageable);
        return new AggregatedPageImpl<T>((List<T>) result, pageable, totalHits, response.getAggregations());
    }

    /**
     * Map a single {@link SearchHit} to the given {@link Class type}.
     *
     * @param searchHit must not be {@literal null}.
     * @param type      must not be {@literal null}.
     * @return can be {@literal null}.
     * @since 3.2
     */
    @Override
    public <T> T mapSearchHit(SearchHit searchHit, Class<T> type) {
        log.info("mapSearchHit : {},{}",searchHit,type);
        try {
            return type.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
