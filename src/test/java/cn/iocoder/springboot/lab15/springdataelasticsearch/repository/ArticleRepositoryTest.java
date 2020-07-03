package cn.iocoder.springboot.lab15.springdataelasticsearch.repository;

import cn.hutool.core.util.RandomUtil;
import cn.iocoder.springboot.lab15.springdataelasticsearch.Application;
import cn.iocoder.springboot.lab15.springdataelasticsearch.bo.Article;
import cn.iocoder.springboot.lab15.springdataelasticsearch.dataobject.ESProductDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ArticleRepositoryTest {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ArticleRepository articleRepository;
    @Test // 插入一条记录
    public void testInsert() {
        Article article = new Article();
        article.setId(5L)
                .setAuthor("李博闻")
//                .setRelease(LocalDateTime.now())
                .setContent("\n" +
                        "好了，大型互撕现场开始，建议离远点观看，容易溅一身血…\n" +
                        "\n" +
                        "技术猿猿们开始欢呼了：\n" +
                        "\n" +
                        "太飒了！这简直就是戳中我们技术的灵魂心声啊！早就受够了你们这帮产品狗，你们，你们.....此处情绪激动的说不出话，省略100个内心词......根本就不懂技术好嘛！\n" +
                        "\n" +
                        "一万个吐槽证据已经下载完毕：举例：刚毕业的非技术背景妹子产品经理，说两句还哭的，真的是职场靓丽风景线了\n");
        articleRepository.save(article);
    }

    // 这里要注意，如果使用 save 方法来更新的话，必须是全量字段，否则其它字段会被覆盖。
    // 所以，这里仅仅是作为一个示例。
    @Test // 更新一条记录
    public void testUpdate() {
        Article article = new Article();
        article.setId(1L);
        articleRepository.save(article);
    }

    @Test // 根据 ID 编号，删除一条记录
    public void testDelete() {
        productRepository.deleteById(1);
    }

    @Test // 根据 ID 编号，查询一条记录
    public void testSelectById() {
        Optional<Article> article = articleRepository.findById(1L);
        System.out.println(article.get());
    }

    @Test // 根据 ID 编号数组，查询多条记录
    public void testSelectByIds() {
        Iterable<ESProductDO> users = productRepository.findAllById(Arrays.asList(1, 4));
        users.forEach(System.out::println);
    }





    @Test // 插入一条记录
    public void testInsert1() {
        Article article = new Article();
        article.setId(RandomUtil.randomLong())
                .setAuthor("朱开元")
                .setTitle("阿里CEO张勇放话，90%产品岗将由技术产生，不懂技术的产品人被out了吗？")
                .setRelease(LocalDateTime.now())
                .setContent("\n" +
                        "好了，大型互撕现场开始，建议离远点观看，容易溅一身血…\n" +
                        "\n" +
                        "技术猿猿们开始欢呼了：\n" +
                        "\n" +
                        "太飒了！这简直就是戳中我们技术的灵魂心声啊！早就受够了你们这帮产品狗，你们，你们.....此处情绪激动的说不出话，省略100个内心词......根本就不懂技术好嘛！\n" +
                        "\n" +
                        "一万个吐槽证据已经下载完毕：举例：刚毕业的非技术背景妹子产品经理，说两句还哭的，真的是职场靓丽风景线了\n");
        articleRepository.save(article);
    }

    @Test // 插入一条记录
    public void testInsert2() {
        Article article = new Article();
        article.setId(RandomUtil.randomLong())
                .setAuthor("李博闻")
                .setTitle("阿里CEO张勇放话，90%产品岗将由技术产生，不懂技术的产品人被out了吗？")
//                .setRelease(LocalDateTime.now())
                .setContent("\n" +
                        "好了，大型互撕现场开始，建议离远点观看，容易溅一身血…\n" +
                        "\n" +
                        "技术猿猿们开始欢呼了：\n" +
                        "\n" +
                        "太飒了！这简直就是戳中我们技术的灵魂心声啊！早就受够了你们这帮产品狗，你们，你们.....此处情绪激动的说不出话，省略100个内心词......根本就不懂技术好嘛！\n" +
                        "\n" +
                        "一万个吐槽证据已经下载完毕：举例：刚毕业的非技术背景妹子产品经理，说两句还哭的，真的是职场靓丽风景线了\n");
        articleRepository.save(article);
    }

    @Test // 插入一条记录
    public void testInsert3() {
        Article article = new Article();
        article.setId(RandomUtil.randomLong())
                .setAuthor("李博闻")
                .setTitle("废掉一个人最快的方法，就是让他闲着")
                .setRelease(LocalDateTime.now())
                .setContent("有人说，想要看一个人是否优秀，那就看他闲下来做什么。\\n    技术猿猿们开始欢呼了　　这世上有人忙里偷闲，利用坐车和排队的间隙，读书，思考，写作，也有人终日无所事事，虚度光阴。\\n　　闲，并不是一个人的福气。相反，废掉一个人最快的方式就是让他闲下来。\\n　　正如罗曼·罗兰所说：“生活中最沉重的负担不是工作，而是无聊。”\\n　　闲着闲着，一个人就废了。\\n　　蔡康永曾说过：“当你没有上进心的时候，你是在杀人，你不小心，杀了你自己。”\\n　　朋友大学毕业后，凭着高学历进了一家大公司，以为从此一生安稳，本职工作完成后便悠闲地追剧。\\n　　身边有同事下班后忙着考证、进修时，她嗤之以鼻，认为别人学历不如自己，再怎么努力也无济于事。\\n　　虽然每天按时上下班，和同事做着相似的工作，但只有潮水退去的时候，才能知道谁在裸泳。\\n　　不过五年时间，行业环境影响下，公司面临改革，需要裁员，高学历出身的她赫然在列。\\n　　毕业八年的她被迫重返人才市场，但彼时的她与毕业时相比毫无长进，面试屡屡碰壁。\\n　　李尚龙曾说：\\n　　“真正的安稳是历经世事后的淡薄，你还没有见过世界，就想隐退山林，到头来只会是井底之蛙。”\\n　　人生如逆水行舟，不进则退。\\n　　优胜劣汰的世界里，你必须不断提升自己的价值。\\n　　被窝里的温度，远不如未来的收获温暖；书本里的故事，总有你学到的人生，贪图安逸，只会让生命生锈。\\n　　常有人感叹中年危机来得猝不及防，但其实在它到来之前，你有无数次让它绕开你的机会，但也许正是因为你选择了安逸，才给了它可乘之机。\\n　　所谓不负此生，就是不曾辜负生命中的每个阶段。");
        articleRepository.save(article);
    }
}
