package com.skh.exceptions;

import com.skh.models.ResultDetail;

import java.util.List;

public class MultiStatusException extends RuntimeException {
    private final List<ResultDetail> results;

    public MultiStatusException(List<ResultDetail> results) {
        super("Multi-Status Exception");
        this.results = results;
    }

    public List<ResultDetail> getResults() {
        return results;
    }
}
