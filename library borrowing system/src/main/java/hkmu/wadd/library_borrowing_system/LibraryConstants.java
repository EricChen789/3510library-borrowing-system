package hkmu.wadd.library_borrowing_system;

/**
 * 借阅与逾期费用规则（演示用）。
 */
public final class LibraryConstants {

    /** 单次借阅最长天数 */
    public static final int LOAN_PERIOD_DAYS = 14;

    /** 逾期计费：每满 7 天为一个计费段，每段 10 HKD（第 1–7 天为第一段10 HKD） */
    public static final int OVERDUE_FEE_PER_BLOCK_HKD = 10;

    /** 每个计费段对应的天数 */
    public static final int OVERDUE_FEE_BLOCK_DAYS = 7;

    private LibraryConstants() {}
}
