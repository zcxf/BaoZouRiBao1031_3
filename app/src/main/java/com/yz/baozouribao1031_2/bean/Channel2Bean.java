package com.yz.baozouribao1031_2.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/3.
 */

public class Channel2Bean {

    private int id;
    private String name;
    private String summary;
    private String thumbnail;
    private String image;
    private int timestamp;

    private List<DataBean> data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private int document_id;
        private int display_type;
        private String title;
        private String image;
        private String thumbnail;
        private String author_avatar;
        private String author_name;
        private int author_id;
        private int section_id;
        private String share_url;
        private String url;
        private int hit_count;
        private String section_name;
        private String section_image;
        private String section_color;
        private String hit_count_string;
        private int timestamp;
        private int comment_count;
        private int vote_count;

        public int getDocument_id() {
            return document_id;
        }

        public void setDocument_id(int document_id) {
            this.document_id = document_id;
        }

        public int getDisplay_type() {
            return display_type;
        }

        public void setDisplay_type(int display_type) {
            this.display_type = display_type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getAuthor_avatar() {
            return author_avatar;
        }

        public void setAuthor_avatar(String author_avatar) {
            this.author_avatar = author_avatar;
        }

        public String getAuthor_name() {
            return author_name;
        }

        public void setAuthor_name(String author_name) {
            this.author_name = author_name;
        }

        public int getAuthor_id() {
            return author_id;
        }

        public void setAuthor_id(int author_id) {
            this.author_id = author_id;
        }

        public int getSection_id() {
            return section_id;
        }

        public void setSection_id(int section_id) {
            this.section_id = section_id;
        }

        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getHit_count() {
            return hit_count;
        }

        public void setHit_count(int hit_count) {
            this.hit_count = hit_count;
        }

        public String getSection_name() {
            return section_name;
        }

        public void setSection_name(String section_name) {
            this.section_name = section_name;
        }

        public String getSection_image() {
            return section_image;
        }

        public void setSection_image(String section_image) {
            this.section_image = section_image;
        }

        public String getSection_color() {
            return section_color;
        }

        public void setSection_color(String section_color) {
            this.section_color = section_color;
        }

        public String getHit_count_string() {
            return hit_count_string;
        }

        public void setHit_count_string(String hit_count_string) {
            this.hit_count_string = hit_count_string;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public int getComment_count() {
            return comment_count;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }

        public int getVote_count() {
            return vote_count;
        }

        public void setVote_count(int vote_count) {
            this.vote_count = vote_count;
        }
    }
}
