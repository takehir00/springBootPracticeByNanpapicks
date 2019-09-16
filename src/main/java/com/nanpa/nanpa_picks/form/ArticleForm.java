package com.nanpa.nanpa_picks.form;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Data
public class ArticleForm {
    public long id;

    public String url;

    public String title;

    public String imageUrl;
}
