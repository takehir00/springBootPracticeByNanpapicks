package admin.services.impl;

import admin.models.comment.CommentReadModel;
import admin.responses.CommentTopResponse;
import admin.services.CommentService;
import db.entities.Comment;
import db.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@ComponentScan("db.repositories")
@EntityScan(basePackageClasses = Comment.class)
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Override
    public CommentTopResponse listing() {
        List<Comment> commentEntityList = commentRepository.findAll();
        return CommentTopResponse.builder()
                .commentList(
                        commentEntityList.stream()
                                .map(CommentReadModel::convertToCommentReadModel)
                                .collect(Collectors.toList()))
                .build();
    }
}
