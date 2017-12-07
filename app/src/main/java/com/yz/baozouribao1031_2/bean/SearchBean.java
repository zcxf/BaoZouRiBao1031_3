package com.yz.baozouribao1031_2.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/3.
 */

public class SearchBean {

    private int total_pages;
    private int per_page;
    private int page;
    private int total_count;

    private List<DataBean> data;

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
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
        private String share_image;
        private String key_words;
        private String video_image_url;
        private int section_id;
        private String display_date;
        private String ga_prefix;
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
        private String author_summary;

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

        public String getShare_image() {
            return share_image;
        }

        public void setShare_image(String share_image) {
            this.share_image = share_image;
        }

        public String getKey_words() {
            return key_words;
        }

        public void setKey_words(String key_words) {
            this.key_words = key_words;
        }

        public String getVideo_image_url() {
            return video_image_url;
        }

        public void setVideo_image_url(String video_image_url) {
            this.video_image_url = video_image_url;
        }

        public int getSection_id() {
            return section_id;
        }

        public void setSection_id(int section_id) {
            this.section_id = section_id;
        }

        public String getDisplay_date() {
            return display_date;
        }

        public void setDisplay_date(String display_date) {
            this.display_date = display_date;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
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

        public String getAuthor_summary() {
            return author_summary;
        }

        public void setAuthor_summary(String author_summary) {
            this.author_summary = author_summary;
        }
    }
}
