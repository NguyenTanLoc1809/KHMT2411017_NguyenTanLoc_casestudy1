# Expense Manager – Quản lý thu chi (Buổi 7)

**Sinh viên:** Nguyễn Tấn Lộc – **MSSV:** KHMT2411017
**Môn:** Lập trình thiết bị di động – **Case study 1:** Ứng dụng quản lý thu chi cá nhân
**Công nghệ:** Android (Kotlin), giao diện XML, SQLite (`SQLiteOpenHelper`)

## Nội dung Buổi 7: Xây dựng database nâng cao + Thống kê

Buổi 7 mở rộng cơ sở dữ liệu lên **phiên bản 2** và dùng dữ liệu đó để làm màn hình **Thống kê**.

| Hạng mục | Trạng thái | Nơi thực hiện |
|---|---|---|
| Bảng `categories` (danh mục lưu trong DB) | ✅ | `DatabaseHelper` |
| **Migration** DB v1 → v2, giữ nguyên giao dịch cũ | ✅ | `DatabaseHelper.onUpgrade()` |
| Truy vấn thống kê `SUM` / `GROUP BY` theo tháng, theo danh mục | ✅ | `getMonthTotals`, `getExpenseByCategory`, `getLatestMonthKey` |
| Màn hình **Thống kê**: tổng thu/chi/còn lại, biểu đồ cột 6 tháng, tỉ lệ chi theo danh mục | ✅ | `StatsActivity`, `BarChartView` |
| Màn hình **Quản lý danh mục**: thêm / xóa danh mục riêng, chọn màu | ✅ | `CategoryActivity` |
| Ô chọn danh mục ở màn hình Thêm/Sửa lấy từ DB | ✅ | `AddTransactionActivity` |
| Kiểm thử DB (thêm/sửa/xóa, thống kê, migration) | ✅ | `DatabaseHelperTest`, `FormattersTest` |

Các chức năng của Buổi 5–6 (SQLite, thêm/sửa/xóa, Dashboard, danh sách có lọc) vẫn giữ nguyên.

## Cách dùng

- **Thống kê:** tab *Thống kê* ở thanh dưới Dashboard. Dùng mũi tên `<` `>` để đổi tháng.
  Mặc định mở ở tháng có giao dịch mới nhất (dữ liệu mẫu nằm ở tháng 9/2024).
- **Quản lý danh mục:** bấm biểu tượng ☰ (menu) ở góc trái Dashboard. Chọn Chi tiêu/Thu nhập, bấm `+` để
  thêm danh mục (đặt tên + chọn màu). Danh mục mặc định không xóa được; danh mục đang có giao dịch dùng
  cũng không xóa được.
- Cài bản này **đè lên bản Buổi 6** để thấy migration: giao dịch cũ vẫn còn, danh mục được thêm tự động.

## Cấu trúc mã nguồn

```
app/src/main/java/com/example/khmt2411017_nguyentanloc/
├── MainActivity.kt              Màn hình chào
├── DashboardActivity.kt         Dashboard
├── AddTransactionActivity.kt    Thêm + sửa + xóa giao dịch
├── TransactionListActivity.kt   Tất cả giao dịch + lọc Thu/Chi
├── StatsActivity.kt             (Buổi 7) Thống kê theo tháng
├── BarChartView.kt              (Buổi 7) Biểu đồ cột tự vẽ bằng Canvas
├── CategoryActivity.kt          (Buổi 7) Quản lý danh mục
├── CategoryAdapter.kt           (Buổi 7)
├── TransactionAdapter.kt
├── DatabaseHelper.kt            SQLite v2: 2 bảng, CRUD, thống kê, migration
├── Transaction.kt  Category.kt  Stats.kt   Model
├── Formatters.kt                Định dạng tiền / ngày / tháng
└── ViewUtils.kt                 Tràn viền, ô nhập tiền, đường kẻ, tiện ích giao diện
docs/database-design.md          Thiết kế CSDL (sơ đồ, SQL, migration, truy vấn)
```

## Cách chạy

1. Mở thư mục dự án bằng **Android Studio** (bản mới, hỗ trợ AGP 9.x) và chờ Gradle sync xong.
2. Chọn máy ảo hoặc điện thoại (Android 7.0 / API 24 trở lên) rồi bấm **Run ▶**.
3. Kiểm thử: chuột phải `DatabaseHelperTest` / `MoneyTextWatcherTest` (androidTest) hoặc `FormattersTest` (test) → *Run*.

## Ảnh chụp màn hình

Thêm ảnh chụp vào `docs/screenshots/` rồi gắn vào đây:

| Dashboard | Thống kê | Quản lý danh mục | Thêm danh mục |
|---|---|---|---|
| _(ảnh)_ | _(ảnh)_ | _(ảnh)_ | _(ảnh)_ |
