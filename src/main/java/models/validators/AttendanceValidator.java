package models.validators;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import actions.views.AttendanceView;
import constants.MessageConst;

/**
 * 勤怠インスタンスに設定されている値のバリデーションを行うクラス
 */
public class AttendanceValidator {

    /**
     * 勤怠インスタンスの各項目についてバリデーションを行う
     * @param av 勤怠インスタンス
     * @return エラーのリスト
     */
    public static List<String> validate(AttendanceView av) {
        List<String> errors = new ArrayList<String>();

        //出勤時間のチェック
        String start_atError = validateStart_at(av.getStart_at());
        if (!start_atError.equals("")) {
            errors.add(start_atError);
        }

        //休憩時間のチェック
        String restError = validateRest(av.getRest());
        if (!restError.equals("")) {
            errors.add(restError);
        }

        //退勤時間のチェック
        String leaving_atError = validateLeaving_at(av.getLeaving_at());
        if (!leaving_atError.equals("")) {
            errors.add(leaving_atError);
        }

        //勤務区分のチェック
        String attclassError = validateattclass(av.getAttclass());
        if (!attclassError.equals("")) {
            errors.add(attclassError);
        }

      //申請区分のチェック
        String requestError = validateRequest(av.getRequest());
        if (!requestError.equals("")) {
            errors.add(requestError);
        }

        return errors;
    }


    private static String validateRequest(String request) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}


	private static String validateRest(LocalDateTime rest) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}


	/**
     * 入力値があるかをチェックし、なければエラーメッセージを返却
     * @return エラーメッセージ
     */
    private static String validateStart_at(LocalDateTime start_at) {
        if (start_at == null || start_at.equals("")) {
            return MessageConst.E_NOCONTENT.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }

    private static String validateLeaving_at(LocalDateTime leaving_at) {
        if (leaving_at == null || leaving_at.equals("")) {
            return MessageConst.E_NOCONTENT.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }
    private static String validaterest(String rest) {
        if (rest == null || rest.equals("")) {
            return MessageConst.E_NOCONTENT.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }

    private static String validateattclass(String attclass) {
        if (attclass == null || attclass.equals("")) {
            return MessageConst.E_NOCONTENT.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }

    private static String validaterequest(String request) {
        if (request == null || request.equals("")) {
            return MessageConst.E_NOCONTENT.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }
}