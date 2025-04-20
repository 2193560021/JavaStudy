package com.lyy.search.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author atguigu
 * @since 2025-04-20
 */
@TableName("journal_article")
public class JournalArticle implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 文献标题
     */
    private String paperTitle;

    /**
     * 主题
     */
    private String theme;

    /**
     * 学科
     */
    private String subject;

    /**
     * 来源
     */
    private String journal;

    /**
     * 来源类别（SCI、EI、北大核心）
     */
    private String journalType;

    /**
     * 一作
     */
    private String firstAuthors;

    /**
     * 二作及以后
     */
    private String otherAuthors;

    /**
     * 日期
     */
    private LocalDate publishDate;

    /**
     * 机构
     */
    private String organization;

    /**
     * 基金
     */
    private String foundation;

    /**
     * DOI
     */
    private String doi;

    /**
     * 引文
     */
    private String cite;

    private String pdfUrl;

    /**
     * 下载次数
     */
    private Integer downloadCount;

    /**
     * 浏览次数
     */
    private Integer viewCount;

    /**
     * 上传用户
     */
    private Long createUserId;

    /**
     * 上传日期
     */
    private LocalDateTime createTime;

    /**
     * 更新用户
     */
    private Long updateUserId;

    /**
     * 更新日期
     */
    private LocalDateTime updateTime;

    /**
     * 逻辑删除 0-已删除 1-正常
     */
    private Integer isDel;

    private String keywordFirstAuthors;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getPaperTitle() {
        return paperTitle;
    }

    public void setPaperTitle(String paperTitle) {
        this.paperTitle = paperTitle;
    }
    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }
    public String getJournalType() {
        return journalType;
    }

    public void setJournalType(String journalType) {
        this.journalType = journalType;
    }
    public String getFirstAuthors() {
        return firstAuthors;
    }

    public void setFirstAuthors(String firstAuthors) {
        this.firstAuthors = firstAuthors;
    }
    public String getOtherAuthors() {
        return otherAuthors;
    }

    public void setOtherAuthors(String otherAuthors) {
        this.otherAuthors = otherAuthors;
    }
    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }
    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }
    public String getFoundation() {
        return foundation;
    }

    public void setFoundation(String foundation) {
        this.foundation = foundation;
    }
    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }
    public String getCite() {
        return cite;
    }

    public void setCite(String cite) {
        this.cite = cite;
    }
    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }
    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }
    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }
    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
    public String getKeywordFirstAuthors() {
        return keywordFirstAuthors;
    }

    public void setKeywordFirstAuthors(String keywordFirstAuthors) {
        this.keywordFirstAuthors = keywordFirstAuthors;
    }

    @Override
    public String toString() {
        return "JournalArticle{" +
            "id=" + id +
            ", paperTitle=" + paperTitle +
            ", theme=" + theme +
            ", subject=" + subject +
            ", journal=" + journal +
            ", journalType=" + journalType +
            ", firstAuthors=" + firstAuthors +
            ", otherAuthors=" + otherAuthors +
            ", publishDate=" + publishDate +
            ", organization=" + organization +
            ", foundation=" + foundation +
            ", doi=" + doi +
            ", cite=" + cite +
            ", pdfUrl=" + pdfUrl +
            ", downloadCount=" + downloadCount +
            ", viewCount=" + viewCount +
            ", createUserId=" + createUserId +
            ", createTime=" + createTime +
            ", updateUserId=" + updateUserId +
            ", updateTime=" + updateTime +
            ", isDel=" + isDel +
            ", keywordFirstAuthors=" + keywordFirstAuthors +
        "}";
    }
}
