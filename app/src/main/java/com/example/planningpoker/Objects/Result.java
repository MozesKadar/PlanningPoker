package com.example.planningpoker.Objects;

public class Result {
    private String result, resultOwner;

    public Result() {
    }

    public Result(String result, String resultOwner) {
        this.result = result;
        this.resultOwner = resultOwner;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResultOwner() {
        return resultOwner;
    }

    public void setResultOwner(String resultOwner) {
        this.resultOwner = resultOwner;
    }

    @Override
    public String toString() {
        return "Result{" +
                "result='" + result + '\'' +
                ", resultOwner='" + resultOwner + '\'' +
                '}';
    }
}
