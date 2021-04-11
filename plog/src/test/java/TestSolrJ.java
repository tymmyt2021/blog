import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import www.wzb.dao.UserContentMapper;
import www.wzb.entity.UserContent;
import www.wzb.service.UserContentService;

import java.io.IOException;
import java.util.List;


@ContextConfiguration(locations = {"classpath:applicationContext-redis.xml","classpath:spring-mybatis.xml","classpath:applicationContext-solr.xml"})
public class TestSolrJ extends AbstractJUnit4SpringContextTests {
    @Autowired
    private SolrClient solrServer;

    @Autowired
    private UserContentService userContentService;

    @Test
    public void testSave() throws Exception {

        //1.创建一个文档对象
        SolrInputDocument inputDocument = new SolrInputDocument();
        inputDocument.addField( "id", "34" );
        inputDocument.addField( "item_title", "ssm项目开发实战" );
        inputDocument.addField( "item_content", "ssm指的是:Spring MVC + Spring + Mybatis" );
        inputDocument.addField( "item_image", "www.ssm.png" );
        inputDocument.addField( "author", "wly" );
        //2.将文档写入索引库中
        solrServer.add( inputDocument );
        //3.提交
        solrServer.commit();
    }

    @Test
    public void testSaveAll() throws IOException, SolrServerException {
        List<UserContent> list = userContentService.findAll();
        if(list!=null && list.size()>0){
            for (UserContent cont : list){
                SolrInputDocument inputDocument = new SolrInputDocument();
                inputDocument.addField( "comment_num", cont.getCommentNum() );
                inputDocument.addField( "downvote", cont.getDownvote() );
                inputDocument.addField( "upvote", cont.getUpvote() );
                inputDocument.addField( "nick_name", cont.getNickName());
                inputDocument.addField( "img_url", cont.getImgUrl() );
                inputDocument.addField( "rpt_time", cont.getRptTime() );
                inputDocument.addField( "content", cont.getContent() );
                inputDocument.addField( "category", cont.getCategory());
                inputDocument.addField( "title", cont.getTitle() );
                inputDocument.addField( "u_id", cont.getuId() );
                inputDocument.addField( "id", cont.getId());
                inputDocument.addField( "personal", cont.getPersonal());
                solrServer.add( inputDocument );
            }
        }

        solrServer.commit();
    }

    @Test
    public void testDelete(){


            String password = new Md5PasswordEncoder().encodePassword("wzb123456", "tymmyt2021@163.com");
            System.out.println(password);

    }
}
