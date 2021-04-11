package www.wzb.service;

import www.wzb.common.PageHelper.Page;
import www.wzb.entity.UserContent;

public interface SolrService {
    /**
     * 根据关键字搜索文章并分页
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<UserContent> findByKeyWords(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 添加文章到solr索引库中
     * @param userContent
     */
    void addUserContent(UserContent userContent);

    /**
     * 根据solr索引库
     * @param userContent
     */
    void updateUserContent(UserContent userContent);

    /**
     * 根据文章id删除索引库
     * @param id
     */
    void deleteById(Long id);
}
