package cc.fs.sample.easyhttp.lib;

import android.support.annotation.Nullable;

/**
 * HttpUrl封装
 */
public class EasyUrl {

    private final String scheme;
    private final String host;
    /**
     * http端口是80 , https端口是443
     */
    private final int port;

    public EasyUrl(Builder builder) {
        this.scheme = builder.scheme;
        this.host = builder.host;
        this.port = builder.effectivePort();
    }

    @Nullable
    public static EasyUrl parse(String url) {
        if (url == null) throw new NullPointerException("url == null");
        Builder builder = new Builder();
        ParseResult parseResult = builder.parse(url);
        return parseResult == ParseResult.SUCCESS ? builder.build() : null;

    }

    public static class Builder {
        private String scheme;
        private String host;
        private int port = -1;

        public Builder() {
            scheme = "http";
        }

        public Builder scheme(String scheme) {
            if (scheme == null) {
                throw new NullPointerException("scheme == null");
            } else if (scheme.equalsIgnoreCase("http")) {
                this.scheme = "http";
            } else if (scheme.equalsIgnoreCase("https")) {
                this.scheme = "https";
            } else {
                throw new IllegalArgumentException("unexpected scheme: " + scheme);
            }
            return this;
        }

        public Builder host(String host) {
            if (host == null) throw new NullPointerException("host == null");
            this.host = host;
            return this;
        }

        public Builder port(int port) {
            if (port <= 0 || port > 65535)
                throw new IllegalArgumentException("unexpected port: " + port);
            this.port = port;
            return this;
        }

        public int effectivePort() {
            return port != -1 ? port : defaultPort(scheme);
        }

        private int defaultPort(String scheme) {
            if ("http".equals(scheme)) {
                return 80;
            } else if ("https".equals(scheme)) {
                return 443;
            } else {
                return -1;
            }
        }

        //解析url
        public ParseResult parse(String url) {
            if (host == null) throw new NullPointerException("host == null");
            int schemelen = -1;
            if (host.startsWith("http:")) {
                scheme = "http";
                schemelen = "http://".length();
            } else if (host.startsWith("https:")) {
                scheme = "https";
                schemelen = "https://".length();
            } else {
                return ParseResult.UNSUPPORTED_SCHEME;
            }

            //获取 host 和 port
            String noSchemeUrl = url.substring(schemelen);
            if (noSchemeUrl.contains("/")) {
                String addressUrl = noSchemeUrl.substring(0, noSchemeUrl.indexOf("/", 0));
                if (addressUrl.contains(":")) {
                    String[] params = addressUrl.split(":");
                    host = params[0];
                    port = Integer.valueOf(params[1]);
                } else {
                    host = addressUrl;
                    port = defaultPort(scheme);
                }
                if (port == -1) return ParseResult.INVALID_PORT;
            } else {
                if (noSchemeUrl.contains(":")) {
                    String[] params = noSchemeUrl.split(":");
                    host = params[0];
                    port = Integer.valueOf(params[1]);
                } else {
                    host = noSchemeUrl;
                    port = defaultPort(scheme);
                }
                if (port == -1) return ParseResult.INVALID_PORT;
            }

            return ParseResult.SUCCESS;
        }

        public EasyUrl build() {
            return new EasyUrl(this);
        }

    }


    enum ParseResult {
        SUCCESS,
        MISSING_SCHEME,
        UNSUPPORTED_SCHEME,
        INVALID_PORT,
        INVALID_HOST,
    }
}
