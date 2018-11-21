package com.brkc.json;

import java.util.List;

/**
 * 描述：添加类的描述
 *
 * @author 小白攻城狮
 * @e-mail 635032648@qq.com
 * @time Created on 2018/11/21
 * @update 添加更新的内容
 */
public class GsonFormat {


    /**
     * data : {"countcommenturl":"http://zhbj.qianlong.com/client/content/countComment/","more":"/10002/list_2.json","news":[{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10002/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35310,"listimage":"http://10.0.2.2:8080/zhbj/10002/1452327318UU91.jpg","pubdate":"2014-04-08 14:58","title":"专题","type":"news","url":"http://10.0.2.2:8080/zhbj/10002/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10002/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35318,"listimage":"http://10.0.2.2:8080/zhbj/10002/1452327318UU91.jpg","pubdate":"2014-04-08 14:58","title":"专题","type":"news","url":"http://10.0.2.2:8080/zhbj/10002/724D6A55496A11726628.html"}],"title":"专题","topic":[{"description":"11111111","id":10101,"listimage":"http://10.0.2.2:8080/zhbj/10002/1452327318UU91.jpg","sort":1,"title":"专题","url":"http://10.0.2.2:8080/zhbj/10002/list_1.json"},{"description":"22222222","id":10100,"listimage":"http://10.0.2.2:8080/zhbj/10002/1452327318UU91.jpg","sort":2,"title":"222222222222","url":"http://10.0.2.2:8080/zhbj/10002/list_1.json"}]}
     * retcode : 200
     */

    private DataBean data;
    private int retcode;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public static class DataBean {
        /**
         * countcommenturl : http://zhbj.qianlong.com/client/content/countComment/
         * more : /10002/list_2.json
         * news : [{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10002/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35310,"listimage":"http://10.0.2.2:8080/zhbj/10002/1452327318UU91.jpg","pubdate":"2014-04-08 14:58","title":"专题","type":"news","url":"http://10.0.2.2:8080/zhbj/10002/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10002/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35318,"listimage":"http://10.0.2.2:8080/zhbj/10002/1452327318UU91.jpg","pubdate":"2014-04-08 14:58","title":"专题","type":"news","url":"http://10.0.2.2:8080/zhbj/10002/724D6A55496A11726628.html"}]
         * title : 专题
         * topic : [{"description":"11111111","id":10101,"listimage":"http://10.0.2.2:8080/zhbj/10002/1452327318UU91.jpg","sort":1,"title":"专题","url":"http://10.0.2.2:8080/zhbj/10002/list_1.json"},{"description":"22222222","id":10100,"listimage":"http://10.0.2.2:8080/zhbj/10002/1452327318UU91.jpg","sort":2,"title":"222222222222","url":"http://10.0.2.2:8080/zhbj/10002/list_1.json"}]
         */

        private String countcommenturl;
        private String more;
        private String title;
        private List<NewsBean> news;
        private List<TopicBean> topic;

        public String getCountcommenturl() {
            return countcommenturl;
        }

        public void setCountcommenturl(String countcommenturl) {
            this.countcommenturl = countcommenturl;
        }

        public String getMore() {
            return more;
        }

        public void setMore(String more) {
            this.more = more;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<NewsBean> getNews() {
            return news;
        }

        public void setNews(List<NewsBean> news) {
            this.news = news;
        }

        public List<TopicBean> getTopic() {
            return topic;
        }

        public void setTopic(List<TopicBean> topic) {
            this.topic = topic;
        }

        public static class NewsBean {
            /**
             * comment : true
             * commentlist : http://10.0.2.2:8080/zhbj/10002/comment_1.json
             * commenturl : http://zhbj.qianlong.com/client/user/newComment/35319
             * id : 35310
             * listimage : http://10.0.2.2:8080/zhbj/10002/1452327318UU91.jpg
             * pubdate : 2014-04-08 14:58
             * title : 专题
             * type : news
             * url : http://10.0.2.2:8080/zhbj/10002/724D6A55496A11726628.html
             */

            private boolean comment;
            private String commentlist;
            private String commenturl;
            private int id;
            private String listimage;
            private String pubdate;
            private String title;
            private String type;
            private String url;

            public boolean isComment() {
                return comment;
            }

            public void setComment(boolean comment) {
                this.comment = comment;
            }

            public String getCommentlist() {
                return commentlist;
            }

            public void setCommentlist(String commentlist) {
                this.commentlist = commentlist;
            }

            public String getCommenturl() {
                return commenturl;
            }

            public void setCommenturl(String commenturl) {
                this.commenturl = commenturl;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getListimage() {
                return listimage;
            }

            public void setListimage(String listimage) {
                this.listimage = listimage;
            }

            public String getPubdate() {
                return pubdate;
            }

            public void setPubdate(String pubdate) {
                this.pubdate = pubdate;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class TopicBean {
            /**
             * description : 11111111
             * id : 10101
             * listimage : http://10.0.2.2:8080/zhbj/10002/1452327318UU91.jpg
             * sort : 1
             * title : 专题
             * url : http://10.0.2.2:8080/zhbj/10002/list_1.json
             */

            private String description;
            private int id;
            private String listimage;
            private int sort;
            private String title;
            private String url;

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getListimage() {
                return listimage;
            }

            public void setListimage(String listimage) {
                this.listimage = listimage;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
