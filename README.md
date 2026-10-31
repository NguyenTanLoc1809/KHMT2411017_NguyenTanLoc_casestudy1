# Expense Manager – Quản lý thu chi (Buổi 6)

**Sinh viên:** Nguyễn Tấn Lộc – **MSSV:** KHMT2411017
**Môn:** Lập trình thiết bị di động – **Case study 1:** Ứng dụng quản lý thu chi cá nhân
**Công nghệ:** Android (Kotlin), giao diện XML, SQLite (`SQLiteOpenHelper`)

## Yêu cầu Buổi 6

> Hoàn thiện các màn hình **thêm, sửa** và **load thông tin giao dịch lên màn hình Dashboard**.

| Yêu cầu | Trạng thái | Nơi thực hiện |
|---|---|---|
| Màn hình **Thêm** giao dịch | ✅ | `AddTransactionActivity` (nút `+` ở Dashboard) |
| Màn hình **Sửa** giao dịch | ✅ | `AddTransactionActivity` (bấm vào 1 giao dịch) |
| Xóa giao dịch (có hộp thoại xác nhận) | ✅ | nút "Xóa giao dịch" trong chế độ sửa |
| Load số dư, tổng thu, tổng chi lên Dashboard | ✅ | `DashboardActivity.loadData()` |
| Load giao dịch gần đây lên Dashboard | ✅ | RecyclerView + `TransactionAdapter` |
| Xem tất cả giao dịch, lọc Thu/Chi | ✅ (bổ sung) | `TransactionListActivity` ("Xem tất cả") |
| Dữ liệu lưu bền vững bằng SQLite (từ Buổi 5) | ✅ | `DatabaseHelper` |

## Chức năng chi tiết

- **Dashboard** đúng bố cục mẫu thiết kế: thẻ số dư (có nút ẩn/hiện số dư), thẻ tổng thu nhập / tổng chi tiêu,
  5 giao dịch gần nhất, nút `+`, thanh điều hướng dưới. Dữ liệu tự cập nhật mỗi khi quay lại màn hình.
- **Thêm / Sửa giao dịch**: chọn Chi tiêu/Thu nhập, nhập số tiền (tự chèn dấu chấm `1.500.000`),
  tên giao dịch, danh mục (đổi theo loại), ngày (hộp chọn ngày), ghi chú. Có kiểm tra dữ liệu nhập
  (thiếu tên, số tiền = 0, thiếu danh mục sẽ báo lỗi ngay tại ô nhập).
- **Xóa giao dịch** có hộp thoại xác nhận.
- **Tất cả giao dịch**: danh sách đầy đủ, lọc theo Tất cả / Thu nhập / Chi tiêu.
- Tab **Thống kê** và nút **Menu** hiện thông báo "sẽ hoàn thiện ở các buổi sau" (thuộc phạm vi buổi 7–8).
  Số `3` trên biểu tượng chuông là phần giao diện tĩnh theo mẫu.

## Cấu trúc mã nguồn

```
app/src/main/java/com/example/khmt2411017_nguyentanloc/
├── MainActivity.kt              Màn hình chào
├── DashboardActivity.kt         Dashboard (số dư, tổng thu/chi, giao dịch gần đây)
├── AddTransactionActivity.kt    Thêm + sửa + xóa giao dịch
├── TransactionListActivity.kt   Tất cả giao dịch + bộ lọc
├── TransactionAdapter.kt        Adapter RecyclerView
├── DatabaseHelper.kt            SQLite: tạo bảng, CRUD, thống kê
├── Transaction.kt               Model giao dịch
├── Category.kt                  Danh mục (tên, icon, màu)
├── Formatters.kt                Định dạng tiền / ngày
└── ViewUtils.kt                 Xử lý tràn viền, tự định dạng ô tiền, đường kẻ danh sách
docs/database-design.md          Thiết kế CSDL (sơ đồ, SQL, dữ liệu mẫu)
```


## Cách chạy

1. Mở thư mục dự án bằng **Android Studio** (bản mới, hỗ trợ AGP 9.x) và chờ Gradle sync xong.
2. Chọn máy ảo hoặc điện thoại (Android 7.0 / API 24 trở lên) rồi bấm **Run ▶**.
3. Lần chạy đầu app tự tạo database và chèn 6 giao dịch mẫu (số dư 5.000.000 đ).

Chạy kiểm thử: `Run > Run Tests` (unit test) hoặc chạy `DatabaseHelperTest` trong `androidTest` để kiểm tra
thêm/sửa/xóa/tính tổng của SQLite.


