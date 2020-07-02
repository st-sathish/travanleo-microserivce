package com.travanleo.comment.service;

import com.travanleo.comment.data.CommentData;

import java.util.List;

public interface CommentReadPlatformService {

    List<CommentData> retrieveMyCommentList();
}
