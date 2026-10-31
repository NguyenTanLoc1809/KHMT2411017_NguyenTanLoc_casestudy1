# Expense Manager – Quản lý thu chi (Buổi 5)

**Sinh viên:** Nguyễn Tấn Lộc – **MSSV:** KHMT2411017
**Môn:** Lập trình thiết bị di động – **Case study 1:** Ứng dụng quản lý thu chi cá nhân
**Công nghệ:** Android (Kotlin), giao diện XML, SQLite (`SQLiteOpenHelper`)

## Yêu cầu Buổi 5

> **Thiết kế và xây dựng database cho ứng dụng bằng SQLite.**

| Yêu cầu | Trạng thái | Nơi thực hiện |
|---|---|---|
| Thiết kế bảng dữ liệu | ✅ | bảng `transactions` – xem [`docs/database-design.md`](docs/database-design.md) |
| Xây dựng lớp SQLite | ✅ | `DatabaseHelper.kt` (`SQLiteOpenHelper`) |
| Các thao tác thêm / sửa / xóa / truy vấn | ✅ | `insertTransaction`, `updateTransaction`, `deleteTransaction`, `getTransactionById`, `getTransactions` |
| Thống kê tổng thu, tổng chi, số dư bằng `SUM` | ✅ | `getTotalIncome`, `getTotalExpense`, `getBalance` |
| Dữ liệu mẫu khi tạo DB lần đầu | ✅ | `seedSampleData()` (khớp bản thiết kế: số dư 5.000.000 đ) |
| Kiểm thử các thao tác DB | ✅ | `DatabaseHelperTest` (thư mục `androidTest`) |
| Dashboard hiển thị dữ liệu thật từ SQLite | ✅ | `DashboardActivity` |

## Cách kiểm chứng database ngay trên app

Form Thêm/Sửa đầy đủ thuộc **Buổi 6**. Ở Buổi 5, Dashboard có 3 thao tác để thấy database hoạt động:

| Thao tác trên app | Lệnh SQLite tương ứng |
|---|---|
| Bấm nút **`+`** | `INSERT` một giao dịch mẫu (ngày hôm nay) → số dư, tổng thu/chi, danh sách đổi theo |
| **Bấm** vào một giao dịch | `SELECT ... WHERE id = ?` → hiện chi tiết đọc từ DB |
| **Giữ lâu** một giao dịch | `DELETE ... WHERE id = ?` (có hộp thoại xác nhận) |

Tắt hẳn app rồi mở lại: dữ liệu vẫn còn vì đã được lưu trong file `expense_manager.db`.

## Cấu trúc mã nguồn

```
app/src/main/java/com/example/khmt2411017_nguyentanloc/
├── MainActivity.kt         Màn hình chào
├── DashboardActivity.kt    Dashboard đọc dữ liệu từ SQLite
├── TransactionAdapter.kt   Adapter RecyclerView
├── DatabaseHelper.kt       SQLite: tạo bảng, CRUD, thống kê, dữ liệu mẫu
├── Transaction.kt          Model giao dịch
├── Category.kt             Danh mục (tên, icon, màu)
├── Formatters.kt           Định dạng tiền / ngày
└── ViewUtils.kt            Xử lý tràn viền, đường kẻ danh sách
app/src/androidTest/.../DatabaseHelperTest.kt   Kiểm thử SQLite
docs/database-design.md     Thiết kế CSDL (sơ đồ, SQL, dữ liệu mẫu)
```

## Cách chạy

1. Mở thư mục dự án bằng **Android Studio** (bản mới, hỗ trợ AGP 9.x) và chờ Gradle sync xong.
2. Chọn máy ảo hoặc điện thoại (Android 7.0 / API 24 trở lên) rồi bấm **Run ▶**.
3. Lần chạy đầu app tự tạo database và chèn 6 giao dịch mẫu.
4. Chạy `DatabaseHelperTest` (chuột phải → *Run*) để kiểm thử thêm/sửa/xóa/tính tổng.

> Xem lại dữ liệu trong Android Studio: **View → Tool Windows → App Inspection → Database Inspector**.

## Ảnh chụp màn hình

Thêm ảnh chụp vào thư mục `docs/screenshots/` rồi gắn vào đây:

| Chào | Dashboard |
|---|---|
| _(ảnh)_ | _(ảnh)_ |
