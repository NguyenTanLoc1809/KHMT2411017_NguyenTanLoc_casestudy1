# Expense Manager – Quản lý thu chi (Buổi 8 – Bản hoàn thiện)

**Sinh viên:** Nguyễn Tấn Lộc – **MSSV:** KHMT2411017
**Môn:** Lập trình thiết bị di động – **Case study 1:** Ứng dụng quản lý thu chi cá nhân
**Công nghệ:** Android (Kotlin), giao diện XML, SQLite (`SQLiteOpenHelper`)

## Nội dung Buổi 8: Hoàn thiện ứng dụng

Buổi này gom mọi chức năng từ Buổi 5–7 và hoàn thiện những phần còn thiếu để app dùng được như sản phẩm thật.

### Toàn bộ chức năng của ứng dụng

| Nhóm | Chức năng | Buổi |
|---|---|---|
| Cơ sở dữ liệu | SQLite 2 bảng (`transactions`, `categories`), migration v1 → v2 | 5, 7 |
| Dashboard | Số dư (ẩn/hiện), tổng thu, tổng chi, 5 giao dịch gần nhất | 5, 6 |
| Giao dịch | Thêm / sửa / xóa, nhập tiền tự chèn dấu chấm, chọn ngày, kiểm tra dữ liệu | 6 |
| Thống kê | Thu/chi theo tháng, biểu đồ cột 6 tháng, tỉ lệ chi theo danh mục | 7 |
| Danh mục | Xem / thêm / xóa danh mục riêng, chọn màu | 7 |
| **Tìm kiếm** | Tìm theo tên, ghi chú, danh mục; **gõ không dấu vẫn tìm được** ("an trua" → "Ăn trưa") | **8** |
| **Vuốt để xóa + Hoàn tác** | Vuốt ngang một giao dịch để xóa, bấm HOÀN TÁC để lấy lại | **8** |
| **Menu ☰** | Quản lý danh mục · Xuất CSV · Xóa toàn bộ giao dịch · Giới thiệu | **8** |
| **Xuất CSV** | Lưu toàn bộ giao dịch ra file `.csv` (mở bằng Excel/Google Sheets, đúng tiếng Việt) | **8** |
| **Chuông thông báo** | Chấm đỏ + hộp thoại cảnh báo khi tháng này chi nhiều hơn thu | **8** |
| **Biểu tượng ứng dụng** | Icon ví tiền thay cho icon mặc định | **8** |

## Hướng dẫn sử dụng nhanh

1. **Chào → Bắt đầu** để vào Dashboard.
2. Nút **`+`**: thêm giao dịch. **Bấm một giao dịch**: sửa hoặc xóa.
3. **Xem tất cả** (hoặc tab *Giao dịch*): danh sách đầy đủ, gõ vào ô tìm kiếm, lọc Thu/Chi, vuốt để xóa.
4. Tab **Thống kê**: đổi tháng bằng mũi tên `<` `>`.
5. **Biểu tượng ☰**: menu chức năng (danh mục, xuất CSV, xóa dữ liệu, giới thiệu).
6. **Biểu tượng chuông**: thông báo tình hình thu chi tháng hiện tại.

> Dữ liệu mẫu nằm ở tháng 8–9/2024 nên chuông thông báo (tính theo tháng hiện tại thật) sẽ báo "chưa có giao dịch"
> cho tới khi bạn thêm giao dịch trong tháng này. Muốn thấy cảnh báo: thêm một khoản chi lớn hơn tổng thu của tháng.

## Cấu trúc mã nguồn

```
app/src/main/java/com/example/khmt2411017_nguyentanloc/
├── MainActivity.kt              Màn hình chào
├── DashboardActivity.kt         Dashboard + menu ☰ + chuông thông báo + xuất CSV
├── AddTransactionActivity.kt    Thêm + sửa + xóa giao dịch
├── TransactionListActivity.kt   Danh sách đầy đủ: lọc, tìm kiếm, vuốt xóa + hoàn tác
├── StatsActivity.kt             Thống kê theo tháng
├── BarChartView.kt              Biểu đồ cột tự vẽ bằng Canvas
├── CategoryActivity.kt          Quản lý danh mục
├── TransactionAdapter.kt  CategoryAdapter.kt
├── DatabaseHelper.kt            SQLite v2: 2 bảng, CRUD, thống kê, migration
├── CsvExporter.kt               Tạo nội dung CSV (có chống CSV injection)
├── Transaction.kt  Category.kt  Stats.kt   Model
├── Formatters.kt                Tiền / ngày / tháng / bỏ dấu tiếng Việt
└── ViewUtils.kt                 Tràn viền, ô nhập tiền, đường kẻ, tiện ích
docs/database-design.md          Thiết kế CSDL (sơ đồ, SQL, migration, truy vấn)
```

## Kiểm thử

| Loại | File | Nội dung |
|---|---|---|
| Unit test (chạy trên máy tính) | `FormattersTest`, `CsvExporterTest` | định dạng tiền/ngày/tháng, bỏ dấu, CSV |
| Instrumented test (cần máy ảo) | `DatabaseHelperTest` | CRUD, thống kê, danh mục, migration v1→v2, xóa hết/hoàn tác |
| Instrumented test | `MoneyTextWatcherTest` | hồi quy lỗi văng khi gõ số tiền ≥ 4 chữ số |

## Cách chạy

1. Mở thư mục dự án bằng **Android Studio** (bản mới, hỗ trợ AGP 9.x) và chờ Gradle sync xong.
2. Chọn máy ảo hoặc điện thoại (Android 7.0 / API 24 trở lên) rồi bấm **Run ▶**.
3. Lần chạy đầu app tự tạo database, danh mục mặc định và 6 giao dịch mẫu (số dư 5.000.000 đ).
4. Xem dữ liệu trực tiếp: **View → Tool Windows → App Inspection → Database Inspector**.

## Ảnh chụp màn hình

Thêm ảnh chụp vào `docs/screenshots/` rồi gắn vào đây:

| Dashboard | Thêm giao dịch | Tất cả giao dịch | Thống kê | Menu |
|---|---|---|---|---|
| _(ảnh)_ | _(ảnh)_ | _(ảnh)_ | _(ảnh)_ | _(ảnh)_ |
