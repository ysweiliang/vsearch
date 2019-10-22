package com.macro.vsearch.article;

import lombok.Data;

/**
 * 文章相关实体类
 *
 * @author yuans
 * @create 2019-09-27-16:38
 */
@Data
public class Article {

    /**
     * 文章id
     */
    private Integer id;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章详情
     */
    private String content;
    /**
     * 作者
     */
    private String author;
    /**
     * 文章收藏数
     */
    private Integer collect;
    /**
     * 文章浏览量
     */
    private Integer pageView;
    /**
     * 文章创建日期
     */
    private String createDt;

}
