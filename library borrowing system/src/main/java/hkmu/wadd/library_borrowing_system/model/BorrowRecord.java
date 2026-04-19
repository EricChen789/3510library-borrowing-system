package hkmu.wadd.library_borrowing_system.model;

import hkmu.wadd.library_borrowing_system.LibraryConstants;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "borrow_records")
public class BorrowRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(nullable = false)
    private LocalDateTime borrowDate;

    private LocalDateTime returnDate;

    @Column(nullable = false)
    private LocalDateTime dueDate;

    @Column(nullable = false)
    private boolean returned = false;

    public BorrowRecord() {}

    public BorrowRecord(User user, Book book, LocalDateTime dueDate) {
        this.user = user;
        this.book = book;
        this.borrowDate = LocalDateTime.now();
        this.dueDate = dueDate;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }
    public LocalDateTime getBorrowDate() { return borrowDate; }
    public void setBorrowDate(LocalDateTime borrowDate) { this.borrowDate = borrowDate; }
    public LocalDateTime getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDateTime returnDate) { this.returnDate = returnDate; }
    public LocalDateTime getDueDate() { return dueDate; }
    public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }
    public boolean isReturned() { return returned; }
    public void setReturned(boolean returned) { this.returned = returned; }

    public boolean isOverdue() {
        return !returned && LocalDateTime.now().isAfter(dueDate);
    }

    public long getDaysUntilDue() {
        if (returned) return 0;
        return getCalendarDaysUntilDue();
    }

    /** 按「日历日」距离应还日期的天数；应还日当天为 0。 */
    public long getCalendarDaysUntilDue() {
        if (returned) return Long.MAX_VALUE;
        return ChronoUnit.DAYS.between(LocalDate.now(), dueDate.toLocalDate());
    }

    /** 已逾期整天天数（应还日次日计为第 1 天）；未逾期为 0。 */
    public long getWholeDaysOverdue() {
        if (returned) return 0;
        LocalDate due = dueDate.toLocalDate();
        LocalDate today = LocalDate.now();
        if (!today.isAfter(due)) return 0;
        return ChronoUnit.DAYS.between(due, today);
    }

    /** 按规则估算逾期费用（HKD）。 */
    public int getEstimatedOverdueFeeHkd() {
        long d = getWholeDaysOverdue();
        if (d <= 0) return 0;
        return LibraryConstants.OVERDUE_FEE_PER_BLOCK_HKD
                * (int) Math.ceil(d / (double) LibraryConstants.OVERDUE_FEE_BLOCK_DAYS);
    }

    /**
     * 应还提醒级别：0 正常(绿)，1 临期(橙，≤3 天)，2 紧急(红，≤1 天)，3 已逾期。
     */
    public int getUrgencyLevel() {
        if (returned) return 0;
        if (isOverdue()) return 3;
        long days = getCalendarDaysUntilDue();
        if (days <= 1) return 2;
        if (days <= 3) return 1;
        return 0;
    }
}