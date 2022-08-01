package services;

import java.util.List;

import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import actions.views.LikeConverter;
import actions.views.LikeView;
import actions.views.ReportConverter;
import actions.views.ReportView;
import constants.JpaConst;
import models.Like;
import models.validators.LikeValidator;

/**
 * いいねテーブルの操作に関わる処理を行うクラス
 */
public class LikeService extends ServiceBase {

	 /**
     * 指定した従業員が作成したいいねデータを、指定されたページ数の一覧画面に表示する分取得しLikeViewのリストで返却する
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
     * 指定した日報のいいねの件数を取得し、返却する
     * @param report
     * @return いいねデータの件数
     */
    public long countAllMine(ReportView report, EmployeeView employee) {

        long my_like_count = (long) em.createNamedQuery(JpaConst.Q_LIK_COUNT_ALL_MINE, Long.class)
                .setParameter(JpaConst.JPQL_PARM_REPORT, ReportConverter.toModel(report))
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(employee))
                .getSingleResult();

       return my_like_count;
    }



    /**
     * idを条件に取得したデータをLikeViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public LikeView findOne(int id) {
        return LikeConverter.toView(findOneInternal(id));
    }


    /**
     * 画面から入力されたいいねの登録内容を元にデータを1件作成し、いいねテーブルに登録する
     * @param lv いいねの登録内容
     * @return バリデーションや登録処理中に発生したエラーのリスト
     */
    public List<String> create(LikeView lv) {
    	//登録内容のバリデーションを行う
        List<String> errors = LikeValidator.validate(lv);
        if (errors.size() == 0) {
            createInternal(lv);
        }

        //エラーを返却（エラーがなければ0件の空リスト）
        return errors;
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
     * @param lv いいねデータ
     */
    private void createInternal(LikeView lv) {

        em.getTransaction().begin();
        em.persist(LikeConverter.toModel(lv));
        em.getTransaction().commit();

    	}
    public long count(ReportView report) {
        long reportslikeCount = (long) em.createNamedQuery(JpaConst.Q_LIK_COUNT, Long.class)
                .setParameter(JpaConst.JPQL_PARM_REPORT, ReportConverter.toModel(report))
                .getSingleResult();
        return reportslikeCount;
    }}