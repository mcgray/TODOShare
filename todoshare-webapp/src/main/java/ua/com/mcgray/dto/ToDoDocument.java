package ua.com.mcgray.dto;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.solr.client.solrj.beans.Field;

/**
 * @author orezchykov
 * @since 23.05.13
 */

public class ToDoDocument {

    @Field
    private String id;

    @Field("originalid")
    private Long originalId;

    @Field("title_s")
    private String title;

    @Field
    private String content;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public Long getOriginalId() {
        return originalId;
    }

    public void setOriginalId(final Long originalId) {
        this.originalId = originalId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public ToDoDocument() {
    }

    public ToDoDocument(final String id, final Long originalId, final String title, final String content) {
        this.id = id;
        this.originalId = originalId;
        this.title = title;
        this.content = content;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.id)
                                    .append(this.originalId)
                                    .append(this.title)
                                    .append(this.content).toHashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ToDoDocument other = (ToDoDocument) obj;
        return new EqualsBuilder().append(this.id, other.id)
                                    .append(this.originalId, other.originalId)
                                    .append(this.title, other.title)
                                    .append(this.content, other.content).isEquals();
    }

    @Override
    public String toString() {
        return "ToDoDocument{" +
                "id='" + id + '\'' +
                ", originalId=" + originalId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
