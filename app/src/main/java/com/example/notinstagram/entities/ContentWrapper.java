package com.example.notinstagram.entities;

public class ContentWrapper {
    private Content content;
    private ContentItem contentItem;

    public ContentWrapper(Content content, ContentItem contentItem) {
        this.content = content;
        this.contentItem = contentItem;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public ContentItem getContentItem() {
        return contentItem;
    }

    public void setContentItem(ContentItem contentItem) {
        this.contentItem = contentItem;
    }

    @Override
    public String toString() {
        return "ContentWrapper{" +
                "content=" + content +
                ", contentItem=" + contentItem +
                '}';
    }
}
