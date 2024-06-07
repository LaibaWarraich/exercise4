package at.ac.fhcampuswien.fhmdb.api;

import at.ac.fhcampuswien.fhmdb.models.Genre;

public class MovieAPIRequestBuilder {
    private final StringBuilder url;

    public MovieAPIRequestBuilder(String baseUrl) {
        this.url = new StringBuilder(baseUrl);
    }

    public MovieAPIRequestBuilder query(String query) {
        if (query != null && !query.isEmpty()) {
            appendDelimiter();
            url.append("query=").append(query);
        }
        return this;
    }

    public MovieAPIRequestBuilder genre(Genre genre) {
        if (genre != null) {
            appendDelimiter();
            url.append("genre=").append(genre);
        }
        return this;
    }

    public MovieAPIRequestBuilder releaseYear(String releaseYear) {
        if (releaseYear != null && !releaseYear.isEmpty()) {
            appendDelimiter();
            url.append("releaseYear=").append(releaseYear);
        }
        return this;
    }

    public MovieAPIRequestBuilder ratingFrom(String ratingFrom) {
        if (ratingFrom != null && !ratingFrom.isEmpty()) {
            appendDelimiter();
            url.append("ratingFrom=").append(ratingFrom);
        }
        return this;
    }

    public String build() {
        return url.toString();
    }

    private void appendDelimiter() {
        if (url.indexOf("?") == -1) {
            url.append("?");
        } else {
            url.append("&");
        }
    }
}

