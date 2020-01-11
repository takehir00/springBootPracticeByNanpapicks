package admin.util;

import org.springframework.stereotype.Component;

@Component
public class PageUtil {

    /**
     * テーブルのレコード数からページ数を計算する
     *
     * @param recordCount
     * @param limit
     * @return
     */
    public static int calculatePageCount(int recordCount, int limit) {
        if (recordCount % limit >= 1) {
            return recordCount / limit + 1;
        } else {
            return recordCount/limit;
        }
    }

    /**
     * 指定したページ数とlimit数から取得したデータのoffsetを計算する
     *
     * @param page
     * @param limit
     * @return
     */
    public static int calculatePageOffset(int page, int limit) {
        if (page <= 1) {
            return 0;
        } else {
            return (page - 1) * limit;
        }
    }
}
