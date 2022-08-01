package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.EmployeeView;
import actions.views.LikeView;
import actions.views.ReportView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.MessageConst;
import services.LikeService;
import services.ReportService;

/**
 * いいねに関する処理を行うActionクラス
 *
 */
public class LikeAction extends ActionBase {

    private LikeService service;
    private ReportService repservice;
    /**
     * メソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException {

        service = new LikeService();
        repservice = new ReportService();
        //メソッドを実行
        invoke();
        service.close();
    }

    /**
     * 新規登録を行う
     * @throws ServletException
     * @throws IOException
     */
    public void create() throws ServletException, IOException {

        	//セッションからログイン中の従業員情報を取得
            EmployeeView ev = (EmployeeView)getSessionScope(AttributeConst.LOGIN_EMP);
            //idを条件に日報データを取得する
            ReportView rv = repservice.findOne(toNumber(getRequestParam(AttributeConst.REP_NAMEID)));

          //CSRF対策 tokenのチェック
    		if (checkToken()) {
            //パラメータの値をもとにいいねのインスタンスを作成する
            LikeView lv = new LikeView(
                    null,
                    rv,
                    ev);

            //いいね情報登録
            List<String> errors = service.create(lv);

            if (errors.size() > 0) {
                //登録中にエラーがあった場合

                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.REPORT, lv);//入力されたいいね情報
                putRequestScope(AttributeConst.ERR, errors);//エラーのリスト

                //詳細画面を再表示
                forward(ForwardConst.FW_REP_SHOW);

            } else {
                //登録中にエラーがなかった場合

                //セッションに登録完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_REP, ForwardConst.CMD_INDEX);
            }
        }
    }


}