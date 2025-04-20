package com.lyy.search.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author atguigu
 * @since 2025-04-20
 */
@TableName("abstract_journal_article")
public class AbstractJournalArticle implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 文献id
     */
    private Long paperId;

    /**
     * 摘要
     */
    private String abstractText;

    /**
     * 关键词数组
     */
    private String keywords;

    /**
     * 逻辑删除 0-已删除 1-正常
     */
    private Integer isDel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getPaperId() {
        return paperId;
    }

    public void setPaperId(Long paperId) {
        this.paperId = paperId;
    }
    public String getAbstractText() {
        return abstractText;
    }

    public void setAbstractText(String abstractText) {
        this.abstractText = abstractText;
    }
    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    @Override
    public String toString() {
        return "AbstractJournalArticle{" +
            "id=" + id +
            ", paperId=" + paperId +
            ", abstractText=" + abstractText +
            ", keywords=" + keywords +
            ", isDel=" + isDel +
        "}";
    }
}
