package cn.iocoder.springboot.lab15.springdataelasticsearch.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

/**
 * @author zhukaiyuan
 */
@Data
@Document(indexName = "article", // 索引名
        type = "article", // 类型。未来的版本即将废弃
        shards = 1, // 默认索引分区数
        replicas = 0, // 每个分区的备份数
        refreshInterval = "-1" // 刷新间隔
)
@Accessors(chain = true)
@NoArgsConstructor
public class Article {

    private Long id;
    @Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_smart")
    private String title;

//    @Field(type = FieldType.Text, analyzer = "pinyin", searchAnalyzer = "pinyin")
//    private String pinyinTitle;
//
    private String author;
//    @Field(type = FieldType.Keyword, analyzer = "pinyin", searchAnalyzer = "pinyin")
//    private String pinyinAuthor;


    @Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_smart")
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Field(name = "release", type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime release;
}
