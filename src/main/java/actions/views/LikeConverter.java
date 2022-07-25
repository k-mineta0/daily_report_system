package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Like;

/**
 * DTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class LikeConverter {

    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param rv LikeViewのインスタンス
     * @return Likeのインスタンス
     */
    public static Like toModel(LikeView lv) {
        return new Like(
                lv.getId(),
                ReportConverter.toModel(lv.getReport()),
                EmployeeConverter.toModel(lv.getEmployee()));

    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param l Likeのインスタンス
     * @return LikeViewのインスタンス
     */
    public static LikeView toView(Like l) {

        if (l == null) {
            return null;
        }

        return new LikeView(
        l.getId(),
        ReportConverter.toView(l.getReport()),
        EmployeeConverter.toView(l.getEmployee()));

    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<LikeView> toViewList(List<Like> list) {
        List<LikeView> evs = new ArrayList<>();

        for (Like l : list) {
            evs.add(toView(l));
        }

        return evs;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param l DTOモデル(コピー先)
     * @param lv Viewモデル(コピー元)
     */
    public static void copyViewToModel(Like l, LikeView lv) {
        l.setId(lv.getId());
        l.setReport(ReportConverter.toModel(lv.getReport()));
        l.setEmployee(EmployeeConverter.toModel(lv.getEmployee()));

    }

}
