package cc.fs.sample.easyhttp.lib;

/**
 * http请求
 * 使用Builder模式:
 * 1.构造对象比较复杂(使用比较多对象)
 * 2.创建过程与对象分离
 */
public class EasyRequest {

    private final EasyUrl httpUrl;
    private final String method;
    private EasyHeader requstHeader;
    private final EasyBody requestBody;

    public EasyRequest(Builder builder) {
        this.httpUrl = builder.easyUrl;
        this.method = builder.method;
        this.requstHeader = builder.requstHeader;
        this.requestBody = builder.requestBody;
    }

    public static class Builder {
        private EasyUrl easyUrl;
        private String method;
        private EasyHeader requstHeader;
        private EasyBody requestBody;


        public Builder() {
            method = "GET";
        }

        public Builder url(EasyUrl url) {
            if (url == null) throw new NullPointerException("url == null");
            this.easyUrl = url;
            return this;
        }

        /**
         * 需要判断url类型
         * http/https/ip:port
         */
        public Builder url(String url) {
            if (url == null) throw new NullPointerException("url == null");
            EasyUrl easyUrl = EasyUrl.parse(url);
            if (easyUrl == null) throw new NullPointerException("httpUrl = null");
            return url(easyUrl);
        }

        public Builder get() {
            return method("GET", null);
        }

        public Builder post() {
            return method("POST", null);
        }

        public Builder put() {
            return method("PUT", null);
        }

        public Builder delete() {
            return method("DELETE", null);
        }

        public Builder method(String method, EasyBody requestBody) {
            if (method == null) throw new NullPointerException("method == null");
            if (method.isEmpty()) throw new NullPointerException("method is empty");
            if (hasSupportMethod(method)) throw new IllegalArgumentException(" unsupport this method ");

            this.method = method;
            this.requestBody = requestBody;
            return this;
        }

        private boolean hasSupportMethod(String method) {
            if (method.equals("GET") || method.equals("POST") || method.equals("PUT") || method.equals("DELETE")) {
                return true;
            }
            return false;
        }


    }

}
