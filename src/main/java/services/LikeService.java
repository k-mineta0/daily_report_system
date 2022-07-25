package services;

import java.util.List;

import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import actions.views.LikeConverter;
import actions.views.LikeView;
import constants.JpaConst;
import models.Like;

/**
 * いいねテーブルの操作に関わる処理を行うクラス
 */
public class LikeService extends ServiceBase {

	 /**
     * 指定した従業員が作成したいいねデータを、指定されたページ数の一覧画面に表示する分取得しReportViewのリストで返却する
     * @param employee 従業員
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    public List<LikeView> getMinePerPage(EmployeeView employee, int page) {

        List<Like> likes = em.createNamedQuery(JpaConst.Q_LIK_GET_ALL_MINE, Like.class)
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(employee))
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return LikeConverter.toViewList(likes);
    }

    /**
     * 指定した従業員が作成したいいねデータの件数を取得し、返却する
     * @param employee
     * @return 日報データの件数
     */
    public long countAllMine(EmployeeView employee) {

        long count = (long) em.createNamedQuery(JpaConst.Q_LIK_COUNT_ALL_MINE, Long.class)
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(employee))
                .getSingleResult();

        return count;
    }

    /**
     * 指定されたページ数の一覧画面に表示するいいねデータを取得し、LikeViewのリストで返却する
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    public List<LikeView> getAllPerPage(int page) {

        List<Like> likes = em.createNamedQuery(JpaConst.Q_LIK_GET_ALL, Like.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return LikeConverter.toViewList(likes);
    }

    /**
     * いいねテーブルのデータの件数を取得し、返却する
     * @return データの件数
     */
    public long countAll() {
        long likes_count = (long) em.createNamedQuery(JpaConst.Q_LIK_COUNT, Long.class)
                .getSingleResult();
        return likes_count;
    }

    /**
     * idを条件に取得したデータをViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public LikeView findOne(int id) {
        return LikeConverter.toView(findOneInternal(id));
    }



    /**
     * idを条件にデータを1件取得する
     * @param id
     * @return 取得データのインスタンス
     */
    private Like findOneInternal(int id) {
        return em.find(Like.class, id);
    }
    /**
     * いいねデータを1件登録する
     * @param lv 日報データ
     */
    private void createInternal(LikeView lv) {

        em.getTransaction().begin();
        em.persist(LikeConverter.toModel(lv));
        em.getTransaction().commit();

    }

    /**
     * いいねデータを更新する
     * @param lv いいねデータ
     */
    private void updateInternal(LikeView lv) {

        em.getTransaction().begin();
        Like l = findOneInternal(lv.getId());
        LikeConverter.copyViewToModel(l, lv);
        em.getTransaction().commit();


    }

}