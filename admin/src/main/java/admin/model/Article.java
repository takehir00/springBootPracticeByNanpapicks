package admin.model;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    public Long id;

    @Column
    public String url;

    @Column
    public String title;

    @Column
    public String imageUrl;

    //このモデルクラスからDAOクラスやrepositoryクラスにdbアクセスするのがいいのか、
    // ここでdbにアクセスする処理を実装してしまっていいのか。どうなんだろ。ここでやることに問題はない気がする
    /**
     * 記事全件取得
     *
     * @return
     */
    public List<Article> getAll() {
        return null;
    }
}
