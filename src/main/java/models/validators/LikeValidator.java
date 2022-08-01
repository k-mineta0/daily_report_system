package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.LikeView;

/**
 * いいねインスタンスに設定されている値のバリデーションを行うクラス
 */
public class LikeValidator {

	/**
	 * インスタンスの各項目についてバリデーションを行う
	 * @param lv 日報インスタンス
	 * @return エラーのリスト
	 */
	public static List<String> validate(LikeView lv) {
		List<String> errors = new ArrayList<String>();

		return errors;
	}

}