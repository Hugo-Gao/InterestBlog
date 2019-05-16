package com.gaoyunfan.rest;

/**
 * @author yunfan.gyf
 **/
public enum RestCode {

    OK(0,"OK"),
    KEY_WORD_NOT_NULL(1,"关键词不能为空"),
    BLOG_NOT_FOUND(2,"未找到博客"),
    USER_NOT_EXIST(3,"用户不存在"),
    WRONG_PAGE(10100,"页码不存在");

    public final int code;
    public final String msg;

    RestCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
