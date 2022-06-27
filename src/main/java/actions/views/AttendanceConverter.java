package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Attendance;

/**
 * 勤怠データのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class AttendanceConverter {

    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param av AttendanceViewのインスタンス
     * @return Reportのインスタンス
     */
    public static Attendance toModel(AttendanceView av) {
        return new Attendance(
                av.getId(),
                EmployeeConverter.toModel(av.getEmployee()),
                av.getStart_at(),
                av.getRest(),
                av.getAttclass(),
                av.getLeavingAt(),
                av.getCreatedAt());
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param a Attendanceのインスタンス
     * @return AttendanceViewのインスタンス
     */
    public static AttendanceView toView(Attendance a) {

        if (a == null) {
            return null;
        }

        return new AttendanceView(
                a.getId(),
                EmployeeConverter.toView(a.getEmployee()),
                a.getStart_at(),
                a.getRest(),
                a.getAttclass(),
                a.getLeavingAt(),
                a.getCreatedAt());
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<AttendanceView> toViewList(List<Attendance> list) {
        List<AttendanceView> evs = new ArrayList<>();

        for (Attendance a : list) {
            evs.add(toView(a));
        }

        return evs;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param r DTOモデル(コピー先)
     * @param rv Viewモデル(コピー元)
     */
    public static void copyViewToModel(Attendance a, AttendanceView av) {
        a.setId(av.getId());
        a.setEmployee(EmployeeConverter.toModel(av.getEmployee()));
        a.setStart_at(av.getStart_at());
        a.setRest(av.getRest());
        a.setAttclass(av.getAttclass());
        a.setLeavingAt(av.getLeavingAt());
        a.setCreatedAt(av.getCreatedAt());

    }

}