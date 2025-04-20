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
@TableName("academic_dissertation")
public class AcademicDissertation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 论文标题
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
     * 学位
     */
    private String degree;

    /**
     * 学生
     */
    private String student;

    /**
     * 导师
     */
    private String teacher;

    /**
     * 年度
     */
    private LocalDate year;

    /**
     * 单位
     */
    private String organization;

    /**
     * 研究层次
     */
    private String researchLevel;

    /**
     * DOI
     */
    private String doi;

    /**
     * 摘要
     */
    private String abstractText;

    /**
     * 关键词数组
     */
    private String keywords;

    /**
     * 引文
     */
    private String cite;

    /**
     * 对象存储地址
     */
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

    private String keywordFirst;

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
    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }
    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
    public LocalDate getYear() {
        return year;
    }

    public void setYear(LocalDate year) {
        this.year = year;
    }
    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }
    public String getResearchLevel() {
        return researchLevel;
    }

    public void setResearchLevel(String researchLevel) {
        this.researchLevel = researchLevel;
    }
    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
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
    public String getKeywordFirst() {
        return keywordFirst;
    }

    public void setKeywordFirst(String keywordFirst) {
        this.keywordFirst = keywordFirst;
    }

    @Override
    public String toString() {
        return "AcademicDissertation{" +
            "id=" + id +
            ", paperTitle=" + paperTitle +
            ", theme=" + theme +
            ", subject=" + subject +
            ", degree=" + degree +
            ", student=" + student +
            ", teacher=" + teacher +
            ", year=" + year +
            ", organization=" + organization +
            ", researchLevel=" + researchLevel +
            ", doi=" + doi +
            ", abstractText=" + abstractText +
            ", keywords=" + keywords +
            ", cite=" + cite +
            ", pdfUrl=" + pdfUrl +
            ", downloadCount=" + downloadCount +
            ", viewCount=" + viewCount +
            ", createUserId=" + createUserId +
            ", createTime=" + createTime +
            ", updateUserId=" + updateUserId +
            ", updateTime=" + updateTime +
            ", keywordFirst=" + keywordFirst +
        "}";
    }
}
