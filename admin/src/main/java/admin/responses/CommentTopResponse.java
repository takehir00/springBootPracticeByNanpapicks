package admin.responses;

import admin.models.comment.CommentReadModel;
import lombok.Builder;

import java.util.List;

@Builder
public class CommentTopResponse {
    // ここで持たせるクラスはentityクラスか、commentレスポンスクラスか、commentモデルクラスか
    // entityはテーブルをオブジェクトにしたもの、ビジネスロジックは持たせたくない
    // モデルクラスを作る
    public List<CommentReadModel> commentList;
    public String flash;
}
